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
    private static final long MOCK_ID1 = 1L;
    private static final long MOCK_ID2 = 2L;
    private static final String MOCK_NAME1 = "Saving Private Ryan";
    private static final String MOCK_NAME2 = "Green Book";
    private static final FilmType MOCK_FILM_TYPE1 = FilmType.OLD;
    private static final FilmType MOCK_FILM_TYPE2 = FilmType.NEW_RELEASE;

    // equals ok
    @Test
    void givenTwoEqualFilms_whenCheckIfEquals_thenBothFilmsMustBeEquals()
    {
        // given
        final Film mockFilm1 = FilmTestHelper.generateFilm(MOCK_ID1, MOCK_NAME1, MOCK_FILM_TYPE1);
        final Film mockFilm2 = FilmTestHelper.generateFilm(MOCK_ID1, MOCK_NAME1, MOCK_FILM_TYPE1);

        // when + then
        assertEquals(mockFilm1.hashCode(), mockFilm2.hashCode());
        assertEquals(mockFilm1, mockFilm2);
    }

    // equals nok
    @Test
    void givenTwoDifferentFilms_whenCheckIfEquals_thenBothFilmsMustNotBeEquals()
    {
        // given
        final Film mockFilm1 = FilmTestHelper.generateFilm(MOCK_ID1, MOCK_NAME1, MOCK_FILM_TYPE1);
        final Film mockFilm2 = FilmTestHelper.generateFilm(MOCK_ID2, MOCK_NAME1, MOCK_FILM_TYPE1);

        // when + then
        assertNotEquals(mockFilm1.hashCode(), mockFilm2.hashCode());
        assertNotEquals(mockFilm1, mockFilm2);
    }

    // toString
    @Test
    void givenFilm_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final Film mockFilm = FilmTestHelper.generateFilm(MOCK_ID2, MOCK_NAME2, MOCK_FILM_TYPE2);
        final String expected = "Film{" +
            "id=" + MOCK_ID2 +
            ", name='" + MOCK_NAME2 + '\'' +
            ", type=" + MOCK_FILM_TYPE2 +
            '}';

        // when
        final String result = mockFilm.toString();

        // then
        assertEquals(expected, result);

    }
}
