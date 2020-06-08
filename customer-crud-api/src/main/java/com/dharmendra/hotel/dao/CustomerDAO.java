package com.dharmendra.hotel.dao;

import java.util.List;

import com.dharmendra.hotel.entity.Customer;

/**
 * @author Dharmendra
 *
 */
public interface CustomerDAO {
	
	public List<Customer> findAll();

	public Customer findById(long id);

	public void save(Customer customer);

	public void deleteById(long id);
}
