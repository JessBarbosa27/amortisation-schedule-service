package com.jesslabs.finance.amortisationscheduleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmortizationScheduleDetailsDTO {

    private Long id;

    private Long amortizationScheduleId;

    private Double monthlyRepayment;

    private Double dueInterestAmount;

    private Double balance;

}
