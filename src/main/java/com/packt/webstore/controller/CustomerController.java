package com.packt.webstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.service.CustomerService;

@Controller
@RequestMapping("market")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	@RequestMapping("/customers")
	public String list(Model model) {
		List<Customer> customers = customerService.getAllCustomers();
		model.addAttribute("customers", customers);
		return "customers";
	}
	
	@RequestMapping("/customers2")
	public String list2(Model model) {
		model.addAttribute("customers", customerService.getAllCustomers2());
		return "customers2";
	}
	
	@RequestMapping(value="/customers/add", method=RequestMethod.GET)
	public String getNewCustomerFrom(
			@ModelAttribute("newCustomer") Customer newCustomer) {
		return "addCustomer";
	}	
	
	@RequestMapping(value="/customers/add", method=RequestMethod.POST)
	public String processAddNewCustomer(
			@ModelAttribute("newCustomer") Customer newCustomer,
			BindingResult result) {
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException(
					"Attempting to bind disallowed fields: "
							+ StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		customerService.addCustomer(newCustomer);
		return "redirect:/market/customers";
	}	
}
