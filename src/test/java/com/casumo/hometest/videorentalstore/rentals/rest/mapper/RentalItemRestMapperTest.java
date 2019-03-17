package com.casumo.hometest.videorentalstore.rentals.rest.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.common.infra.MappingTool;
import com.casumo.hometest.videorentalstore.films.domain.Film;
import com.casumo.hometest.videorentalstore.films.rest.mapper.FilmRestMapper;
import com.casumo.hometest.videorentalstore.rentals.RentalTestHelper;
import com.casumo.hometest.videorentalstore.rentals.domain.RentalItem;
import com.casumo.hometest.videorentalstore.rentals.rest.RentalItemRest;


/**
 * RentalItemRestMapperTest class - RentalItemRestMapper test class.
 */
class RentalItemRestMapperTest
{
    // map - ok
    @Test
    void givenValidRentalItem_whenMapToRest_thenReturnRentalItemRestObject()
    {
        // given
        final RentalItem mockRentalItem = RentalTestHelper.MOCK_RENTAL_ITEM2;
        final Film mockFilm = mockRentalItem.getFilm();

        // when
        final RentalItemRest result = RentalItemRestMapper.map(mockRentalItem);

        // then
        assertNotNull(result);
        assertThat(result.getId(), is(mockRentalItem.getId()));
        assertThat(result.getFilm(), is(FilmRestMapper.map(mockFilm)));
        assertThat(result.getDaysRented(), is(mockRentalItem.getDaysrented()));
        assertThat(result.getPrice(), is(mockRentalItem.getPrice()));
        assertThat(result.getSubcharge(), is(mockRentalItem.getSubcharge()));
        assertThat(result.getStartDateTime(), is(MappingTool.offsetDateTimeOrNull(mockRentalItem.getStartdatetime())));
        assertThat(result.getEndDateTime(), is(MappingTool.offsetDateTimeOrNull(mockRentalItem.getEnddatetime())));
    }

    // map - null
    @Test
    void givenNullRentalItem_whenMapToRest_thenReturnNullValue()
    {
        // given + when + then
        assertNull(RentalItemRestMapper.map(null));
    }
}
