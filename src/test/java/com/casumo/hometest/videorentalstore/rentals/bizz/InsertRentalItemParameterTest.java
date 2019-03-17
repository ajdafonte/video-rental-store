package com.casumo.hometest.videorentalstore.rentals.bizz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.rentals.RentalTestHelper;


/**
 * InsertRentalItemParameterTest class - InsertRentalItemParameter test class.
 */
class InsertRentalItemParameterTest
{
    // equals ok
    @Test
    void givenTwoEqualInsertRentalItemParameters_whenCheckIfEquals_thenBothParametersMustBeEquals()
    {
        // given
        final InsertRentalItemParameter mockParameter1 =
            RentalTestHelper.generateInsertRentalItemParameter(RentalTestHelper.MOCK_ID1, RentalTestHelper.MOCK_DAYS_RENTED1);
        final InsertRentalItemParameter mockParameter2 =
            RentalTestHelper.generateInsertRentalItemParameter(RentalTestHelper.MOCK_ID1, RentalTestHelper.MOCK_DAYS_RENTED1);

        // when + then
        assertEquals(mockParameter1.hashCode(), mockParameter2.hashCode());
        assertEquals(mockParameter1, mockParameter2);
    }

    // equals nok
    @Test
    void givenTwoDifferentInsertRentalItemParameters_whenCheckIfEquals_thenBothParametersMustNotBeEquals()
    {
        // given
        final InsertRentalItemParameter mockParameter1 =
            RentalTestHelper.MOCK_INSERT_RENTAL_ITEM_PARAMETER1;
        final InsertRentalItemParameter mockParameter2 =
            RentalTestHelper.MOCK_INSERT_RENTAL_ITEM_PARAMETER2;

        // when + then
        assertNotEquals(mockParameter1.hashCode(), mockParameter2.hashCode());
        assertNotEquals(mockParameter1, mockParameter2);
    }

    // toString
    @Test
    void givenInsertRentalItemParameter_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final InsertRentalItemParameter mockParameter = RentalTestHelper.MOCK_INSERT_RENTAL_ITEM_PARAMETER2;
        final String expected = "InsertRentalItemParameter{" +
            "filmId=" + mockParameter.getFilmId() +
            ", daysToRent=" + mockParameter.getDaysToRent() +
            '}';

        // when
        final String result = mockParameter.toString();

        // then
        assertEquals(expected, result);

    }
}
