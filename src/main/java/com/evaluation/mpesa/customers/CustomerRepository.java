package com.evaluation.mpesa.customers;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    Optional<Customer> findCustomerByEmail(String email);
    Optional<Customer> findCustomerByPhone(String phone);
}
