package com.jesslabs.finance.amortisationscheduleservice.service;

import com.jesslabs.finance.amortisationscheduleservice.dto.AmortizationDTO;
import com.jesslabs.finance.amortisationscheduleservice.dto.AmortizationScheduleDTO;
import com.jesslabs.finance.amortisationscheduleservice.dto.LoanDetailsDTO;
import com.jesslabs.finance.amortisationscheduleservice.exception.NotFoundException;
import com.jesslabs.finance.amortisationscheduleservice.exception.InternalServerException;
import lombok.NonNull;

import java.util.List;

public interface AmortizationScheduleService {

    AmortizationScheduleDTO createAmortizationSchedule(@NonNull LoanDetailsDTO loanDetailsDto) throws InternalServerException;

    List<AmortizationScheduleDTO> getAmortizationSchedules() throws InternalServerException;

    List<AmortizationDTO> getAmortizationsByAmortizationScheduleId(Long id) throws NotFoundException, InternalServerException;

}
