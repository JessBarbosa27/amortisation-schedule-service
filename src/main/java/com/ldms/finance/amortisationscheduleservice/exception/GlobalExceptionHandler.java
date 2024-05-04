package com.ldms.finance.amortisationscheduleservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {
        ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<Object> handleConflictException(InternalServerException ex) {
        ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage().isEmpty() ? "Something went wrong, please try again" : ex.getMessage());
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
