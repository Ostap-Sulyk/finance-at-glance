package com.financeatglance.financeatglance.security.manager;

import com.financeatglance.financeatglance.entities.Customer;
import com.financeatglance.financeatglance.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {
    private final CustomerService customerService;
    private final PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Customer customer = customerService.getCustomerByEmail(authentication.getName()).orElse(null);

        if (customer == null || !encoder.matches(authentication.getCredentials().toString(), customer.getPassword())) {
            throw new BadCredentialsException("Username or password is incorrect");
        }

        return new UsernamePasswordAuthenticationToken(customer.getEmail(), customer.getAuthorities(), customer.getAuthorities());
    }
}
