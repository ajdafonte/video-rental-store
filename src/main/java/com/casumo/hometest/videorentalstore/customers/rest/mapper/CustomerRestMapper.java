package com.casumo.hometest.videorentalstore.customers.rest.mapper;

import com.casumo.hometest.videorentalstore.customers.domain.Customer;
import com.casumo.hometest.videorentalstore.customers.rest.CustomerRest;
import com.casumo.hometest.videorentalstore.customers.rest.InsertCustomerRequestBody;


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

    public static Customer mapToBizz(final InsertCustomerRequestBody parameterRest)
    {
        if (parameterRest != null)
        {
            final Customer customer = new Customer();
            customer.setUsername(parameterRest.getUsername());
            customer.setEmail(parameterRest.getEmail());
            return customer;
        }
        return null;
    }
}
