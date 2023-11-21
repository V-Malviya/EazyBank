package com.vaibhav.eazybank.controller;

import com.vaibhav.eazybank.model.Customer;
import com.vaibhav.eazybank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {
    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SignUpController(CustomerRepository customerRepository,PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder=passwordEncoder;
    }
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Customer customer)
    {
        if(customer.getEmail()==null || customer.getEmail().length()==0)
        {
            return new ResponseEntity<>("Invalid email provided",HttpStatus.NOT_ACCEPTABLE);
        }
        customer.setPwd(passwordEncoder.encode(customer.getPwd()));
        Customer savedCustomer =customerRepository.save(customer);
        if(savedCustomer==null)
        {
            return new ResponseEntity<>("Something wrong",HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("Successfully Added wit id: "+savedCustomer.getId(),HttpStatus.OK);
    }
}
