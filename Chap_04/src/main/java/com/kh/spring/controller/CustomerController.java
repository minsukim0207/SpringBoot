package com.kh.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.springdb.service.CustomerService;
import com.kh.springdb.vo.Customer;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	private final CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping
	public String customerList(Model model) {
		model.addAttribute("customers", customerService.getAllCustomers());
		return "customerList";
	}
	
	@GetMapping("/new")
	public String customerForm(Model model) {
		model.addAttribute("customer", new Customer());
		return "customerForm";
	}
	
	@PostMapping("/new")
	public String saveCustomer(@ModelAttribute Customer customer) {
		customerService.saveCustomer(customer);
		return "redirect:/customers";
	}
}
