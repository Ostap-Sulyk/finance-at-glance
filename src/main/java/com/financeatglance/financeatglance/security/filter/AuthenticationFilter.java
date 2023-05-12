package com.financeatglance.financeatglance.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financeatglance.financeatglance.entities.Customer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.rmi.server.ExportException;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Customer customer = new ObjectMapper().readValue(request.getInputStream(), Customer.class);
            System.out.println(customer.getEmail());
            System.out.println(customer.getPassword());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return super.attemptAuthentication(request, response);
    }
}