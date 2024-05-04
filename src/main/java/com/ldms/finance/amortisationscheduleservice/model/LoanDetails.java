package com.ldms.finance.amortisationscheduleservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LOAN_DETAILS")
public class LoanDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @PositiveOrZero
    @Column(name = "COST")
    private Double cost;

    @PositiveOrZero
    @Column(name = "DEPOSIT")
    private Double deposit;

    @PositiveOrZero
    @Column(name = "INTEREST")
    private Double interest;

    @PositiveOrZero
    @Min(value = 1)
    @Column(name = "NO_OF_PAYMENTS")
    private Integer noOfPayments;

    @PositiveOrZero
    @Min(value = 0)
    @Column(name = "BALLOON")
    private Double balloon;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AmortizationSchedule amortizationSchedule;

}
