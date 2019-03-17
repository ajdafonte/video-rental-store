package com.casumo.hometest.videorentalstore.rentals.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.rentals.RentalTestHelper;


/**
 * InsertRentalRequestBodyTest class - InsertRentalRequestBody test class.
 */
class InsertRentalRequestBodyTest
{
    // equals ok
    @Test
    void givenTwoEqualInsertRentalRequestBodies_whenCheckIfEquals_thenBothRequestBodiesMustBeEquals()
    {
        // given
        final InsertRentalRequestBody mockRequestBody1 = RentalTestHelper.generateInsertRentalRequestBody(RentalTestHelper.MOCK_ID1,
            Arrays.asList(RentalTestHelper.MOCK_INSERT_RENTAL_ITEM_REQUEST_BODY1, RentalTestHelper.MOCK_INSERT_RENTAL_ITEM_REQUEST_BODY2));
        final InsertRentalRequestBody mockRequestBody2 = RentalTestHelper.generateInsertRentalRequestBody(RentalTestHelper.MOCK_ID1,
            Arrays.asList(RentalTestHelper.MOCK_INSERT_RENTAL_ITEM_REQUEST_BODY1, RentalTestHelper.MOCK_INSERT_RENTAL_ITEM_REQUEST_BODY2));

        // when + then
        assertEquals(mockRequestBody1.hashCode(), mockRequestBody2.hashCode());
        assertEquals(mockRequestBody1, mockRequestBody2);
    }

    // equals nok
    @Test
    void givenTwoDifferentInsertRentalRequestBodies_whenCheckIfEquals_thenBothRequestBodiesMustNotBeEquals()
    {
        // given
        final InsertRentalRequestBody mockRequestBody1 =
            RentalTestHelper.generateInsertRentalRequestBody(RentalTestHelper.MOCK_ID1,
                Arrays.asList(RentalTestHelper.MOCK_INSERT_RENTAL_ITEM_REQUEST_BODY1, RentalTestHelper.MOCK_INSERT_RENTAL_ITEM_REQUEST_BODY2));
        final InsertRentalRequestBody mockRequestBody2 =
            RentalTestHelper.generateInsertRentalRequestBody(RentalTestHelper.MOCK_ID1,
                Collections.singletonList(RentalTestHelper.MOCK_INSERT_RENTAL_ITEM_REQUEST_BODY1));

        // when + then
        assertNotEquals(mockRequestBody1.hashCode(), mockRequestBody2.hashCode());
        assertNotEquals(mockRequestBody1, mockRequestBody2);
    }

    // toString
    @Test
    void givenInsertRentalRequestBody_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final InsertRentalRequestBody mockRequestBody = RentalTestHelper.MOCK_INSERT_RENTAL_REQUEST_BODY1;
        final String expected = "InsertRentalRequestBody{" +
            "customerId=" + mockRequestBody.getCustomerId() +
            ", rentalItems=" + mockRequestBody.getRentalItems() +
            '}';

        // when
        final String result = mockRequestBody.toString();

        // then
        assertEquals(expected, result);

    }
}
