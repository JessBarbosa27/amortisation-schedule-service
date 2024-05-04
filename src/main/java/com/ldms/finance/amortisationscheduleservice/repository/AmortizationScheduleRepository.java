package com.ldms.finance.amortisationscheduleservice.repository;

import com.ldms.finance.amortisationscheduleservice.model.AmortizationSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmortizationScheduleRepository extends JpaRepository<AmortizationSchedule, Long> {
}
