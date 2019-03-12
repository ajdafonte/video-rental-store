package com.casumo.hometest.videorentalstore.customers.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.customers.CustomerTestHelper;


/**
 * InsertCustomerRequestBodyTest class - InsertCustomerRequestBody test class.
 */
class InsertCustomerRequestBodyTest
{
    // equals ok
    @Test
    void givenTwoEqualInsertCustomerRequestBodies_whenCheckIfEquals_thenBothRequestBodiesMustBeEquals()
    {
        // given
        final InsertCustomerRequestBody mockRequestBody1 =
            CustomerTestHelper.generateInsertCustomerRequestBody(CustomerTestHelper.MOCK_USERNAME1, CustomerTestHelper.MOCK_EMAIL1);
        final InsertCustomerRequestBody mockRequestBody2 =
            CustomerTestHelper.generateInsertCustomerRequestBody(CustomerTestHelper.MOCK_USERNAME1, CustomerTestHelper.MOCK_EMAIL1);

        // when + then
        assertEquals(mockRequestBody1.hashCode(), mockRequestBody2.hashCode());
        assertEquals(mockRequestBody1, mockRequestBody2);
    }

    // equals nok
    @Test
    void givenTwoDifferentInsertCustomerRequestBodies_whenCheckIfEquals_thenBothRequestBodiesMustNotBeEquals()
    {
        // given
        final InsertCustomerRequestBody mockRequestBody1 =
            CustomerTestHelper.generateInsertCustomerRequestBody(CustomerTestHelper.MOCK_USERNAME1, CustomerTestHelper.MOCK_EMAIL1);
        final InsertCustomerRequestBody mockRequestBody2 =
            CustomerTestHelper.generateInsertCustomerRequestBody(CustomerTestHelper.MOCK_USERNAME2, CustomerTestHelper.MOCK_EMAIL1);

        // when + then
        assertNotEquals(mockRequestBody1.hashCode(), mockRequestBody2.hashCode());
        assertNotEquals(mockRequestBody1, mockRequestBody2);
    }

    // toString
    @Test
    void givenInsertCustomerRequestBody_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final InsertCustomerRequestBody mockRequestBody =
            CustomerTestHelper.generateInsertCustomerRequestBody(CustomerTestHelper.MOCK_USERNAME2, CustomerTestHelper.MOCK_EMAIL2);
        final String expected = "InsertCustomerRequestBody{" +
            "username='" + CustomerTestHelper.MOCK_USERNAME2 + '\'' +
            ", email='" + CustomerTestHelper.MOCK_EMAIL2 + '\'' +
            '}';

        // when
        final String result = mockRequestBody.toString();

        // then
        assertEquals(expected, result);

    }
}
