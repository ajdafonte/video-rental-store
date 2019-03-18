package com.casumo.hometest.videorentalstore.rentals.rest.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.rentals.RentalTestHelper;
import com.casumo.hometest.videorentalstore.rentals.bizz.PatchRentalParameter;
import com.casumo.hometest.videorentalstore.rentals.rest.PatchRentalRequestBody;


/**
 * PatchRentalParameterMapperTest class - PatchRentalParameterMapper test class.
 */
class PatchRentalParameterMapperTest
{
    // map - ok
    @Test
    void givenValidPatchRentalRequestBody_whenMapToParameter_thenReturnPatchRentalParameter()
    {
        // given
        final PatchRentalRequestBody mockRequestBody = RentalTestHelper.MOCK_PATCH_RENTAL_REQUEST_BODY1;
        final long mockRentalId = RentalTestHelper.MOCK_ID2;

        // when
        final PatchRentalParameter result = PatchRentalParameterMapper.map(mockRentalId, mockRequestBody);

        // then
        assertNotNull(result);
        assertThat(result.getRentalId(), is(mockRentalId));
        assertThat(result.getRentalItemsToReturn(), is(mockRequestBody.getRentalItemsToReturn()));
    }

    // map - null
    @Test
    void givenNullPatchRentalParameter_whenMapToRest_thenReturnNullValue()
    {
        assertNull(PatchRentalParameterMapper.map(RentalTestHelper.MOCK_ID2, null));
    }
}
