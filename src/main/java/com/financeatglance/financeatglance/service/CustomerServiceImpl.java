package com.financeatglance.financeatglance.service;

import com.financeatglance.financeatglance.entities.Customer;
import com.financeatglance.financeatglance.exceptions.EmailAlreadyExistException;
import com.financeatglance.financeatglance.pojos.Role;
import com.financeatglance.financeatglance.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import static com.financeatglance.financeatglance.constants.ValidationMessages.EMAIL_ALREADY_EXIST;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder encoder;

    @Override
    public Customer createCustomer(Customer customer) {
        if (customerRepository.findCustomerByEmail(customer.getEmail()).isPresent()) {
            throw new EmailAlreadyExistException(EMAIL_ALREADY_EXIST);
        } else {
            customer.setPassword(encoder.encode(customer.getPassword()));
            customer.setDividends(new ArrayList<>());
            customer.setRole(Role.USER);

            return customerRepository.save(customer);
        }
    }

    @Override
    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
        return  customerRepository.findCustomerByEmail(email);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }
}
