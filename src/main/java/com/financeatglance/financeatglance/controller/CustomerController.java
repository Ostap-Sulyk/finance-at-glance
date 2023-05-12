package com.financeatglance.financeatglance.controller;

import com.financeatglance.financeatglance.entities.Customer;
import com.financeatglance.financeatglance.entities.Dividend;
import com.financeatglance.financeatglance.service.CustomerService;
import com.financeatglance.financeatglance.service.DividendService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

//TODO: refactor every method in controller to handle any logic in service
@RestController
@RequestMapping("")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final DividendService dividendService;

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer newCustomer, UriComponentsBuilder ucb) {
        Customer savedCustomer = customerService.createCustomer(newCustomer);
        URI locationOfNewCustomer = ucb.path("/{id}").buildAndExpand(savedCustomer.getId()).toUri();

        return ResponseEntity.created(locationOfNewCustomer).build();
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer newCustomer, UriComponentsBuilder ucb) {
        Customer savedCustomer = customerService.createCustomer(newCustomer);
        URI locationOfNewCustomer = ucb.path("/{id}").buildAndExpand(savedCustomer.getId()).toUri();
        return ResponseEntity.created(locationOfNewCustomer).body(savedCustomer);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String id) {
        return customerService.getCustomer(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{email}")
    public ResponseEntity<Dividend> addDividend(@PathVariable String email, @RequestBody @Valid Dividend dividend, UriComponentsBuilder ucb) {
        Dividend savedDividend = dividendService.saveDividend(dividend, email);
        URI uri = ucb.path("/{id}").buildAndExpand(savedDividend.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
