package com.financeatglance.financeatglance.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.financeatglance.financeatglance.constants.ValidationMessages.*;

public class PasswordStrengthValidator implements ConstraintValidator<StrongPassword, String> {
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z].*[A-Z])(?=.*[@#$%^&+=!])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z]).{8,}$";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);

        if (!matcher.matches()) {
            context.disableDefaultConstraintViolation();
            if (password.length() < 8)
                context.buildConstraintViolationWithTemplate(PASSWORD_TOO_SHORT).addConstraintViolation();
            else if (!password.matches("^(?=.*[a-z].*[a-z].*[a-z]).*$"))
                context.buildConstraintViolationWithTemplate(THREE_LOWERCASE_LETTERS).addConstraintViolation();
            else if (!password.matches("^(?=.*[A-Z].*[A-Z]).*$"))
                context.buildConstraintViolationWithTemplate(TWO_UPPERCASE_LETTERS).addConstraintViolation();
            else if (!password.matches("^(?=.*[0-9].*[0-9]).*$"))
                context.buildConstraintViolationWithTemplate(TWO_DIGITS).addConstraintViolation();
            else if (!password.matches("^(?=.*[@#$%^&+=!]).*$"))
                context.buildConstraintViolationWithTemplate(ONE_SPECIAL_CHARACTER).addConstraintViolation();

            return false;
        }
        return true;
    }
}

