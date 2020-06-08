package com.dharmendra.hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dharmendra.hotel.dao.CustomerDAO;
import com.dharmendra.hotel.entity.Customer;

/**
 * @author Dharmendra
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDAO customerDAO;

	@Override
	public List<Customer> findAll() {

		return customerDAO.findAll();
	}

	@Override
	public Customer findById(long id) {

		return customerDAO.findById(id);
	}

	@Override
	public void save(Customer customer) {
		customerDAO.save(customer);

	}

	@Override
	public void deleteById(long id) {
		customerDAO.deleteById(id);

	}

}
