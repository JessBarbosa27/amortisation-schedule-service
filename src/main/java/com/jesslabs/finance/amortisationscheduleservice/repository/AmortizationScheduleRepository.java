package com.jesslabs.finance.amortisationscheduleservice.repository;

import com.jesslabs.finance.amortisationscheduleservice.model.AmortizationSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmortizationScheduleRepository extends JpaRepository<AmortizationSchedule, Long> {
}
