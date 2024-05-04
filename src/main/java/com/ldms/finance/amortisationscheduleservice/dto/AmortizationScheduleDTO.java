package com.ldms.finance.amortisationscheduleservice.dto;

import com.ldms.finance.amortisationscheduleservice.model.AmortizationScheduleDetails;
import com.ldms.finance.amortisationscheduleservice.model.LoanDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmortizationScheduleDTO {

    private Long id;

    private LoanDetails loanDetails;

    private AmortizationScheduleDetails amortizationScheduleDetails;

}