package com.myloanz.partnership.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.validation.annotation.Validated;
import java.time.LocalDate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;

public class SubmitLoanRequest {
    @Range(min = 100, max = 99999)
    private int principalAmount;
    @Range(min = 6, max = 24)
    private int termMonths;
    @Valid
    private Collateral collateral;
    @Valid
    private Customer customer;

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

    public Collateral getCollateral() {
        return collateral;
    }

    public void setCollateral(Collateral collateral) {
        this.collateral = collateral;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public class Collateral {
        @Pattern(regexp = "TOYOTA|HONDA|BMW|FORD", flags = Pattern.Flag.CASE_INSENSITIVE)
        private String brand;

        @Size(max = 50)
        @NotBlank
        private String model;

        @Min(2015)
        private int manufacturingYear;

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public int getManufacturingYear() {
            return manufacturingYear;
        }

        public void setManufacturingYear(int manufacturingYear) {
            this.manufacturingYear = manufacturingYear;
        }
    }

    public class Customer {

        @Pattern(regexp = "^[A-Za-z ]{3,50}$")
        private String name;

        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate birthDate;

        @Min(500)
        private int monthlyIncome;

        @Size(max = 50)
        @NotBlank
        private String idNumber;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LocalDate getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
        }

        public int getMonthlyIncome() {
            return monthlyIncome;
        }

        public void setMonthlyIncome(int monthlyIncome) {
            this.monthlyIncome = monthlyIncome;
        }

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }
    }
}
