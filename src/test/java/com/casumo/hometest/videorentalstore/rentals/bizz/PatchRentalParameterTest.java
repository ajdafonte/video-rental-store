package com.casumo.hometest.videorentalstore.rentals.bizz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.rentals.RentalTestHelper;


/**
 * PatchRentalParameterTest class - PatchRentalParameter test class.
 */
class PatchRentalParameterTest
{
    // equals ok
    @Test
    void givenTwoEqualPatchRentalParameters_whenCheckIfEquals_thenBothParametersMustBeEquals()
    {
        // given
        final PatchRentalParameter mockParameter1 =
            RentalTestHelper.generatePatchRentalParameter(RentalTestHelper.MOCK_ID1,
                Arrays.asList(RentalTestHelper.MOCK_ID1, RentalTestHelper.MOCK_ID2));
        final PatchRentalParameter mockParameter2 =
            RentalTestHelper.generatePatchRentalParameter(RentalTestHelper.MOCK_ID1,
                Arrays.asList(RentalTestHelper.MOCK_ID1, RentalTestHelper.MOCK_ID2));

        // when + then
        assertEquals(mockParameter1.hashCode(), mockParameter2.hashCode());
        assertEquals(mockParameter1, mockParameter2);
    }

    // equals nok
    @Test
    void givenTwoDifferentPatchRentalParameters_whenCheckIfEquals_thenBothParametersMustNotBeEquals()
    {
        // given
        final PatchRentalParameter mockParameter1 =
            RentalTestHelper.generatePatchRentalParameter(RentalTestHelper.MOCK_ID1,
                Arrays.asList(RentalTestHelper.MOCK_ID1, RentalTestHelper.MOCK_ID2));
        final PatchRentalParameter mockParameter2 =
            RentalTestHelper.generatePatchRentalParameter(RentalTestHelper.MOCK_ID1, Collections.singletonList(RentalTestHelper.MOCK_ID2));

        // when + then
        assertNotEquals(mockParameter1.hashCode(), mockParameter2.hashCode());
        assertNotEquals(mockParameter1, mockParameter2);
    }

    // toString
    @Test
    void givenPatchRentalParameter_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final PatchRentalParameter mockParameter = RentalTestHelper.MOCK_PATCH_RENTAL_PARAMETER1;
        final String expected = "PatchRentalParameter{" +
            "rentalId=" + mockParameter.getRentalId() +
            ", rentalItemsToReturn=" + mockParameter.getRentalItemsToReturn() +
            '}';

        // when
        final String result = mockParameter.toString();

        // then
        assertEquals(expected, result);

    }
}
