package com.cinema.service;

import com.cinema.domain.Customer;
import com.cinema.domain.CustomerRepository;

import java.util.Collection;

import org.springframework.stereotype.Service;

/**
 * Created by Patryk on 2017-04-27.
 */
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }


    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    public void delete(Long id){
        customerRepository.delete(id);
    }

    public Collection<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer findById(Long id){
        return customerRepository.findOne(id);
    }
    
    public Customer findByUsername(String username){
    	return customerRepository.findCustomerByUsername(username);
    }
}
