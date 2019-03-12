package com.casumo.hometest.videorentalstore.customers;

import com.casumo.hometest.videorentalstore.customers.domain.Customer;
import com.casumo.hometest.videorentalstore.customers.rest.CustomerRest;
import com.casumo.hometest.videorentalstore.customers.rest.InsertCustomerRequestBody;


/**
 * CustomerTestHelper class.
 */
public class CustomerTestHelper
{
    public static final long MOCK_ID1 = 1L;
    public static final long MOCK_ID2 = 2L;
    public static final long MOCK_UNKNOWN_ID = 400L;
    public static final String MOCK_USERNAME1 = "cr7";
    public static final String MOCK_USERNAME2 = "lapulga";
    public static final String MOCK_EMAIL1 = "cr7@email.com";
    public static final String MOCK_EMAIL2 = "lapulga@email.com";
    public static final long MOCK_BONUSPOINTS1 = 4L;
    public static final long MOCK_BONUSPOINTS2 = 3L;
    public static final long MOCK_NEW_CUSTOMER_ID = 3L;
    public static final String MOCK_NEW_CUSTOMER_USERNAME = "elpibe";
    public static final String MOCK_NEW_CUSTOMER_EMAIL = "elpibe@email.com";
    public static final long MOCK_NEW_CUSTOMER_BONUSPOINTS = 0L;

    public static final Customer MOCK_CUSTOMER1;
    public static final CustomerRest MOCK_CUSTOMER_REST1;
    public static final Customer MOCK_CUSTOMER2;
    public static final CustomerRest MOCK_CUSTOMER_REST2;
    public static final Customer MOCK_NEW_CUSTOMER;
    public static final CustomerRest MOCK_NEW_CUSTOMER_REST;
    public static final InsertCustomerRequestBody MOCK_INSERT_REQ_BODY1;

    static
    {
        MOCK_CUSTOMER1 = generateCustomer(MOCK_ID1, MOCK_USERNAME1, MOCK_EMAIL1, MOCK_BONUSPOINTS1);
        MOCK_CUSTOMER_REST1 = generateCustomerRest(MOCK_ID1, MOCK_USERNAME1, MOCK_EMAIL1, MOCK_BONUSPOINTS1);
        MOCK_CUSTOMER2 = generateCustomer(MOCK_ID2, MOCK_USERNAME2, MOCK_EMAIL2, MOCK_BONUSPOINTS2);
        MOCK_CUSTOMER_REST2 = generateCustomerRest(MOCK_ID2, MOCK_USERNAME2, MOCK_EMAIL2, MOCK_BONUSPOINTS2);

        MOCK_NEW_CUSTOMER =
            generateCustomer(MOCK_NEW_CUSTOMER_ID, MOCK_NEW_CUSTOMER_USERNAME, MOCK_NEW_CUSTOMER_EMAIL, MOCK_NEW_CUSTOMER_BONUSPOINTS);
        MOCK_NEW_CUSTOMER_REST =
            generateCustomerRest(MOCK_NEW_CUSTOMER_ID, MOCK_NEW_CUSTOMER_USERNAME, MOCK_NEW_CUSTOMER_EMAIL, MOCK_NEW_CUSTOMER_BONUSPOINTS);

        MOCK_INSERT_REQ_BODY1 = generateInsertCustomerRequestBody(MOCK_NEW_CUSTOMER_USERNAME, MOCK_NEW_CUSTOMER_EMAIL);
    }

    public static Customer generateCustomer(final long id, final String username, final String email, final long bonuspoints)
    {
        final Customer customer = new Customer();
        customer.setId(id);
        customer.setUsername(username);
        customer.setEmail(email);
        customer.setBonuspoints(bonuspoints);
        return customer;
    }

    public static CustomerRest generateCustomerRest(final long id, final String username, final String email, final long bonuspoints)
    {
        final CustomerRest customerRest = new CustomerRest();
        customerRest.setId(id);
        customerRest.setUsername(username);
        customerRest.setEmail(email);
        customerRest.setBonuspoints(bonuspoints);
        return customerRest;
    }

    public static InsertCustomerRequestBody generateInsertCustomerRequestBody(final String username, final String email)
    {
        final InsertCustomerRequestBody requestBody = new InsertCustomerRequestBody();
        requestBody.setUsername(username);
        requestBody.setEmail(email);
        return requestBody;
    }

    public static InsertCustomerRequestBody generateInsertCustomerRequestBody(final String username)
    {
        final InsertCustomerRequestBody requestBody = new InsertCustomerRequestBody();
        requestBody.setUsername(username);
        return requestBody;
    }

    public static Customer generateCustomer(final String username, final String email)
    {
        final Customer customer = new Customer();
        customer.setUsername(username);
        customer.setEmail(email);
        return customer;
    }

}
