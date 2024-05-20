package com.jesslabs.finance.amortisationscheduleservice.controller;

import com.jesslabs.finance.amortisationscheduleservice.dto.AmortizationDTO;
import com.jesslabs.finance.amortisationscheduleservice.dto.AmortizationScheduleDTO;
import com.jesslabs.finance.amortisationscheduleservice.dto.LoanDetailsDTO;
import com.jesslabs.finance.amortisationscheduleservice.exception.InternalServerException;
import com.jesslabs.finance.amortisationscheduleservice.exception.NotFoundException;
import com.jesslabs.finance.amortisationscheduleservice.service.AmortizationScheduleService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/")
@OpenAPIDefinition(info = @Info(title = "Amortization Schedule Controller"))
public class AmortizationScheduleController {

    private final AmortizationScheduleService amortizationScheduleService;

    public AmortizationScheduleController(AmortizationScheduleService amortizationScheduleService) {
        this.amortizationScheduleService = amortizationScheduleService;
    }

    @PostMapping(value = "/amortization-schedule")
    @Operation(description = "Creates a amortization schedule.")
    public ResponseEntity<AmortizationScheduleDTO> getAmortizationSchedules(@RequestBody @Valid LoanDetailsDTO loanDetailsDTO) throws InternalServerException {
        return ResponseEntity.status(HttpStatus.CREATED).body(amortizationScheduleService.createAmortizationSchedule(loanDetailsDTO));
    }

    @GetMapping(value = "/amortization-schedules")
    @Operation(description = "Gets amortization schedules.")
    public ResponseEntity<List<AmortizationScheduleDTO>> getAmortizationSchedules() throws InternalServerException {
        return ResponseEntity.status(HttpStatus.OK).body(amortizationScheduleService.getAmortizationSchedules());
    }

    @GetMapping(value = "/amortizations/{id}")
    @Operation(description = "Gets amortizations by schedule id.")
    public ResponseEntity<List<AmortizationDTO>> getAmortizationsByAmortizationScheduleId(@PathVariable Long id) throws NotFoundException, InternalServerException {
        return ResponseEntity.status(HttpStatus.OK).body(amortizationScheduleService.getAmortizationsByAmortizationScheduleId(id));
    }

}
