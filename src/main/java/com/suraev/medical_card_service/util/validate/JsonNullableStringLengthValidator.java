package com.suraev.medical_card_service.util.validate;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class JsonNullableStringLengthValidator implements ConstraintValidator<LengthNullableStringProperty, String> {
   private int min;
   private int max;
    @Override
    public void initialize(LengthNullableStringProperty constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        int length=value.length();
        return length >= min && length <= max;
    }
}

