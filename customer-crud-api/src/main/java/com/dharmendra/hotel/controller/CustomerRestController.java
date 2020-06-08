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

	// expose "/employees" and return list of employees
	@GetMapping("/customers")
	public List<Customer> findAll() {
		return customerService.findAll();
	}

	// add mapping for GET /employees/{employeeId}

	@GetMapping("/customers/{customerId}")
	public Customer getEmployee(@PathVariable long customerId) {

		Customer theCustomer = customerService.findById(customerId);

		if (theCustomer == null) {
			throw new RuntimeException("Customer id not found - " + customerId);
		}

		return theCustomer;
	}

	// add mapping for POST /employees - add new employee

	@PostMapping("/customers")
	public Customer addEmployee(@RequestBody Customer theEmployee) {

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update

		theEmployee.setId(0);

		customerService.save(theEmployee);

		return theEmployee;
	}

	// add mapping for PUT /employees - update existing employee

	@PutMapping("/customers")
	public Customer updateEmployee(@RequestBody Customer theEmployee) {

		customerService.save(theEmployee);

		return theEmployee;
	}

	// add mapping for DELETE /employees/{employeeId} - delete employee

	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable long customerId) {

		Customer tempCustomer = customerService.findById(customerId);

		// throw exception if null

		if (tempCustomer == null) {
			throw new RuntimeException("Employee id not found - " + customerId);
		}

		customerService.deleteById(customerId);

		return "Deleted customer id - " + customerId;
	}
}
