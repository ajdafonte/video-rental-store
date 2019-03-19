package com.casumo.hometest.videorentalstore.films.rest.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.films.FilmTestHelper;
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
        // given
        final FilmType mockFilmType = FilmTestHelper.MOCK_REGULAR_FILM_TYPE;

        // when
        final FilmTypeRest result = FilmTypeRestMapper.map(mockFilmType);

        // then
        assertNotNull(result);
        assertThat(result.getId(), is(mockFilmType.getId()));
        assertThat(result.getName(), is(mockFilmType.getName()));
    }

    // map - null
    @Test
    void givenNullFilmType_whenMapToRest_thenReturnNullValue()
    {
        assertNull(FilmTypeRestMapper.map(null));
    }
}
