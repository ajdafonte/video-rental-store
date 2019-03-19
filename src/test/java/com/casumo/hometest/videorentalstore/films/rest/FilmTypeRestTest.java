package com.casumo.hometest.videorentalstore.films.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.films.FilmTestHelper;


/**
 * FilmTypeRestTest class - FilmTypeRest test class.
 */
class FilmTypeRestTest
{
    // equals ok
    @Test
    void givenTwoEqualFilmTypeRest_whenCheckIfEquals_thenBothFilmTypeRestMustBeEquals()
    {
        // given
        final FilmTypeRest mockFilmTypeRest1 =
            FilmTestHelper.generateFilmTypeRest(FilmTestHelper.MOCK_ID1, FilmTestHelper.MOCK_NEW_FILM_TYPE_NAME);
        final FilmTypeRest mockFilmTypeRest2 =
            FilmTestHelper.generateFilmTypeRest(FilmTestHelper.MOCK_ID1, FilmTestHelper.MOCK_NEW_FILM_TYPE_NAME);

        // when + then
        assertEquals(mockFilmTypeRest1.hashCode(), mockFilmTypeRest2.hashCode());
        assertEquals(mockFilmTypeRest1, mockFilmTypeRest2);
    }

    // equals nok
    @Test
    void givenTwoDifferentFilmTypeRest_whenCheckIfEquals_thenBothFilmTypeRestMustNotBeEquals()
    {
        // given
        final FilmTypeRest mockFilmTypeRest1 = FilmTestHelper.generateFilmTypeRest(FilmTestHelper.MOCK_ID1, FilmTestHelper.MOCK_NEW_FILM_TYPE_NAME);
        final FilmTypeRest mockFilmTypeRest2 =
            FilmTestHelper.generateFilmTypeRest(FilmTestHelper.MOCK_ID1, FilmTestHelper.MOCK_REGULAR_FILM_TYPE_NAME);

        // when + then
        assertNotEquals(mockFilmTypeRest1.hashCode(), mockFilmTypeRest2.hashCode());
        assertNotEquals(mockFilmTypeRest1, mockFilmTypeRest2);
    }

    // toString
    @Test
    void givenFilmTypeRest_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final FilmTypeRest mockFilmTypeRest = FilmTestHelper.MOCK_REGULAR_FILM_TYPE_REST;
        final String expected = "FilmTypeRest{" +
            "id=" + mockFilmTypeRest.getId() +
            ", name='" + mockFilmTypeRest.getName() + '\'' +
            '}';

        // when
        final String result = mockFilmTypeRest.toString();

        // then
        assertEquals(expected, result);

    }
}
