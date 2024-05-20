package com.jesslabs.finance.amortisationscheduleservice.repository;

import com.jesslabs.finance.amortisationscheduleservice.model.LoanDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanDetailsRepository extends JpaRepository<LoanDetails, Long> {
}
