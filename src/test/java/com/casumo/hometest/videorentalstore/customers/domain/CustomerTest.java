package com.casumo.hometest.videorentalstore.customers.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.customers.CustomerTestHelper;


/**
 * CustomerTest class - Test Customer class.
 */
class CustomerTest
{
    // equals ok
    @Test
    void givenTwoEqualCustomers_whenCheckIfEquals_thenBothCustomersMustBeEquals()
    {
        // given
        final Customer mockCustomer1 = CustomerTestHelper.generateCustomer(CustomerTestHelper.MOCK_ID1,
            CustomerTestHelper.MOCK_USERNAME1,
            CustomerTestHelper.MOCK_EMAIL1,
            CustomerTestHelper.MOCK_BONUSPOINTS1);
        final Customer mockCustomer2 = CustomerTestHelper.generateCustomer(CustomerTestHelper.MOCK_ID1,
            CustomerTestHelper.MOCK_USERNAME1,
            CustomerTestHelper.MOCK_EMAIL1,
            CustomerTestHelper.MOCK_BONUSPOINTS1);

        // when + then
        assertEquals(mockCustomer1.hashCode(), mockCustomer2.hashCode());
        assertEquals(mockCustomer1, mockCustomer2);
    }

    // equals nok
    @Test
    void givenTwoDifferentCustomers_whenCheckIfEquals_thenBothCustomersMustNotBeEquals()
    {
        // given
        final Customer mockCustomer1 = CustomerTestHelper.generateCustomer(CustomerTestHelper.MOCK_ID1,
            CustomerTestHelper.MOCK_USERNAME1,
            CustomerTestHelper.MOCK_EMAIL1,
            CustomerTestHelper.MOCK_BONUSPOINTS1);
        final Customer mockCustomer2 = CustomerTestHelper.generateCustomer(CustomerTestHelper.MOCK_ID1,
            CustomerTestHelper.MOCK_USERNAME2,
            CustomerTestHelper.MOCK_EMAIL2,
            CustomerTestHelper.MOCK_BONUSPOINTS1);

        // when + then
        assertNotEquals(mockCustomer1.hashCode(), mockCustomer2.hashCode());
        assertNotEquals(mockCustomer1, mockCustomer2);
    }

    // toString
    @Test
    void givenCustomer_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final Customer mockCustomer = CustomerTestHelper.generateCustomer(CustomerTestHelper.MOCK_ID2,
            CustomerTestHelper.MOCK_USERNAME2,
            CustomerTestHelper.MOCK_EMAIL2,
            CustomerTestHelper.MOCK_BONUSPOINTS2);
        final String expected = "Customer{" +
            "id=" + CustomerTestHelper.MOCK_ID2 +
            ", username='" + CustomerTestHelper.MOCK_USERNAME2 + '\'' +
            ", email='" + CustomerTestHelper.MOCK_EMAIL2 + '\'' +
            ", bonuspoints=" + CustomerTestHelper.MOCK_BONUSPOINTS2 +
            '}';

        // when
        final String result = mockCustomer.toString();

        // then
        assertEquals(expected, result);

    }
}
