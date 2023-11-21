package com.vaibhav.eazybank.repository;

import com.vaibhav.eazybank.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> findCustomerByEmail(String email);
}
