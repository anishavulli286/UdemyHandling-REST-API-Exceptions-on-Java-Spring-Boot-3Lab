package com.myloanz.partnership.exception;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;


import java.time.LocalDate;
import java.time.Period;

public class AgeValidator implements ConstraintValidator<Age, LocalDate> {

    private int minAge;
    private int maxAge;

    @Override
    public void initialize(Age constraintAnnotation) {
        this.minAge = constraintAnnotation.minAge();
        this.maxAge = constraintAnnotation.maxAge();
    }

    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {
        var age = Period.between(birthDate, LocalDate.now()).getYears();

        if (age < this.minAge || age > this.maxAge) {
            HibernateConstraintValidatorContext hibernateContext =
                    context.unwrap(HibernateConstraintValidatorContext.class);

            hibernateContext.disableDefaultConstraintViolation();

            hibernateContext
                    .addMessageParameter("currentAge", age)
                    .addMessageParameter("minAge", minAge)
                    .addMessageParameter("maxAge", maxAge)
                    .buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addConstraintViolation();

            return false;
        }

        return true;
    }
}
