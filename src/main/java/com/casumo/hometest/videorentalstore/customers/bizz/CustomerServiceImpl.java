package com.casumo.hometest.videorentalstore.customers.bizz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casumo.hometest.videorentalstore.customers.domain.Customer;
import com.casumo.hometest.videorentalstore.customers.repo.CustomerRepository;


/**
 * CustomerServiceImpl class.
 */
@Service
public class CustomerServiceImpl implements CustomerService
{
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(final CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll()
    {
        return customerRepository.findAll();
    }

    @Override
    public Customer findBy(final long id)
    {
        return customerRepository.findBy(id);
    }

    @Override
    public Customer insert(final Customer customer)
    {
        final long customerId = customerRepository.save(customer);

        return customerRepository.findBy(customerId);
    }
}
