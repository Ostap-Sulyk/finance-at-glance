package com.financeatglance.financeatglance.service;

import com.financeatglance.financeatglance.entities.Customer;

import java.util.Optional;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Optional<Customer> getCustomerById(String id);
    Optional<Customer> getCustomerByEmail(String email);
    Customer updateCustomer(Customer customer);
    void deleteCustomer(String id);

}
