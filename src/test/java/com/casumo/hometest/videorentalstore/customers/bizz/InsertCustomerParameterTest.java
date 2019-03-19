package com.casumo.hometest.videorentalstore.customers.bizz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.customers.CustomerTestHelper;


/**
 * InsertCustomerParameterTest class - InsertCustomerParameter test class.
 */
class InsertCustomerParameterTest
{
    // equals ok
    @Test
    void givenTwoEqualInsertCustomerParameters_whenCheckIfEquals_thenBothParametersMustBeEquals()
    {
        // given
        final InsertCustomerParameter mockParameter1 =
            CustomerTestHelper.generateInsertCustomerParameter(CustomerTestHelper.MOCK_USERNAME1, CustomerTestHelper.MOCK_EMAIL1);
        final InsertCustomerParameter mockParameter2 =
            CustomerTestHelper.generateInsertCustomerParameter(CustomerTestHelper.MOCK_USERNAME1, CustomerTestHelper.MOCK_EMAIL1);

        // when + then
        assertEquals(mockParameter1.hashCode(), mockParameter2.hashCode());
        assertEquals(mockParameter1, mockParameter2);
    }

    // equals nok
    @Test
    void givenTwoDifferentInsertCustomerParameters_whenCheckIfEquals_thenBothParametersMustNotBeEquals()
    {
        // given
        final InsertCustomerParameter mockParameter1 =
            CustomerTestHelper.generateInsertCustomerParameter(CustomerTestHelper.MOCK_USERNAME1, CustomerTestHelper.MOCK_EMAIL1);
        final InsertCustomerParameter mockParameter2 =
            CustomerTestHelper.generateInsertCustomerParameter(CustomerTestHelper.MOCK_USERNAME1, CustomerTestHelper.MOCK_EMAIL2);

        // when + then
        assertNotEquals(mockParameter1.hashCode(), mockParameter2.hashCode());
        assertNotEquals(mockParameter1, mockParameter2);
    }

    // toString
    @Test
    void givenInsertCustomerParameter_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final InsertCustomerParameter mockParameter = CustomerTestHelper.MOCK_INSERT_PARAMETER1;
        final String expected = "InsertCustomerParameter{" +
            "username='" + mockParameter.getUsername() + '\'' +
            ", email='" + mockParameter.getEmail() + '\'' +
            '}';

        // when
        final String result = mockParameter.toString();

        // then
        assertEquals(expected, result);

    }
}
