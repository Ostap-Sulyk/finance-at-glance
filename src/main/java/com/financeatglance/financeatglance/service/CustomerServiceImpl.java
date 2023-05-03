package com.financeatglance.financeatglance.service;

import com.financeatglance.financeatglance.entities.Customer;
import com.financeatglance.financeatglance.exceptions.EmailAlreadyExistException;
import com.financeatglance.financeatglance.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import static com.financeatglance.financeatglance.constants.ValidationMessages.EMAIL_ALREADY_EXIST;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        if (customerRepository.findCustomerByEmail(customer.getEmail()).isPresent()) {
            throw new EmailAlreadyExistException(EMAIL_ALREADY_EXIST);
        } else {
            customer.setDividends(new ArrayList<>());
            return customerRepository.save(customer);
        }
    }

    @Override
    public Optional<Customer> getCustomer(String id) {
        return customerRepository.findById(id);
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
