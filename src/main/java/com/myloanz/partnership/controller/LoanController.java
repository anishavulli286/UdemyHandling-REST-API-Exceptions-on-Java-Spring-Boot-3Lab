package com.myloanz.partnership.controller;

import com.myloanz.partnership.api.request.SubmitLoanRequest;
import com.myloanz.partnership.api.response.SubmitLoanResponse;
import com.myloanz.partnership.entity.Loan;
import com.myloanz.partnership.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.myloanz.partnership.exception.LoanBussinessException;
import java.time.LocalDate;
import java.time.Period;
import com.myloanz.partnership.exception.LoanOwnerException;
import jakarta.validation.Valid;

@RestController
public class LoanController {

    private static final String HTTP_HEADER_PARTNER_SECRET = "partner-secret";

    @Autowired
    private LoanService loanService;

    @PostMapping(value = "/api/loan", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SubmitLoanResponse> submitLoan(
            @RequestBody@Valid SubmitLoanRequest loanRequest,
            @RequestHeader(name = HTTP_HEADER_PARTNER_SECRET, required = true) String partnerSecret
    ) {
//        if (loanRequest.getPrincipalAmount() < 100 || loanRequest.getPrincipalAmount() > 99999) {
//            throw new LoanBussinessException("Loan principal amount must be between 100 and 9999: " + loanRequest.getPrincipalAmount());
//        }
//        var age = Period.between(loanRequest.getCustomer().getBirthDate(), LocalDate.now()).getYears();
//        if (age < 18 || age > 70) {
//            throw new LoanBussinessException("Loan age must be between 18 and 70 years");
//        }
        var savedLoan = loanService.saveLoanToDatabase(loanRequest, partnerSecret);

        var submitLoanResponse = new SubmitLoanResponse();

        submitLoanResponse.setLoanId(savedLoan.getLoanId());
        submitLoanResponse.setCustomerName(savedLoan.getCustomerName());
        submitLoanResponse.setStatus(savedLoan.getStatus());

        return ResponseEntity.status(HttpStatus.CREATED).body(submitLoanResponse);
    }

    @GetMapping(value = "/api/loan", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Loan> findLoan(@RequestParam(name = "loan_id", required = true) String loanId,
                                  @RequestHeader(name = HTTP_HEADER_PARTNER_SECRET, required = true) String partnerSecret) {
        var existingLoan = loanService.findLoan(loanId, partnerSecret);
        if (existingLoan == null) {
            if (loanService.isLoanIdExist(loanId)) {
                throw new LoanOwnerException("You cannot access loan with this id: " + loanId);
            } else {
                throw new LoanBussinessException("Loan: " + loanId + " does not exist");
            }
        }

        return ResponseEntity.ok().body(existingLoan);
    }

}
