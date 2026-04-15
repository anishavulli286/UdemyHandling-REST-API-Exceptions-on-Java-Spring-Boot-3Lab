package com.myloanz.partnership.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID loanId;

    private int principalAmount;

    private int termMonths;

    @Column(length = 50)
    private String collateralBrand;

    @Column(length = 50)
    private String collateralModel;

    private int collateralManufacturingYear;

    @Column(length = 50)
    private String customerName;

    private LocalDate customerBirthDate;

    private int customerMonthlyIncome;

    @Column(length = 50)
    private String customerIdNumber;

    @Column(length = 50)
    private String createdBy;

    @Column(length = 50)
    private String status;

    private int loanInterest;

    private int monthlyInstallment;

    public UUID getLoanId() {
        return loanId;
    }

    public void setLoanId(UUID loanId) {
        this.loanId = loanId;
    }

    public int getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(int principalAmount) {
        this.principalAmount = principalAmount;
    }

    public int getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(int termMonths) {
        this.termMonths = termMonths;
    }

    public String getCollateralBrand() {
        return collateralBrand;
    }

    public void setCollateralBrand(String collateralBrand) {
        this.collateralBrand = collateralBrand;
    }

    public String getCollateralModel() {
        return collateralModel;
    }

    public void setCollateralModel(String collateralModel) {
        this.collateralModel = collateralModel;
    }

    public int getCollateralManufacturingYear() {
        return collateralManufacturingYear;
    }

    public void setCollateralManufacturingYear(int collateralManufacturingYear) {
        this.collateralManufacturingYear = collateralManufacturingYear;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getCustomerBirthDate() {
        return customerBirthDate;
    }

    public void setCustomerBirthDate(LocalDate customerBirthDate) {
        this.customerBirthDate = customerBirthDate;
    }

    public int getCustomerMonthlyIncome() {
        return customerMonthlyIncome;
    }

    public void setCustomerMonthlyIncome(int customerMonthlyIncome) {
        this.customerMonthlyIncome = customerMonthlyIncome;
    }

    public String getCustomerIdNumber() {
        return customerIdNumber;
    }

    public void setCustomerIdNumber(String customerIdNumber) {
        this.customerIdNumber = customerIdNumber;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLoanInterest() {
        return loanInterest;
    }

    public void setLoanInterest(int loanInterest) {
        this.loanInterest = loanInterest;
    }

    public int getMonthlyInstallment() {
        return monthlyInstallment;
    }

    public void setMonthlyInstallment(int monthlyInstallment) {
        this.monthlyInstallment = monthlyInstallment;
    }
}
