package com.vaibhav.eazybank.configuration;

import com.vaibhav.eazybank.model.Customer;
import com.vaibhav.eazybank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EazyBankUserDetail implements UserDetailsService {
    private CustomerRepository customerRepository;
    @Autowired
    public EazyBankUserDetail(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Customer> fetchedCustomers=customerRepository.findCustomerByEmail(username);
        if (fetchedCustomers.isEmpty())
        {
            throw new UsernameNotFoundException("There is no user available with name "+username);
        }
        List<GrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(fetchedCustomers.get(0).getAuthorities()));
        return new User(fetchedCustomers.get(0).getEmail(),fetchedCustomers.get(0).getPwd(),authorities);
    }
}
