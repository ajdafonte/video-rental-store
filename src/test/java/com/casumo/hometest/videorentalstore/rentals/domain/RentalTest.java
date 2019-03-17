package com.casumo.hometest.videorentalstore.rentals.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.customers.CustomerTestHelper;
import com.casumo.hometest.videorentalstore.rentals.RentalTestHelper;


/**
 * RentalTest class - Rental test class.
 */
class RentalTest
{
    // equals ok
    @Test
    void givenTwoEqualRentals_whenCheckIfEquals_thenBothRentalsMustBeEquals()
    {
        // given
        final Rental mockRental1 =
            RentalTestHelper.generateRental(RentalTestHelper.MOCK_ID1,
                CustomerTestHelper.MOCK_CUSTOMER1,
                RentalTestHelper.MOCK_START_DATETIME1,
                RentalTestHelper.MOCK_RENTAL_ITEMS1);
        final Rental mockRental2 =
            RentalTestHelper.generateRental(RentalTestHelper.MOCK_ID1,
                CustomerTestHelper.MOCK_CUSTOMER1,
                RentalTestHelper.MOCK_START_DATETIME1,
                RentalTestHelper.MOCK_RENTAL_ITEMS1);

        // when + then
        assertEquals(mockRental1.hashCode(), mockRental2.hashCode());
        assertEquals(mockRental1, mockRental2);
    }

    // equals nok
    @Test
    void givenTwoDifferentRentals_whenCheckIfEquals_thenBothRentalsMustNotBeEquals()
    {
        // given
        final Rental mockRental1 = RentalTestHelper.MOCK_RENTAL1;
        final Rental mockRental2 = RentalTestHelper.MOCK_RENTAL2;

        // when + then
        assertNotEquals(mockRental1.hashCode(), mockRental2.hashCode());
        assertNotEquals(mockRental1, mockRental2);
    }

    // toString
    @Test
    void givenRental_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final Rental mockRental = RentalTestHelper.MOCK_RENTAL2;
        final String expected = "Rental{" +
            "id=" + mockRental.getId() +
            ", startdatetime=" + mockRental.getStartdatetime() +
            ", customer=" + mockRental.getCustomer() +
            ", rentalItems=" + mockRental.getRentalItems() +
            '}';

        // when
        final String result = mockRental.toString();

        // then
        assertEquals(expected, result);

    }
}
