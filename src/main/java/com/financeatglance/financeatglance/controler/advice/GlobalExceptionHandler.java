package com.financeatglance.financeatglance.controler.advice;

import com.financeatglance.financeatglance.exceptions.EmailAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<Object> handleEmailAlreadyExistsException(EmailAlreadyExistException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("email", ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
