package com.casumo.hometest.videorentalstore.rentals.rest.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.rentals.RentalTestHelper;
import com.casumo.hometest.videorentalstore.rentals.bizz.InsertRentalItemParameter;
import com.casumo.hometest.videorentalstore.rentals.bizz.InsertRentalParameter;
import com.casumo.hometest.videorentalstore.rentals.rest.InsertRentalItemRequestBody;
import com.casumo.hometest.videorentalstore.rentals.rest.InsertRentalRequestBody;


/**
 * InsertRentalParameterMapperTest class - InsertRentalParameterMapper test class.
 */
class InsertRentalParameterMapperTest
{
    // map - ok
    @Test
    void givenValidInsertRentalRequestBody_whenMapToParameter_thenReturnInsertRentalParameter()
    {
        // given
        final InsertRentalRequestBody mockRequestBody = RentalTestHelper.MOCK_INSERT_RENTAL_REQUEST_BODY1;
        final List<InsertRentalItemRequestBody> mockItemsRequestBody = mockRequestBody.getRentalItems();
        final List<InsertRentalItemParameter> expectedRentalItems = mockItemsRequestBody.stream()
            .map(InsertRentalParameterMapper::map)
            .collect(Collectors.toList());

        // when
        final InsertRentalParameter result = InsertRentalParameterMapper.map(mockRequestBody);

        // then
        assertNotNull(result);
        assertThat(result.getCustomerId(), is(mockRequestBody.getCustomerId()));
        assertThat(result.getItemsToRent(), is(expectedRentalItems));
    }

    // map - null
    @Test
    void givenNullInsertRentalParameter_whenMapToRest_thenReturnNullValue()
    {
        assertNull(InsertRentalParameterMapper.map((InsertRentalRequestBody) null));
    }

    // map - null
    @Test
    void givenNullInsertRentalItemParameter_whenMapToRest_thenReturnNullValue()
    {
        assertNull(InsertRentalParameterMapper.map((InsertRentalItemRequestBody) null));
    }

}
