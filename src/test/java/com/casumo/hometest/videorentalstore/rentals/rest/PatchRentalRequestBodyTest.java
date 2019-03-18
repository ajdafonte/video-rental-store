package com.casumo.hometest.videorentalstore.rentals.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.rentals.RentalTestHelper;


/**
 * PatchRentalRequestBodyTest class - PatchRentalRequestBody test class.
 */
class PatchRentalRequestBodyTest
{
    // equals ok
    @Test
    void givenTwoEqualPatchRentalRequestBodies_whenCheckIfEquals_thenBothRequestBodiesMustBeEquals()
    {
        // given
        final PatchRentalRequestBody mockRequestBody1 =
            RentalTestHelper.generatePatchRentalRequestBody(Collections.singletonList(RentalTestHelper.MOCK_ID1));
        final PatchRentalRequestBody mockRequestBody2 =
            RentalTestHelper.generatePatchRentalRequestBody(Collections.singletonList(RentalTestHelper.MOCK_ID1));

        // when + then
        assertEquals(mockRequestBody1.hashCode(), mockRequestBody2.hashCode());
        assertEquals(mockRequestBody1, mockRequestBody2);
    }

    // equals nok
    @Test
    void givenTwoDifferentPatchRentalRequestBodies_whenCheckIfEquals_thenBothRequestBodiesMustNotBeEquals()
    {
        // given
        final PatchRentalRequestBody mockRequestBody1 =
            RentalTestHelper.generatePatchRentalRequestBody(Collections.singletonList(RentalTestHelper.MOCK_ID1));
        final PatchRentalRequestBody mockRequestBody2 =
            RentalTestHelper.MOCK_PATCH_RENTAL_REQUEST_BODY1;

        // when + then
        assertNotEquals(mockRequestBody1.hashCode(), mockRequestBody2.hashCode());
        assertNotEquals(mockRequestBody1, mockRequestBody2);
    }

    // toString
    @Test
    void givenPatchRentalRequestBody_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final PatchRentalRequestBody mockRequestBody = RentalTestHelper.MOCK_PATCH_RENTAL_REQUEST_BODY1;
        final String expected = "PatchRentalRequestBody{" +
            "rentalItemsToReturn=" + mockRequestBody.getRentalItemsToReturn() +
            '}';

        // when
        final String result = mockRequestBody.toString();

        // then
        assertEquals(expected, result);

    }
}
