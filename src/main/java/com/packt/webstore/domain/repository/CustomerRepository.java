package com.packt.webstore.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Customer;

public interface CustomerRepository {
	List<Customer> getAllCustomers();
	List<Customer> getAllCustomers2();
	void addCustomer(Customer customer);
	
	Customer getCustomer(String customerId);
	Boolean isCustomerExist(String customerId);
}
