package com.casumo.hometest.videorentalstore.films.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.films.FilmTestHelper;


/**
 * InsertFilmRequestBodyTest class - InsertFilmRequestBody test class.
 */
class InsertFilmRequestBodyTest
{
    // equals ok
    @Test
    void givenTwoEqualInsertFilmRequestBodies_whenCheckIfEquals_thenBothRequestBodiesMustBeEquals()
    {
        // given
        final InsertFilmRequestBody mockRequestBody1 =
            FilmTestHelper.generateInsertFilmRequestBody(FilmTestHelper.MOCK_NAME1, FilmTestHelper.MOCK_ID1);
        final InsertFilmRequestBody mockRequestBody2 =
            FilmTestHelper.generateInsertFilmRequestBody(FilmTestHelper.MOCK_NAME1, FilmTestHelper.MOCK_ID1);

        // when + then
        assertEquals(mockRequestBody1.hashCode(), mockRequestBody2.hashCode());
        assertEquals(mockRequestBody1, mockRequestBody2);
    }

    // equals nok
    @Test
    void givenTwoDifferentInsertFilmRequestBodies_whenCheckIfEquals_thenBothRequestBodiesMustNotBeEquals()
    {
        // given
        final InsertFilmRequestBody mockRequestBody1 =
            FilmTestHelper.generateInsertFilmRequestBody(FilmTestHelper.MOCK_NAME1, FilmTestHelper.MOCK_ID1);
        final InsertFilmRequestBody mockRequestBody2 =
            FilmTestHelper.generateInsertFilmRequestBody(FilmTestHelper.MOCK_NAME2, FilmTestHelper.MOCK_ID1);

        // when + then
        assertNotEquals(mockRequestBody1.hashCode(), mockRequestBody2.hashCode());
        assertNotEquals(mockRequestBody1, mockRequestBody2);
    }

    // toString
    @Test
    void givenInsertFilmRequestBody_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final InsertFilmRequestBody mockRequestBody = FilmTestHelper.MOCK_INSERT_REQ_BODY1;
        final String expected = "InsertFilmRequestBody{" +
            "name='" + mockRequestBody.getName() + '\'' +
            ", filmTypeId=" + mockRequestBody.getFilmTypeId() +
            '}';

        // when
        final String result = mockRequestBody.toString();

        // then
        assertEquals(expected, result);

    }
}
