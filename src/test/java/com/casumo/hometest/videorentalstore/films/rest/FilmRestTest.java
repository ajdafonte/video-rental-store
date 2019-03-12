package com.casumo.hometest.videorentalstore.films.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.films.FilmTestHelper;


/**
 * FilmRestTest class - FilmRest test class.
 */
class FilmRestTest
{
    // equals ok
    @Test
    void givenTwoEqualFilmRest_whenCheckIfEquals_thenBothFilmRestMustBeEquals()
    {
        // given
        final FilmRest mockFilmRest1 =
            FilmTestHelper.generateFilmRest(FilmTestHelper.MOCK_ID1, FilmTestHelper.MOCK_NAME1, FilmTestHelper.MOCK_FILM_TYPE1);
        final FilmRest mockFilmRest2 =
            FilmTestHelper.generateFilmRest(FilmTestHelper.MOCK_ID1, FilmTestHelper.MOCK_NAME1, FilmTestHelper.MOCK_FILM_TYPE1);

        // when + then
        assertEquals(mockFilmRest1.hashCode(), mockFilmRest2.hashCode());
        assertEquals(mockFilmRest1, mockFilmRest2);
    }

    // equals nok
    @Test
    void givenTwoDifferentFilmRest_whenCheckIfEquals_thenBothFilmRestMustNotBeEquals()
    {
        // given
        final FilmRest mockFilmRest1 =
            FilmTestHelper.generateFilmRest(FilmTestHelper.MOCK_ID1, FilmTestHelper.MOCK_NAME1, FilmTestHelper.MOCK_FILM_TYPE1);
        final FilmRest mockFilmRest2 =
            FilmTestHelper.generateFilmRest(FilmTestHelper.MOCK_ID2, FilmTestHelper.MOCK_NAME1, FilmTestHelper.MOCK_FILM_TYPE1);

        // when + then
        assertNotEquals(mockFilmRest1.hashCode(), mockFilmRest2.hashCode());
        assertNotEquals(mockFilmRest1, mockFilmRest2);
    }

    // toString
    @Test
    void givenFilmRest_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final FilmRest mockFilmRest =
            FilmTestHelper.generateFilmRest(FilmTestHelper.MOCK_ID2, FilmTestHelper.MOCK_NAME2, FilmTestHelper.MOCK_FILM_TYPE2);
        final String expected = "FilmRest{" +
            "id=" + FilmTestHelper.MOCK_ID2 +
            ", name='" + FilmTestHelper.MOCK_NAME2 + '\'' +
            ", type=" + FilmTestHelper.MOCK_FILM_TYPE2 +
            '}';

        // when
        final String result = mockFilmRest.toString();

        // then
        assertEquals(expected, result);

    }
}
