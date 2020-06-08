package com.dharmendra.hotel.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dharmendra.hotel.entity.Customer;

/**
 * @author Dharmendra
 *
 */
@Repository
@Transactional
public class CustomerDAOImpl implements CustomerDAO {
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Customer> findAll() {
		// create a query
		Query theQuery = entityManager.createQuery("from Customer");

		// execute query and get result list
		List<Customer> customer = theQuery.getResultList();

		// return the results
		return customer;
	}

	@Override
	public Customer findById(long id) {
		// get employee
		Customer theCustomer = entityManager.getReference(Customer.class, id);

		// return employee
		return theCustomer;
	}

	@Override
	public void save(Customer customer) {
		// save or update the employee
		Customer dbCustomer = entityManager.merge(customer);

		// update with id from db ... so we can get generated id for save/insert
		customer.setId(dbCustomer.getId());
	}

	@Override
	public void deleteById(long id) {
		// delete object with primary key
		Query theQuery = entityManager.createQuery("delete from Customer where id=:customerId");

		theQuery.setParameter("customerId", id);

		theQuery.executeUpdate();

	}

}
