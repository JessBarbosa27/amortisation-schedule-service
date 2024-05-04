package com.ldms.finance.amortisationscheduleservice.repository;

import com.ldms.finance.amortisationscheduleservice.model.LoanDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanDetailsRepository extends JpaRepository<LoanDetails, Long> {
}
