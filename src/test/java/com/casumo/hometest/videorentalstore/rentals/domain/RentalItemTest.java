package com.casumo.hometest.videorentalstore.rentals.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.films.FilmTestHelper;
import com.casumo.hometest.videorentalstore.rentals.RentalTestHelper;


/**
 * RentalItemTest - RentalItem test class.
 */
class RentalItemTest
{
    // equals ok
    @Test
    void givenTwoEqualRentalItems_whenCheckIfEquals_thenBothRentalItemsMustBeEquals()
    {
        // given
        final RentalItem mockRentalItem1 =
            RentalTestHelper.generateRentalItem(RentalTestHelper.MOCK_ID1, FilmTestHelper.MOCK_FILM1,
                RentalTestHelper.MOCK_DAYS_RENTED1, RentalTestHelper.MOCK_PRICE1, RentalTestHelper.MOCK_SUBCHARGE1,
                RentalTestHelper.MOCK_START_DATETIME1, RentalTestHelper.MOCK_END_DATETIME1);
        final RentalItem mockRentalItem2 =
            RentalTestHelper.generateRentalItem(RentalTestHelper.MOCK_ID1, FilmTestHelper.MOCK_FILM1,
                RentalTestHelper.MOCK_DAYS_RENTED1, RentalTestHelper.MOCK_PRICE1, RentalTestHelper.MOCK_SUBCHARGE1,
                RentalTestHelper.MOCK_START_DATETIME1, RentalTestHelper.MOCK_END_DATETIME1);

        // when + then
        assertEquals(mockRentalItem1.hashCode(), mockRentalItem2.hashCode());
        assertEquals(mockRentalItem1, mockRentalItem2);
    }

    // equals nok
    @Test
    void givenTwoDifferentRentalItems_whenCheckIfEquals_thenBothRentalItemsMustNotBeEquals()
    {
        // given
        final RentalItem mockRentalItem1 = RentalTestHelper.MOCK_RENTAL_ITEM1;
        final RentalItem mockRentalItem2 = RentalTestHelper.MOCK_RENTAL_ITEM2;

        // when + then
        assertNotEquals(mockRentalItem1.hashCode(), mockRentalItem2.hashCode());
        assertNotEquals(mockRentalItem1, mockRentalItem2);
    }

    // toString
    @Test
    void givenRentalItem_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final RentalItem mockRentalItem = RentalTestHelper.MOCK_RENTAL_ITEM2;
        final String expected = "RentalItem{" +
            "id=" + mockRentalItem.getId() +
            ", daysrented=" + mockRentalItem.getDaysrented() +
            ", price=" + mockRentalItem.getPrice() +
            ", surcharge=" + mockRentalItem.getSurcharge() +
            ", startdatetime=" + mockRentalItem.getStartdatetime() +
            ", enddatetime=" + mockRentalItem.getEnddatetime() +
            ", film=" + mockRentalItem.getFilm() +
            '}';

        // when
        final String result = mockRentalItem.toString();

        // then
        assertEquals(expected, result);

    }
}
