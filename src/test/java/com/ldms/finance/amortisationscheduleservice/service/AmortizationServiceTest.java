package com.ldms.finance.amortisationscheduleservice.service;

import com.ldms.finance.amortisationscheduleservice.dto.LoanDetailsDTO;
import com.ldms.finance.amortisationscheduleservice.exception.InternalServerException;
import com.ldms.finance.amortisationscheduleservice.mapper.AmortizationScheduleMapper;
import com.ldms.finance.amortisationscheduleservice.model.AmortizationSchedule;
import com.ldms.finance.amortisationscheduleservice.model.LoanDetails;
import com.ldms.finance.amortisationscheduleservice.repository.AmortizationRepository;
import com.ldms.finance.amortisationscheduleservice.repository.AmortizationScheduleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AmortizationServiceTest {

    @Mock
    private AmortizationScheduleRepository amortizationScheduleRepository;

    @Mock
    private AmortizationScheduleMapper amortizationScheduleMapper;

    @Mock
    AmortizationRepository amortizationRepository;

    @InjectMocks
    private AmortizationScheduleServiceImplementation service;

    @Test
    void testCreateAmortizationSchedule() throws InternalServerException {
        LoanDetailsDTO loanDetailsDTO = new LoanDetailsDTO();
        loanDetailsDTO.setCost(20000.00);
        loanDetailsDTO.setDeposit(0.00);
        loanDetailsDTO.setInterest(7.5);
        loanDetailsDTO.setNoOfPayments(12);
        loanDetailsDTO.setBalloon(10000.0);

        when(amortizationScheduleMapper.loanDetailsDTOToLoanDetailsDTO(any(LoanDetailsDTO.class)))
                .thenReturn(new LoanDetails());
        when(amortizationScheduleRepository.save(any(AmortizationSchedule.class)))
                .thenReturn(new AmortizationSchedule());

        service.createAmortizationSchedule(loanDetailsDTO);

        verify(amortizationScheduleRepository, times(1)).save(any(AmortizationSchedule.class));
    }

}
