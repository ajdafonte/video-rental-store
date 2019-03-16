package com.casumo.hometest.videorentalstore.films.rest.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.films.FilmTestHelper;
import com.casumo.hometest.videorentalstore.films.domain.Film;
import com.casumo.hometest.videorentalstore.films.domain.FilmType;
import com.casumo.hometest.videorentalstore.films.rest.FilmRest;


/**
 * FilmRestMapperTest class - FilmRestMapper test class.
 */
class FilmRestMapperTest
{
    // map - ok
    @Test
    void givenValidFilm_whenMapToRest_thenReturnFilmRestObject()
    {
        // given
        final Film mockFilm = FilmTestHelper.MOCK_FILM2;
        final FilmType mockFilmType = mockFilm.getType();

        // when
        final FilmRest result = FilmRestMapper.map(mockFilm);

        // then
        assertNotNull(result);
        assertThat(result.getId(), is(mockFilm.getId()));
        assertThat(result.getName(), is(mockFilm.getName()));
        assertThat(result.getType(), is(FilmTypeRestMapper.map(mockFilmType)));
        assertThat(result.getType().getId(), is(mockFilmType.getId()));
        assertThat(result.getType().getName(), is(mockFilmType.getName()));
    }

    // map - null
    @Test
    void givenNullFilm_whenMapToRest_thenReturnNullValue()
    {
        // given + when + then
        assertNull(FilmRestMapper.map(null));
    }

//    // mapToBizz - ok
//    @Test
//    void givenValidInsertFilmRequestBody_whenMapToBizz_thenReturnFilmObject()
//    {
//        // given
//        final InsertFilmRequestBody mockRequestBody = FilmTestHelper.MOCK_INSERT_REQ_BODY1;
//
//        // when
//        final Film result = FilmRestMapper.mapToBizz(mockRequestBody);
//
//        // then
//        assertNotNull(result);
//        assertThat(result.getName(), is(mockRequestBody.getName()));
//        assertThat(result.getType(), is(FilmTypeRestMapper.mapToBizz(mockRequestBody.getFilmTypeId())));
//    }
//
//    // mapToBizz - null
//    @Test
//    void givenNullInsertFilmRequestBody_whenMapToBizz_thenReturnNullValue()
//    {
//        // given + when + then
//        assertNull(FilmRestMapper.mapToBizz(null));
//    }
}
