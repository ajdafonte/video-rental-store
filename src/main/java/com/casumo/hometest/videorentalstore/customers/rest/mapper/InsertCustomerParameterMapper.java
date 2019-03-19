package com.casumo.hometest.videorentalstore.customers.rest.mapper;

import com.casumo.hometest.videorentalstore.customers.bizz.InsertCustomerParameter;
import com.casumo.hometest.videorentalstore.customers.rest.InsertCustomerRequestBody;


/**
 * InsertCustomerParameterMapper class.
 */
public class InsertCustomerParameterMapper
{
    public static InsertCustomerParameter map(final InsertCustomerRequestBody requestBody)
    {
        if (requestBody != null)
        {
            final InsertCustomerParameter customer = new InsertCustomerParameter();
            customer.setUsername(requestBody.getUsername());
            customer.setEmail(requestBody.getEmail());
            return customer;
        }
        return null;
    }
}
