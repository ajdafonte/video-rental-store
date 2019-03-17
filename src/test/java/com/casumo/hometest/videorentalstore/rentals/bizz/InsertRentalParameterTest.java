package com.casumo.hometest.videorentalstore.rentals.bizz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.rentals.RentalTestHelper;


/**
 * InsertRentalParameterTest class. InsertRentalParameter test class.
 */
class InsertRentalParameterTest
{
    // equals ok
    @Test
    void givenTwoEqualInsertRentalParameters_whenCheckIfEquals_thenBothParametersMustBeEquals()
    {
        // given
        final InsertRentalParameter mockParameter1 =
            RentalTestHelper.generateInsertRentalParameter(RentalTestHelper.MOCK_ID1,
                Collections.singletonList(RentalTestHelper.MOCK_INSERT_RENTAL_ITEM_PARAMETER1));
        final InsertRentalParameter mockParameter2 =
            RentalTestHelper.generateInsertRentalParameter(RentalTestHelper.MOCK_ID1,
                Collections.singletonList(RentalTestHelper.MOCK_INSERT_RENTAL_ITEM_PARAMETER1));

        // when + then
        assertEquals(mockParameter1.hashCode(), mockParameter2.hashCode());
        assertEquals(mockParameter1, mockParameter2);
    }

    // equals nok
    @Test
    void givenTwoDifferentInsertRentalParameters_whenCheckIfEquals_thenBothParametersMustNotBeEquals()
    {
        // given
        final InsertRentalParameter mockParameter1 =
            RentalTestHelper.generateInsertRentalParameter(RentalTestHelper.MOCK_ID1,
                Collections.singletonList(RentalTestHelper.MOCK_INSERT_RENTAL_ITEM_PARAMETER1));
        final InsertRentalParameter mockParameter2 =
            RentalTestHelper.generateInsertRentalParameter(RentalTestHelper.MOCK_ID1,
                Collections.singletonList(RentalTestHelper.MOCK_INSERT_RENTAL_ITEM_PARAMETER2));

        // when + then
        assertNotEquals(mockParameter1.hashCode(), mockParameter2.hashCode());
        assertNotEquals(mockParameter1, mockParameter2);
    }

    // toString
    @Test
    void givenInsertRentalParameter_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final InsertRentalParameter mockParameter = RentalTestHelper.MOCK_INSERT_RENTAL_PARAMETER1;
        final String expected = "InsertRentalParameter{" +
            "customerId=" + mockParameter.getCustomerId() +
            ", itemsToRent=" + mockParameter.getItemsToRent() +
            '}';

        // when
        final String result = mockParameter.toString();

        // then
        assertEquals(expected, result);

    }
}
