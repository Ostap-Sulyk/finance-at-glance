package com.financeatglance.financeatglance.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordStrengthValidator.class)
public @interface StrongPassword {
    String message() default "Password cannot be blank";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

