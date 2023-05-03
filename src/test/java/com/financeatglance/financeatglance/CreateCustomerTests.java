package com.financeatglance.financeatglance;


import com.financeatglance.financeatglance.entities.Customer;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.financeatglance.financeatglance.constants.ValidationMessages.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateCustomerTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void rejectEmptyFields() {
        Customer blackCustomer = new Customer();
        ResponseEntity<String> response = restTemplate.postForEntity("/", blackCustomer, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        DocumentContext documentContext = JsonPath.parse(response.getBody());

        String firstNameError = documentContext.read("$.firstName");
        String lastNameError = documentContext.read("$.lastName");
        String emailError = documentContext.read("$.email");
        String passwordError = documentContext.read("$.password");

        assertThat(firstNameError).isEqualTo(BLANK_FIELD);
        assertThat(lastNameError).isEqualTo(BLANK_FIELD);
        assertThat(emailError).isEqualTo(BLANK_FIELD);
        assertThat(passwordError).isEqualTo(BLANK_FIELD);

    }

    @Test
    void rejectIncorrectEmail() {
        Customer customerWithInvalidEmail = new Customer();
        customerWithInvalidEmail.setEmail("john.doe");

        ResponseEntity<String> response = restTemplate.postForEntity("/", customerWithInvalidEmail, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        DocumentContext documentContext = JsonPath.parse(response.getBody());

        String emailError = documentContext.read("$.email");
        assertThat(emailError).isEqualTo(INVALID_EMAIL);

    }

    @Test
    void rejectShortPassword() {
        Customer customer = new Customer();
        customer.setPassword("");

        ResponseEntity<String> response = restTemplate.postForEntity("/", customer, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        String password = documentContext.read("$.password");
        assertThat(password).isEqualTo(PASSWORD_TOO_SHORT);

    }

    @Test
    void rejectPasswordsWithNoLowerCaseLetters() {
        Customer customer = new Customer();
        customer.setPassword("        ");

        ResponseEntity<String> response = restTemplate.postForEntity("/", customer, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        String password = documentContext.read("$.password");
        assertThat(password).isEqualTo(THREE_LOWERCASE_LETTERS);
    }

    @Test
    void rejectPasswordsWithNoUpperCaseLetters() {
        Customer customer = new Customer();
        customer.setPassword("asdfghjkl");

        ResponseEntity<String> response = restTemplate.postForEntity("/", customer, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        String password = documentContext.read("$.password");
        assertThat(password).isEqualTo(TWO_UPPERCASE_LETTERS);
    }

    @Test
    void rejectPasswordsWithNoDigits() {
        Customer customer = new Customer();
        customer.setPassword("asdfghjAA");

        ResponseEntity<String> response = restTemplate.postForEntity("/", customer, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        String password = documentContext.read("$.password");
        assertThat(password).isEqualTo(TWO_DIGITS);
    }

    @Test
    void rejectPasswordsWithNoSpecialCharacters() {
        Customer customer = new Customer();
        customer.setPassword("asdfghjklAA99");

        ResponseEntity<String> response = restTemplate.postForEntity("/", customer, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        String password = documentContext.read("$.password");
        assertThat(password).isEqualTo(ONE_SPECIAL_CHARACTER);
    }


}
