package com.ldms.finance.amortisationscheduleservice.service;

import com.ldms.finance.amortisationscheduleservice.dto.AmortizationDTO;
import com.ldms.finance.amortisationscheduleservice.dto.AmortizationScheduleDTO;
import com.ldms.finance.amortisationscheduleservice.dto.LoanDetailsDTO;
import com.ldms.finance.amortisationscheduleservice.exception.InternalServerException;
import com.ldms.finance.amortisationscheduleservice.exception.NotFoundException;
import lombok.NonNull;

import java.util.List;

public interface AmortizationScheduleService {

    AmortizationScheduleDTO createAmortizationSchedule(@NonNull LoanDetailsDTO loanDetailsDto) throws InternalServerException;

    List<AmortizationScheduleDTO> getAmortizationSchedules() throws InternalServerException;

    List<AmortizationDTO> getAmortizationsByAmortizationScheduleId(Long id) throws NotFoundException, InternalServerException;

}
