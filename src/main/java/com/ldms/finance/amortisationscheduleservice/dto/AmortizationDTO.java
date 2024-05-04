package com.ldms.finance.amortisationscheduleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmortizationDTO {

    private Long id;

    private int period;

    private Double payment;

    private Double principal;

    private Double interest;

    private Double balance;

}
