package com.casumo.hometest.videorentalstore.customers.rest.mapper;

import com.casumo.hometest.videorentalstore.customers.domain.Customer;
import com.casumo.hometest.videorentalstore.customers.rest.CustomerRest;


/**
 * CustomerRestMapper class.
 */
public class CustomerRestMapper
{
    public static CustomerRest map(final Customer customer)
    {
        if (customer != null)
        {
            final CustomerRest customerRest = new CustomerRest();
            customerRest.setId(customer.getId());
            customerRest.setUsername(customer.getUsername());
            customerRest.setEmail(customer.getEmail());
            customerRest.setBonuspoints(customer.getBonuspoints());
            return customerRest;
        }
        return null;
    }
}
