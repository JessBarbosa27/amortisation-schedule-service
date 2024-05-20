package com.jesslabs.finance.amortisationscheduleservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "AMORTIZATION")
public class Amortization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PERIOD")
    private int period;

    @Column(name = "PAYMENT")
    private Double payment;

    @Column(name = "PRINCIPAL")
    private Double principal;

    @Column(name = "INTEREST")
    private Double interest;

    @Column(name = "BALANCE")
    private Double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AMORTIZATION_SCHEDULE_ID")
    private AmortizationSchedule amortizationSchedule;

}
