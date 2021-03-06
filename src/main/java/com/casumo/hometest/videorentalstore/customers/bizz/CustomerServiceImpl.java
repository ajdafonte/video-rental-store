package com.casumo.hometest.videorentalstore.customers.bizz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.casumo.hometest.videorentalstore.common.error.VideoRentalStoreApiError;
import com.casumo.hometest.videorentalstore.common.error.VideoRentalStoreApiException;
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
        return customerRepository.findById(id)
            .orElseThrow(() -> new VideoRentalStoreApiException(VideoRentalStoreApiError.UNKNOWN_RESOURCE, "Customer was not found."));
    }

    @Override
    public Customer insert(final InsertCustomerParameter parameter)
    {
        try
        {
            final Customer customerToInsert = new Customer();
            customerToInsert.setUsername(parameter.getUsername());
            customerToInsert.setEmail(parameter.getEmail());
            return customerRepository.save(customerToInsert);
        }
        catch (final DataAccessException e)
        {
            throw new VideoRentalStoreApiException(VideoRentalStoreApiError.RESOURCE_ALREADY_EXISTS,
                "Already exists a customer with name: " + parameter.getUsername());
        }
    }
}
