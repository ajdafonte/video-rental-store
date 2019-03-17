package com.casumo.hometest.videorentalstore.rentals.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.rentals.RentalTestHelper;


/**
 * InsertRentalItemRequestBodyTest class - InsertRentalItemRequestBody test class.
 */
class InsertRentalItemRequestBodyTest
{
    // equals ok
    @Test
    void givenTwoEqualInsertRentalItemRequestBodies_whenCheckIfEquals_thenBothRequestBodiesMustBeEquals()
    {
        // given
        final InsertRentalItemRequestBody mockRequestBody1 =
            RentalTestHelper.generateInsertRentalItemRequestBody(RentalTestHelper.MOCK_ID1, RentalTestHelper.MOCK_DAYS_RENTED1);
        final InsertRentalItemRequestBody mockRequestBody2 =
            RentalTestHelper.generateInsertRentalItemRequestBody(RentalTestHelper.MOCK_ID1, RentalTestHelper.MOCK_DAYS_RENTED1);

        // when + then
        assertEquals(mockRequestBody1.hashCode(), mockRequestBody2.hashCode());
        assertEquals(mockRequestBody1, mockRequestBody2);
    }

    // equals nok
    @Test
    void givenTwoDifferentInsertRentalItemRequestBodies_whenCheckIfEquals_thenBothRequestBodiesMustNotBeEquals()
    {
        // given
        final InsertRentalItemRequestBody mockRequestBody1 = RentalTestHelper.MOCK_INSERT_RENTAL_ITEM_REQUEST_BODY1;
        final InsertRentalItemRequestBody mockRequestBody2 = RentalTestHelper.MOCK_INSERT_RENTAL_ITEM_REQUEST_BODY2;

        // when + then
        assertNotEquals(mockRequestBody1.hashCode(), mockRequestBody2.hashCode());
        assertNotEquals(mockRequestBody1, mockRequestBody2);
    }

    // toString
    @Test
    void givenInsertRentalItemRequestBody_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final InsertRentalItemRequestBody mockRequestBody = RentalTestHelper.MOCK_INSERT_RENTAL_ITEM_REQUEST_BODY2;
        final String expected = "InsertRentalItemRequestBody{" +
            "filmId=" + mockRequestBody.getFilmId() +
            ", daysToRent=" + mockRequestBody.getDaysToRent() +
            '}';

        // when
        final String result = mockRequestBody.toString();

        // then
        assertEquals(expected, result);

    }
}
