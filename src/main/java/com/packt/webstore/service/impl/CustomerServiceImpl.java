package com.packt.webstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.CustomerRepository;
import com.packt.webstore.domain.repository.impl.InMemoryOrderRepository;
import com.packt.webstore.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	InMemoryOrderRepository inMemoryOrderRepository;
	
	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.getAllCustomers();
	}
	
	@Override
	public List<Customer> getAllCustomers2() {
		return customerRepository.getAllCustomers2();
	}

	@Override
	public void addCustomer(Customer customer) {
		customerRepository.addCustomer(customer);
	}

	@Override
	public long saveCustomer(Customer customer) {
		return inMemoryOrderRepository.saveCustomer(customer);
	}

	@Override
	public Customer getCustomer(String customerId) {
		Customer customer = customerRepository.getCustomer(customerId);
		return customer;
	}

	@Override
	public Boolean isCustomerExist(String customerId) {
		return customerRepository.isCustomerExist(customerId);
	}
}