package com.jesslabs.finance.amortisationscheduleservice.dto;

import com.jesslabs.finance.amortisationscheduleservice.model.AmortizationScheduleDetails;
import com.jesslabs.finance.amortisationscheduleservice.model.LoanDetails;
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