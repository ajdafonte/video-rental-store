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
            FilmTestHelper.generateFilmRest(FilmTestHelper.MOCK_ID1, FilmTestHelper.MOCK_OLD_NAME, FilmTestHelper.MOCK_OLD_FILM_TYPE_REST);
        final FilmRest mockFilmRest2 =
            FilmTestHelper.generateFilmRest(FilmTestHelper.MOCK_ID1, FilmTestHelper.MOCK_OLD_NAME, FilmTestHelper.MOCK_OLD_FILM_TYPE_REST);

        // when + then
        assertEquals(mockFilmRest1.hashCode(), mockFilmRest2.hashCode());
        assertEquals(mockFilmRest1, mockFilmRest2);
    }

    // equals nok
    @Test
    void givenTwoDifferentFilmRest_whenCheckIfEquals_thenBothFilmRestMustNotBeEquals()
    {
        // given
        final FilmRest mockFilmRest1 = FilmTestHelper.MOCK_OLD_FILM_REST;
        final FilmRest mockFilmRest2 = FilmTestHelper.MOCK_NEW_RELEASE_FILM_REST;

        // when + then
        assertNotEquals(mockFilmRest1.hashCode(), mockFilmRest2.hashCode());
        assertNotEquals(mockFilmRest1, mockFilmRest2);
    }

    // toString
    @Test
    void givenFilmRest_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final FilmRest mockFilmRest = FilmTestHelper.MOCK_NEW_RELEASE_FILM_REST;
        final String expected = "FilmRest{" +
            "id=" + mockFilmRest.getId() +
            ", name='" + mockFilmRest.getName() + '\'' +
            ", type=" + mockFilmRest.getType() +
            '}';

        // when
        final String result = mockFilmRest.toString();

        // then
        assertEquals(expected, result);

    }
}
