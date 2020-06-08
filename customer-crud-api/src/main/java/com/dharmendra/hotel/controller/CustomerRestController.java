package com.dharmendra.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dharmendra.hotel.entity.Customer;
import com.dharmendra.hotel.service.CustomerService;

/**
 * @author Dharmendra
 *
 */
@RestController
@RequestMapping("/api")
public class CustomerRestController {
	private CustomerService customerService;

	@Autowired
	public CustomerRestController(CustomerService thecustomerService) {
		customerService = thecustomerService;
	}

	// expose "/Customers" and return list of Customers
	@GetMapping("/customers")
	public List<Customer> findAll() {
		return customerService.findAll();
	}

	// add mapping for GET /Customers/{customers}

	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable long customerId) {

		Customer theCustomer = customerService.findById(customerId);

		if (theCustomer == null) {
			throw new RuntimeException("Customer id not found - " + customerId);
		}

		return theCustomer;
	}

	// add mapping for POST /Customer - add new Customers

	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update

		theCustomer.setId(0);

		customerService.save(theCustomer);

		return theCustomer;
	}

	// add mapping for PUT /Customers - update existing Customers

	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {

		customerService.save(theCustomer);

		return theCustomer;
	}

	// add mapping for DELETE /customers/{customerId} - delete customer

	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable long customerId) {

		Customer tempCustomer = customerService.findById(customerId);

		// throw exception if null

		if (tempCustomer == null) {
			throw new RuntimeException("Customer id not found - " + customerId);
		}

		customerService.deleteById(customerId);

		return "Deleted customer id - " + customerId;
	}
}
