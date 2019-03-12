package com.casumo.hometest.videorentalstore.customers.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.customers.CustomerTestHelper;


/**
 * CustomerRestTest class - CustomerRest test class.
 */
class CustomerRestTest
{
    // equals ok
    @Test
    void givenTwoEqualCustomerRest_whenCheckIfEquals_thenBothCustomerRestMustBeEquals()
    {
        // given
        final CustomerRest mockCustomerRest1 =
            CustomerTestHelper.generateCustomerRest(CustomerTestHelper.MOCK_ID1,
                CustomerTestHelper.MOCK_USERNAME1,
                CustomerTestHelper.MOCK_EMAIL1,
                CustomerTestHelper.MOCK_BONUSPOINTS1);
        final CustomerRest mockCustomerRest2 =
            CustomerTestHelper.generateCustomerRest(CustomerTestHelper.MOCK_ID1,
                CustomerTestHelper.MOCK_USERNAME1,
                CustomerTestHelper.MOCK_EMAIL1,
                CustomerTestHelper.MOCK_BONUSPOINTS1);

        // when + then
        assertEquals(mockCustomerRest1.hashCode(), mockCustomerRest2.hashCode());
        assertEquals(mockCustomerRest1, mockCustomerRest2);
    }

    // equals nok
    @Test
    void givenTwoDifferentCustomerRest_whenCheckIfEquals_thenBothCustomerRestMustNotBeEquals()
    {
        // given
        final CustomerRest mockCustomerRest1 =
            CustomerTestHelper.generateCustomerRest(CustomerTestHelper.MOCK_ID1,
                CustomerTestHelper.MOCK_USERNAME1,
                CustomerTestHelper.MOCK_EMAIL1,
                CustomerTestHelper.MOCK_BONUSPOINTS1);
        final CustomerRest mockCustomerRest2 =
            CustomerTestHelper.generateCustomerRest(CustomerTestHelper.MOCK_ID1,
                CustomerTestHelper.MOCK_USERNAME2,
                CustomerTestHelper.MOCK_EMAIL2,
                CustomerTestHelper.MOCK_BONUSPOINTS1);

        // when + then
        assertNotEquals(mockCustomerRest1.hashCode(), mockCustomerRest2.hashCode());
        assertNotEquals(mockCustomerRest1, mockCustomerRest2);
    }

    // toString
    @Test
    void givenCustomerRest_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final CustomerRest mockCustomerRest =
            CustomerTestHelper.generateCustomerRest(CustomerTestHelper.MOCK_ID2,
                CustomerTestHelper.MOCK_USERNAME2,
                CustomerTestHelper.MOCK_EMAIL2,
                CustomerTestHelper.MOCK_BONUSPOINTS2);
        final String expected = "CustomerRest{" +
            "id=" + CustomerTestHelper.MOCK_ID2 +
            ", username='" + CustomerTestHelper.MOCK_USERNAME2 + '\'' +
            ", email='" + CustomerTestHelper.MOCK_EMAIL2 + '\'' +
            ", bonuspoints=" + CustomerTestHelper.MOCK_BONUSPOINTS2 +
            '}';

        // when
        final String result = mockCustomerRest.toString();

        // then
        assertEquals(expected, result);

    }
}
