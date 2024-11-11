package com.suraev.medical_card_service.util.validate;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = JsonNullableStringLengthValidator.class)
@Target({ ElementType.METHOD,ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface LengthNullableStringProperty {
        String message() default "Invalid length";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
        int min() default 0;
        int max() default Integer.MAX_VALUE;
    }

