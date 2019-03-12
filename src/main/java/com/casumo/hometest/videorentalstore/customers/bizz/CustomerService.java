package com.casumo.hometest.videorentalstore.customers.bizz;

import java.util.List;

import com.casumo.hometest.videorentalstore.customers.domain.Customer;


/**
 * CustomerService interface.
 */
public interface CustomerService
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
    Customer insert(Customer customer);
}
