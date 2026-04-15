package com.myloanz.partnership.exception;

import com.myloanz.partnership.api.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({ArithmeticException.class})
    public ResponseEntity<ExceptionResponse> handleArithmeticException(ArithmeticException e) {
        var response = new ExceptionResponse();
        response.setSummary("Got an Arithmetic Exception");
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        var response = new ExceptionResponse();
        response.setSummary("Cannot read HTTP request body. Check whether the request body is valid JSON format");
        response.setMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {
        var response = new ExceptionResponse();
        response.setSummary("Got an Exception");
        response.setMessage(e.getClass().toString() + ": " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler({LoanBussinessException.class})
    ResponseEntity<ExceptionResponse> handleLoanBussinessException(LoanBussinessException e) {
        var response = new ExceptionResponse();
        response.setSummary("Got an LoanBussinessException");
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({LoanOwnerException.class})
    ResponseEntity<ExceptionResponse> handleLoanOwnerException(LoanOwnerException e) {
        var response = new ExceptionResponse();
        response.setSummary("Got an LoanOwnerException");
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }
}
