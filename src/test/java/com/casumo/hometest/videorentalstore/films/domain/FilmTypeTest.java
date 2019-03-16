package com.casumo.hometest.videorentalstore.films.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.films.FilmTestHelper;


/**
 * FilmTypeTest class - FilmType test class.
 */
class FilmTypeTest
{
    // equals ok
    @Test
    void givenTwoEqualFilmTypes_whenCheckIfEquals_thenBothFilmTypesMustBeEquals()
    {
        // given
        final FilmType mockFilmType1 =
            FilmTestHelper.generateFilmType(FilmTestHelper.MOCK_ID1, FilmTestHelper.MOCK_NAME1, FilmTestHelper.MOCK_PREMIUM_PRICE);
        final FilmType mockFilmType2 =
            FilmTestHelper.generateFilmType(FilmTestHelper.MOCK_ID1, FilmTestHelper.MOCK_NAME1, FilmTestHelper.MOCK_PREMIUM_PRICE);

        // when + then
        assertEquals(mockFilmType1.hashCode(), mockFilmType2.hashCode());
        assertEquals(mockFilmType1, mockFilmType2);
    }

    // equals nok
    @Test
    void givenTwoDifferentFilmTypes_whenCheckIfEquals_thenBothFilmTypesMustNotBeEquals()
    {
        // given
        final FilmType mockFilmType1 = FilmTestHelper.MOCK_OLD_FILM_TYPE;
        final FilmType mockFilmType2 = FilmTestHelper.MOCK_REGULAR_FILM_TYPE;

        // when + then
        assertNotEquals(mockFilmType1.hashCode(), mockFilmType2.hashCode());
        assertNotEquals(mockFilmType1, mockFilmType2);
    }

    // toString
    @Test
    void givenFilmType_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final FilmType mockFilmType = FilmTestHelper.MOCK_NEW_RELEASE_FILM_TYPE;
        final String expected = "FilmType{" +
            "id=" + mockFilmType.getId() +
            ", name='" + mockFilmType.getName() + '\'' +
            ", price=" + mockFilmType.getPrice() +
            '}';

        // when
        final String result = mockFilmType.toString();

        // then
        assertEquals(expected, result);

    }
}
