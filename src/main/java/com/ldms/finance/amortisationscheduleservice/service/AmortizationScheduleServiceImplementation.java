package com.ldms.finance.amortisationscheduleservice.service;

import com.ldms.finance.amortisationscheduleservice.dto.AmortizationDTO;
import com.ldms.finance.amortisationscheduleservice.dto.AmortizationScheduleDTO;
import com.ldms.finance.amortisationscheduleservice.dto.LoanDetailsDTO;
import com.ldms.finance.amortisationscheduleservice.exception.InternalServerException;
import com.ldms.finance.amortisationscheduleservice.exception.NotFoundException;
import com.ldms.finance.amortisationscheduleservice.mapper.AmortizationScheduleMapper;
import com.ldms.finance.amortisationscheduleservice.model.Amortization;
import com.ldms.finance.amortisationscheduleservice.model.AmortizationSchedule;
import com.ldms.finance.amortisationscheduleservice.model.AmortizationScheduleDetails;
import com.ldms.finance.amortisationscheduleservice.model.LoanDetails;
import com.ldms.finance.amortisationscheduleservice.repository.AmortizationRepository;
import com.ldms.finance.amortisationscheduleservice.repository.AmortizationScheduleRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class AmortizationScheduleServiceImplementation implements AmortizationScheduleService {

    private final AmortizationScheduleRepository amortizationScheduleRepository;
    private final AmortizationRepository amortizationRepository;
    private final AmortizationScheduleMapper amortizationScheduleMapper;

    public AmortizationScheduleServiceImplementation(AmortizationScheduleRepository amortizationScheduleRepository, AmortizationRepository amortizationRepository, AmortizationScheduleMapper amortizationScheduleMapper) {
        this.amortizationScheduleRepository = amortizationScheduleRepository;
        this.amortizationRepository = amortizationRepository;
        this.amortizationScheduleMapper = amortizationScheduleMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AmortizationScheduleDTO createAmortizationSchedule(@NonNull LoanDetailsDTO loanDetailsDto) throws InternalServerException {
        log.info("creating amortization schedule for cost: {}, deposit: {}, interest: {}, no of payments: {} and balloon: {}", loanDetailsDto.getCost(), loanDetailsDto.getDeposit(), loanDetailsDto.getInterest(), loanDetailsDto.getNoOfPayments(), loanDetailsDto.getBalloon());

        Double interestRate = (loanDetailsDto.getInterest() / 100) / 12;

        Double remainingBalance = loanDetailsDto.getCost() - loanDetailsDto.getDeposit();

        Double monthlyRepayment = calculateMonthlyRepayment(remainingBalance, loanDetailsDto.getNoOfPayments(), interestRate, loanDetailsDto.getBalloon());

        AmortizationSchedule amortizationSchedule = new AmortizationSchedule();

        List<Amortization> amortizations = new LinkedList<>();

        try {
            LoanDetails loanDetails = amortizationScheduleMapper.loanDetailsDTOToLoanDetailsDTO(loanDetailsDto);

            double totalPendingInterest = 0.00;

            double totalPendingPayment = 0.00;

            for (int period = 1; period <= loanDetailsDto.getNoOfPayments(); period++) {

                Double calculatedInterest = roundOff(getInterest(remainingBalance, interestRate));

                Double principle = roundOff(getPrincipleAmount(monthlyRepayment, calculatedInterest));

                Double balance = roundOff(getBalanceAmount(remainingBalance, principle));

                Amortization amortization = new Amortization();
                amortization.setPeriod(period);
                amortization.setPayment(getPreciseDoubleValue(monthlyRepayment));
                amortization.setPrincipal(getPreciseDoubleValue(principle));
                amortization.setInterest(getPreciseDoubleValue(calculatedInterest));
                amortization.setBalance(getPreciseDoubleValue(balance));

                amortizations.add(amortization);

                remainingBalance = balance;
                totalPendingInterest = totalPendingInterest + calculatedInterest;
                totalPendingPayment = totalPendingPayment + monthlyRepayment;
            }

            amortizationSchedule.setAmortizations(amortizations);

            AmortizationScheduleDetails amortizationScheduleDetails = new AmortizationScheduleDetails();
            amortizationScheduleDetails.setMonthlyRepayment(getPreciseDoubleValue(monthlyRepayment));
            amortizationScheduleDetails.setDueInterest(getPreciseDoubleValue(totalPendingInterest));
            amortizationScheduleDetails.setDuePayment(getPreciseDoubleValue(totalPendingPayment));

            amortizationSchedule.setAmortizationScheduleDetails(amortizationScheduleDetails);
            amortizationScheduleDetails.setAmortizationSchedule(amortizationSchedule);

            amortizationSchedule.setLoanDetails(loanDetails);
            loanDetails.setAmortizationSchedule(amortizationSchedule);

        } catch (Exception e) {
            String errorMessage = "error occurred while processing amortization schedules";
            log.error(errorMessage, e);
            throw new InternalServerException(errorMessage);
        }

        try {
            amortizationScheduleRepository.save(amortizationSchedule);

            amortizations.forEach(amortization -> amortization.setAmortizationSchedule(amortizationSchedule));

            amortizationRepository.saveAll(amortizations);

            AmortizationScheduleDTO amortizationScheduleDTO = amortizationScheduleMapper.amortizationScheduleToAmortizationScheduleDTO(amortizationSchedule);

            log.info("successfully created amortization schedules");
            return amortizationScheduleDTO;
        } catch (Exception e) {
            String errorMessage = "error occurred while creating amortization schedule.";
            log.error(errorMessage, e);
            throw new InternalServerException(errorMessage);
        }
    }

    @Override
    public List<AmortizationScheduleDTO> getAmortizationSchedules() throws InternalServerException {
        try {
            List<AmortizationSchedule> amortizationScheduleList = amortizationScheduleRepository.findAll();

            List<AmortizationScheduleDTO> amortizationScheduleDTOList = new ArrayList<>();
            amortizationScheduleMapper.amortizationScheduleListToAmortizationScheduleDTOList(amortizationScheduleList, amortizationScheduleDTOList);

            return amortizationScheduleDTOList;
        } catch (Exception e) {
            String errorMessage = "error occurred while getting amortization schedules";
            log.error(errorMessage, e);
            throw new InternalServerException(errorMessage);
        }
    }

    @Override
    public List<AmortizationDTO> getAmortizationsByAmortizationScheduleId(Long id) throws NotFoundException, InternalServerException {
        log.info("getting amortizations for amortization schedule id: {}", id);

        if (!amortizationScheduleRepository.existsById(id)) {
            String errorMessage = String.format("could not find amortization schedule with id: %s", id);
            log.warn(errorMessage);
            throw new NotFoundException(errorMessage);
        }

        try {
            List<Amortization> amortizationList = amortizationRepository.findAmortizationsByAmortizationSchedule_Id(id);

            List<AmortizationDTO> amortizationDTOList = new ArrayList<>();
            amortizationScheduleMapper.amortizationListToAmortizationDTOList(amortizationList, amortizationDTOList);

            return amortizationDTOList;
        } catch (Exception e) {
            String errorMessage = String.format("error occurred while getting amortizations for amortization schedule id: %s", id);
            log.error(errorMessage, e);
            throw new InternalServerException(errorMessage);
        }

    }

    private Double calculateMonthlyRepayment(Double remainingBalance, Integer noOfPayments, Double interestRate, Double balloonPayment) throws InternalServerException {
        try {
            double value;
//        (1 + r) ^ n)
            double powerOfInterestPayments = Math.pow(1 + interestRate, noOfPayments);
            if (balloonPayment > 0) {
//			 (P - (B / ((1 + r) ^ n))) * (r / (1 - (1 + r) ^ -n))
                value = (remainingBalance - (balloonPayment / powerOfInterestPayments)) * (interestRate / (1 - Math.pow(1 + interestRate, -noOfPayments)));
            } else {
                // P * ((r * (1 + r) ^ n) / ((1 + r) ^ n - 1))
                value = remainingBalance * ((interestRate * powerOfInterestPayments) / (powerOfInterestPayments - 1));
            }
            return value;
        } catch (Exception e) {
            throw new InternalServerException("error occurred while calculating monthly repayment");
        }
    }

    private Double getInterest(@NonNull Double remainingBalance, @NonNull Double interestRate) {
        return remainingBalance * interestRate;
    }

    private Double getPrincipleAmount(@NonNull Double monthlyRepayment, @NonNull Double calculatedInterest) {
        return monthlyRepayment - calculatedInterest;
    }

    private Double getBalanceAmount(@NonNull Double remainingBalance, @NonNull Double principle) {
        return remainingBalance - principle;
    }

    private Double roundOff(@NonNull Double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    private Double getPreciseDoubleValue(@NonNull Double value) {
        return Double.parseDouble(String.format("%.2f", value));
    }
}
