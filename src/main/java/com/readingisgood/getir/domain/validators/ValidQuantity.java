package com.readingisgood.getir.domain.validators;

import com.readingisgood.getir.domain.validators.interfaces.QuantityValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidQuantity implements ConstraintValidator<QuantityValidator, Integer> {

    @Override
    public void initialize(QuantityValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {

        if (value == null) return true;

        return (value < 0);
    }
}
