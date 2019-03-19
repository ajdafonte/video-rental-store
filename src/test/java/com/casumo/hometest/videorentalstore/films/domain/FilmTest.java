package com.casumo.hometest.videorentalstore.films.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.films.FilmTestHelper;


/**
 * FilmTest class - Test Film class.
 */
class FilmTest
{
    // equals ok
    @Test
    void givenTwoEqualFilms_whenCheckIfEquals_thenBothFilmsMustBeEquals()
    {
        // given
        final Film mockFilm1 = FilmTestHelper.generateFilm(FilmTestHelper.MOCK_ID1, FilmTestHelper.MOCK_OLD_NAME, FilmTestHelper.MOCK_OLD_FILM_TYPE);
        final Film mockFilm2 = FilmTestHelper.generateFilm(FilmTestHelper.MOCK_ID1, FilmTestHelper.MOCK_OLD_NAME, FilmTestHelper.MOCK_OLD_FILM_TYPE);

        // when + then
        assertEquals(mockFilm1.hashCode(), mockFilm2.hashCode());
        assertEquals(mockFilm1, mockFilm2);
    }

    // equals nok
    @Test
    void givenTwoDifferentFilms_whenCheckIfEquals_thenBothFilmsMustNotBeEquals()
    {
        // given
        final Film mockFilm1 = FilmTestHelper.MOCK_OLD_FILM;
        final Film mockFilm2 = FilmTestHelper.MOCK_NEW_RELEASE_FILM;

        // when + then
        assertNotEquals(mockFilm1.hashCode(), mockFilm2.hashCode());
        assertNotEquals(mockFilm1, mockFilm2);
    }

    // toString
    @Test
    void givenFilm_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final Film mockFilm = FilmTestHelper.MOCK_NEW_RELEASE_FILM;
        final String expected = "Film{" +
            "id=" + mockFilm.getId() +
            ", name='" + mockFilm.getName() + '\'' +
            ", type=" + mockFilm.getType() +
            '}';

        // when
        final String result = mockFilm.toString();

        // then
        assertEquals(expected, result);

    }
}
