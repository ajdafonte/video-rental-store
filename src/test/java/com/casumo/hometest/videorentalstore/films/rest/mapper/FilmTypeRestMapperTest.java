package com.casumo.hometest.videorentalstore.films.rest.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.films.domain.FilmType;
import com.casumo.hometest.videorentalstore.films.rest.FilmTypeRest;


/**
 * FilmTypeRestMapperTest class - FilmTypeRestMapper test class.
 */
class FilmTypeRestMapperTest
{
    // map - ok
    @Test
    void givenValidFilmType_whenMapToRest_thenReturnFilmTypeRest()
    {
        assertThat(FilmTypeRestMapper.map(FilmType.NEW_RELEASE), is(FilmTypeRest.NEW_RELEASE));
        assertThat(FilmTypeRestMapper.map(FilmType.REGULAR), is(FilmTypeRest.REGULAR));
        assertThat(FilmTypeRestMapper.map(FilmType.OLD), is(FilmTypeRest.OLD));
    }

    // map - null
    @Test
    void givenNullFilmType_whenMapToRest_thenReturnNullValue()
    {
        assertNull(FilmTypeRestMapper.map(null));
    }

    // mapToBizz - ok
    @Test
    void givenValidFilmTypeRest_whenMapToBizz_thenReturnFilmType()
    {
        assertThat(FilmTypeRestMapper.mapToBizz(FilmTypeRest.NEW_RELEASE), is(FilmType.NEW_RELEASE));
        assertThat(FilmTypeRestMapper.mapToBizz(FilmTypeRest.REGULAR), is(FilmType.REGULAR));
        assertThat(FilmTypeRestMapper.mapToBizz(FilmTypeRest.OLD), is(FilmType.OLD));
    }

    // mapToBizz - nok
    @Test
    void givenNullFilmTypeRest_whenMapToBizz_thenReturnNullValue()
    {
        assertNull(FilmTypeRestMapper.mapToBizz(null));
    }
}
