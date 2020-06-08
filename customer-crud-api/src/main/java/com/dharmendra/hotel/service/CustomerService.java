package com.dharmendra.hotel.service;

import java.util.List;
import com.dharmendra.hotel.entity.Customer;


/**
 * @author Dharmendra
 *
 */
public interface CustomerService {
	
	public List<Customer> findAll();

	public Customer findById(long id);

	public void save(Customer customer);

	public void deleteById(long id);
}
