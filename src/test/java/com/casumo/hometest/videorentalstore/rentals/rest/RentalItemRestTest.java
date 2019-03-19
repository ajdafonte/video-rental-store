package com.casumo.hometest.videorentalstore.rentals.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.common.infra.MappingTool;
import com.casumo.hometest.videorentalstore.films.FilmTestHelper;
import com.casumo.hometest.videorentalstore.rentals.RentalTestHelper;


/**
 * RentalItemItemRestTest class - RentalItemItemRest test class.
 */
class RentalItemRestTest
{
    // equals ok
    @Test
    void givenTwoEqualRentalItemRest_whenCheckIfEquals_thenBothRentalItemRestMustBeEquals()
    {
        // given
        final RentalItemRest mockRentalItemRest1 =
            RentalTestHelper.generateRentalItemRest(RentalTestHelper.MOCK_ID1,
                FilmTestHelper.MOCK_OLD_FILM_REST,
                RentalTestHelper.MOCK_DAYS_RENTED1,
                RentalTestHelper.MOCK_PRICE1,
                RentalTestHelper.MOCK_SUBCHARGE1,
                MappingTool.offsetDateTimeOrNull(RentalTestHelper.MOCK_START_DATETIME1),
                MappingTool.offsetDateTimeOrNull(RentalTestHelper.MOCK_START_DATETIME1));
        final RentalItemRest mockRentalItemRest2 =
            RentalTestHelper.generateRentalItemRest(RentalTestHelper.MOCK_ID1,
                FilmTestHelper.MOCK_OLD_FILM_REST,
                RentalTestHelper.MOCK_DAYS_RENTED1,
                RentalTestHelper.MOCK_PRICE1,
                RentalTestHelper.MOCK_SUBCHARGE1,
                MappingTool.offsetDateTimeOrNull(RentalTestHelper.MOCK_START_DATETIME1),
                MappingTool.offsetDateTimeOrNull(RentalTestHelper.MOCK_START_DATETIME1));

        // when + then
        assertEquals(mockRentalItemRest1.hashCode(), mockRentalItemRest2.hashCode());
        assertEquals(mockRentalItemRest1, mockRentalItemRest2);
    }

    // equals nok
    @Test
    void givenTwoDifferentRentalItemRest_whenCheckIfEquals_thenBothRentalItemRestMustNotBeEquals()
    {
        // given
        final RentalItemRest mockRentalItemRest1 = RentalTestHelper.MOCK_RENTAL_ITEM_REST1;
        final RentalItemRest mockRentalItemRest2 = RentalTestHelper.MOCK_RENTAL_ITEM_REST2;

        // when + then
        assertNotEquals(mockRentalItemRest1.hashCode(), mockRentalItemRest2.hashCode());
        assertNotEquals(mockRentalItemRest1, mockRentalItemRest2);
    }

    // toString
    @Test
    void givenRentalItemRest_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final RentalItemRest mockRentalItemRest = RentalTestHelper.MOCK_RENTAL_ITEM_REST2;
        final String expected = "RentalItemRest{" +
            "id=" + mockRentalItemRest.getId() +
            ", film=" + mockRentalItemRest.getFilm() +
            ", daysRented=" + mockRentalItemRest.getDaysRented() +
            ", price=" + mockRentalItemRest.getPrice() +
            ", surcharge=" + mockRentalItemRest.getSurcharge() +
            ", startDateTime=" + mockRentalItemRest.getStartDateTime() +
            ", endDateTime=" + mockRentalItemRest.getEndDateTime() +
            '}';

        // when
        final String result = mockRentalItemRest.toString();

        // then
        assertEquals(expected, result);

    }
}
