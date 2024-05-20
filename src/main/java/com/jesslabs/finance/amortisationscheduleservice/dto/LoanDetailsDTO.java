package com.jesslabs.finance.amortisationscheduleservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanDetailsDTO {

    @JsonIgnore
    private Long id;

    @Positive
    private Double cost;

    @PositiveOrZero
    private Double deposit;

    @PositiveOrZero
    private Double interest;

    @Min(value = 1)
    @Positive
    private int noOfPayments;

    @PositiveOrZero
    private Double balloon;

}
