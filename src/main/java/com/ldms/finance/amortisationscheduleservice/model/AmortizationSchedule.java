package com.ldms.finance.amortisationscheduleservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "AMORTIZATION_SCHEDULE")
public class AmortizationSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @OneToMany(mappedBy = "amortizationSchedule", cascade = CascadeType.ALL)
    private List<Amortization> amortizations;

    @OneToOne(cascade = CascadeType.ALL)
    private LoanDetails loanDetails;

    @OneToOne(cascade = CascadeType.ALL)
    private AmortizationScheduleDetails amortizationScheduleDetails;

}
