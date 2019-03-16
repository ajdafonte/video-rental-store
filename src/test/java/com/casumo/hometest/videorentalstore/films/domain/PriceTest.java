package com.casumo.hometest.videorentalstore.films.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.films.FilmTestHelper;


/**
 * PriceTest class - Price test class.
 */
class PriceTest
{
    // equals ok
    @Test
    void givenTwoEqualPrices_whenCheckIfEquals_thenBothPricesMustBeEquals()
    {
        // given
        final Price mockPrice1 =
            FilmTestHelper.generatePrice(FilmTestHelper.MOCK_ID1, FilmTestHelper.MOCK_BASIC_PRICE_NAME, FilmTestHelper.MOCK_BASIC_PRICE_VALUE);
        final Price mockPrice2 =
            FilmTestHelper.generatePrice(FilmTestHelper.MOCK_ID1, FilmTestHelper.MOCK_BASIC_PRICE_NAME, FilmTestHelper.MOCK_BASIC_PRICE_VALUE);

        // when + then
        assertEquals(mockPrice1.hashCode(), mockPrice2.hashCode());
        assertEquals(mockPrice1, mockPrice2);
    }

    // equals nok
    @Test
    void givenTwoDifferentPrices_whenCheckIfEquals_thenBothPricesMustNotBeEquals()
    {
        // given
        final Price mockPrice1 = FilmTestHelper.MOCK_BASIC_PRICE;
        final Price mockPrice2 = FilmTestHelper.MOCK_PREMIUM_PRICE;

        // when + then
        assertNotEquals(mockPrice1.hashCode(), mockPrice2.hashCode());
        assertNotEquals(mockPrice1, mockPrice2);
    }

    // toString
    @Test
    void givenPrice_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final Price mockPrice = FilmTestHelper.MOCK_PREMIUM_PRICE;
        final String expected = "Price{" +
            "id=" + mockPrice.getId() +
            ", name='" + mockPrice.getName() + '\'' +
            ", value=" + mockPrice.getValue() +
            '}';

        // when
        final String result = mockPrice.toString();

        // then
        assertEquals(expected, result);

    }
}
