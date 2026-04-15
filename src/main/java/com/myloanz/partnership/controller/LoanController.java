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

@RestController
public class LoanController {

    private static final String HTTP_HEADER_PARTNER_SECRET = "partner-secret";

    @Autowired
    private LoanService loanService;

    @PostMapping(value = "/api/loan", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SubmitLoanResponse> submitLoan(
            @RequestBody SubmitLoanRequest loanRequest,
            @RequestHeader(name = HTTP_HEADER_PARTNER_SECRET, required = true) String partnerSecret
    ) {
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

        return ResponseEntity.ok().body(existingLoan);
    }

}
