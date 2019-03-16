package com.casumo.hometest.videorentalstore.films.rest.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.films.FilmTestHelper;
import com.casumo.hometest.videorentalstore.films.bizz.InsertFilmParameter;
import com.casumo.hometest.videorentalstore.films.rest.InsertFilmRequestBody;


/**
 * FilmTypeRestMapperTest class - FilmTypeRestMapper test class.
 */
class InsertFilmParameterMapperTest
{
    // map - ok
    @Test
    void givenValidInsertFilmRequestBody_whenMapToParameter_thenReturnInsertFilmParameter()
    {
        // given
        final InsertFilmRequestBody mockRequestBody = FilmTestHelper.MOCK_INSERT_REQ_BODY1;

        // when
        final InsertFilmParameter result = InsertFilmParameterMapper.map(mockRequestBody);

        // then
        assertNotNull(result);
        assertThat(result.getName(), is(mockRequestBody.getName()));
        assertThat(result.getFilmTypeId(), is(mockRequestBody.getFilmTypeId()));
    }

    // map - null
    @Test
    void givenNullInsertFilmParameter_whenMapToRest_thenReturnNullValue()
    {
        assertNull(InsertFilmParameterMapper.map(null));
    }

//    // mapToBizz - ok
//    @Test
//    void givenValidFilmTypeRest_whenMapToBizz_thenReturnFilmType()
//    {
//        assertThat(FilmTypeRestMapper.mapToBizz(FilmTypeRest.NEW_RELEASE), is(FilmType.NEW_RELEASE));
//        assertThat(FilmTypeRestMapper.mapToBizz(FilmTypeRest.REGULAR), is(FilmType.REGULAR));
//        assertThat(FilmTypeRestMapper.mapToBizz(FilmTypeRest.OLD), is(FilmType.OLD));
//    }
//
//    // mapToBizz - nok
//    @Test
//    void givenNullFilmTypeRest_whenMapToBizz_thenReturnNullValue()
//    {
//        assertNull(FilmTypeRestMapper.mapToBizz(null));
//    }
}
