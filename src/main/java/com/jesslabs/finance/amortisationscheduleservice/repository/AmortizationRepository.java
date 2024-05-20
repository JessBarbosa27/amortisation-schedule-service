package com.jesslabs.finance.amortisationscheduleservice.repository;

import com.jesslabs.finance.amortisationscheduleservice.model.Amortization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmortizationRepository extends JpaRepository<Amortization, Long> {

    List<Amortization> findAmortizationsByAmortizationSchedule_Id(Long amortizationScheduleId);

}
