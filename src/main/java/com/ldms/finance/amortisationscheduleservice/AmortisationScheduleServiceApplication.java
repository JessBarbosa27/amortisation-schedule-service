package com.ldms.finance.amortisationscheduleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AmortisationScheduleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmortisationScheduleServiceApplication.class, args);
	}

}
