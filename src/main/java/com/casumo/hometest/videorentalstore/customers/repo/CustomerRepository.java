package com.casumo.hometest.videorentalstore.customers.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casumo.hometest.videorentalstore.customers.domain.Customer;


/**
 * CustomerRepository interface.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>
{

}
