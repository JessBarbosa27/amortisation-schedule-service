package com.jesslabs.finance.amortisationscheduleservice.mapper;

import com.jesslabs.finance.amortisationscheduleservice.dto.AmortizationDTO;
import com.jesslabs.finance.amortisationscheduleservice.dto.AmortizationScheduleDTO;
import com.jesslabs.finance.amortisationscheduleservice.dto.LoanDetailsDTO;
import com.jesslabs.finance.amortisationscheduleservice.model.Amortization;
import com.jesslabs.finance.amortisationscheduleservice.model.AmortizationSchedule;
import com.jesslabs.finance.amortisationscheduleservice.model.LoanDetails;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AmortizationScheduleMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LoanDetails loanDetailsDTOToLoanDetailsDTO(LoanDetailsDTO loanDetailsDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void amortizationScheduleListToAmortizationScheduleDTOList(List<AmortizationSchedule> amortizationScheduleList, @MappingTarget List<AmortizationScheduleDTO> amortizationScheduleDTOList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AmortizationScheduleDTO amortizationScheduleToAmortizationScheduleDTO(AmortizationSchedule amortizationSchedule);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void amortizationListToAmortizationDTOList(List<Amortization> amortizationList, @MappingTarget List<AmortizationDTO> amortizationDTOList);

}
