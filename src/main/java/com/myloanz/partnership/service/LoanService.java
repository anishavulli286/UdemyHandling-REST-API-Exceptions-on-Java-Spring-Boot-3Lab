package com.myloanz.partnership.service;

import com.myloanz.partnership.api.request.SubmitLoanRequest;
import com.myloanz.partnership.entity.Loan;
import com.myloanz.partnership.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoanService {

    @Autowired
    private LoanRepository repository;

    public Loan saveLoanToDatabase(SubmitLoanRequest loanRequest, String partnerSecret) {
        var loanInterest = (int) Math.ceil(loanRequest.getPrincipalAmount() * loanRequest.getTermMonths() * 0.01);
        var monthlyInstallment =
                (int) Math.ceil((loanRequest.getPrincipalAmount() + loanInterest) / loanRequest.getTermMonths());

        var loanEntity = map(loanRequest);

        loanEntity.setLoanInterest(loanInterest);
        loanEntity.setMonthlyInstallment(monthlyInstallment);
        loanEntity.setCreatedBy(partnerSecret);
        loanEntity.setStatus("PENDING");

        return repository.save(loanEntity);
    }

    public Loan findLoan(String loanId, String partnerSecret) {
        return repository.findByLoanIdAndCreatedBy(UUID.fromString(loanId), partnerSecret)
                .orElse(null);
    }

    private Loan map(SubmitLoanRequest original) {
        var result = new Loan();

        result.setPrincipalAmount(original.getPrincipalAmount());
        result.setTermMonths(original.getTermMonths());

        result.setCollateralBrand(original.getCollateral().getBrand());
        result.setCollateralManufacturingYear(original.getCollateral().getManufacturingYear());
        result.setCollateralModel(original.getCollateral().getModel());

        result.setCustomerBirthDate(original.getCustomer().getBirthDate());
        result.setCustomerIdNumber(original.getCustomer().getIdNumber());
        result.setCustomerMonthlyIncome(original.getCustomer().getMonthlyIncome());
        result.setCustomerName(original.getCustomer().getName());

        return result;
    }

}
