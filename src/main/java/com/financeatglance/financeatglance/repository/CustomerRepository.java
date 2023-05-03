package com.financeatglance.financeatglance.repository;

import com.financeatglance.financeatglance.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    Optional<Customer> findCustomerByEmail(String email);

}
