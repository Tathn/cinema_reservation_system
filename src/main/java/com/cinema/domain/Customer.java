package com.cinema.domain;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Patryk on 2017-04-19.
 */
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

	private static final long serialVersionUID = -1006690702888698974L;

	@NotEmpty(message = "The username is required.")
    @Column(name = "username", unique=true, nullable = false)
    private String username;

    @Email(message = "Please provide a valid email address.")
    @NotEmpty(message = "Email is required.")
    @Column(name = "email",unique=true ,nullable = false)
    private String email;

    @NotEmpty(message = "Password is required.")
    private String password;

    public Customer(){}
    
    public Customer(Customer customer){
    	this.setId(customer.getId());
    	this.username = customer.username;
    	this.email = customer.email;
    	this.password = customer.password;
    }

    public String getUsername(){ return this.username; }
    public void setUsername(String username){ this.username = username; }

    public String getEmail(){ return this.email; }
    public void setEmail(String email){ this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { 
    	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(); 
    	this.password = bCryptPasswordEncoder.encode(password); 
    }
}
