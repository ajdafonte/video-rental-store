package com.casumo.hometest.videorentalstore.films.bizz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.films.FilmTestHelper;


/**
 * InsertFilmParameterTest class - InsertFilmParameter test class.
 */
class InsertFilmParameterTest
{
    // equals ok
    @Test
    void givenTwoEqualInsertFilmParameters_whenCheckIfEquals_thenBothParametersMustBeEquals()
    {
        // given
        final InsertFilmParameter mockParameter1 =
            FilmTestHelper.generateInsertFilmParameter(FilmTestHelper.MOCK_OLD_NAME, FilmTestHelper.MOCK_ID1);
        final InsertFilmParameter mockParameter2 =
            FilmTestHelper.generateInsertFilmParameter(FilmTestHelper.MOCK_OLD_NAME, FilmTestHelper.MOCK_ID1);

        // when + then
        assertEquals(mockParameter1.hashCode(), mockParameter2.hashCode());
        assertEquals(mockParameter1, mockParameter2);
    }

    // equals nok
    @Test
    void givenTwoDifferentInsertFilmParameters_whenCheckIfEquals_thenBothParametersMustNotBeEquals()
    {
        // given
        final InsertFilmParameter mockParameter1 =
            FilmTestHelper.generateInsertFilmParameter(FilmTestHelper.MOCK_OLD_NAME, FilmTestHelper.MOCK_ID1);
        final InsertFilmParameter mockParameter2 =
            FilmTestHelper.generateInsertFilmParameter(FilmTestHelper.MOCK_NEW_RELEASE_NAME, FilmTestHelper.MOCK_ID1);

        // when + then
        assertNotEquals(mockParameter1.hashCode(), mockParameter2.hashCode());
        assertNotEquals(mockParameter1, mockParameter2);
    }

    // toString
    @Test
    void givenInsertFilmParameter_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final InsertFilmParameter mockParameter = FilmTestHelper.MOCK_INSERT_PARAMETER1;
        final String expected = "InsertFilmParameter{" +
            "name='" + mockParameter.getName() + '\'' +
            ", filmTypeId=" + mockParameter.getFilmTypeId() +
            '}';

        // when
        final String result = mockParameter.toString();

        // then
        assertEquals(expected, result);

    }
}
