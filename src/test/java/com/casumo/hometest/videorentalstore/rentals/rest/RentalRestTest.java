package com.casumo.hometest.videorentalstore.rentals.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.common.infra.MappingTool;
import com.casumo.hometest.videorentalstore.rentals.RentalTestHelper;


/**
 * RentalRestTest class - RentalRest test class.
 */
class RentalRestTest
{
    // equals ok
    @Test
    void givenTwoEqualRentalRest_whenCheckIfEquals_thenBothRentalRestMustBeEquals()
    {
        // given
        final RentalRest mockRentalRest1 =
            RentalTestHelper.generateRentalRest(RentalTestHelper.MOCK_ID1,
                MappingTool.offsetDateTimeOrNull(RentalTestHelper.MOCK_START_DATETIME1),
                RentalTestHelper.MOCK_TOTAL_PRICE1,
                RentalTestHelper.MOCK_TOTAL_SUBCHARGE1,
                RentalTestHelper.MOCK_RENTAL_ITEMS_REST1);
        final RentalRest mockRentalRest2 =
            RentalTestHelper.generateRentalRest(RentalTestHelper.MOCK_ID1,
                MappingTool.offsetDateTimeOrNull(RentalTestHelper.MOCK_START_DATETIME1),
                RentalTestHelper.MOCK_TOTAL_PRICE1,
                RentalTestHelper.MOCK_TOTAL_SUBCHARGE1,
                RentalTestHelper.MOCK_RENTAL_ITEMS_REST1);

        // when + then
        assertEquals(mockRentalRest1.hashCode(), mockRentalRest2.hashCode());
        assertEquals(mockRentalRest1, mockRentalRest2);
    }

    // equals nok
    @Test
    void givenTwoDifferentRentalRest_whenCheckIfEquals_thenBothRentalRestMustNotBeEquals()
    {
        // given
        final RentalRest mockRentalRest1 = RentalTestHelper.MOCK_RENTAL_REST1;
        final RentalRest mockRentalRest2 = RentalTestHelper.MOCK_RENTAL_REST2;

        // when + then
        assertNotEquals(mockRentalRest1.hashCode(), mockRentalRest2.hashCode());
        assertNotEquals(mockRentalRest1, mockRentalRest2);
    }

    // toString
    @Test
    void givenRentalRest_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final RentalRest mockRentalRest = RentalTestHelper.MOCK_RENTAL_REST2;
        final String expected = "RentalRest{" +
            "id=" + mockRentalRest.getId() +
            ", startDateTime=" + mockRentalRest.getStartDateTime() +
            ", totalPrice=" + mockRentalRest.getTotalPrice() +
            ", totalSubcharge=" + mockRentalRest.getTotalSubcharge() +
            ", rentalItems=" + mockRentalRest.getRentalItems() +
            '}';

        // when
        final String result = mockRentalRest.toString();

        // then
        assertEquals(expected, result);

    }
}
