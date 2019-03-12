package com.casumo.hometest.videorentalstore.customers.repo;

import java.util.List;

import com.casumo.hometest.videorentalstore.customers.domain.Customer;


/**
 * CustomerRepository interface.
 */
public interface CustomerRepository
{
    /**
     * @return
     */
    List<Customer> findAll();

    /**
     * @param id
     * @return
     */
    Customer findBy(long id);

    /**
     * @param customer
     * @return
     */
    long save(Customer customer);
}
