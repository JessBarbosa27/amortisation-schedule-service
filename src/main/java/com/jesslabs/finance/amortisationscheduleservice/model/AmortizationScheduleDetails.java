package com.jesslabs.finance.amortisationscheduleservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "AMORTIZATION_SCHEDULE_DETAILS")
public class AmortizationScheduleDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @JsonIgnore
    private Long id;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AmortizationSchedule amortizationSchedule;

    @Column(name = "MONTHLY_REPAYMENT")
    private Double monthlyRepayment;

    @Column(name = "DUE_INTEREST")
    private Double dueInterest;

    @Column(name = "DUE_PAYMENT")
    private Double duePayment;

}
