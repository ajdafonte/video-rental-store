package com.casumo.hometest.videorentalstore.rentals.rest.mapper;

import com.casumo.hometest.videorentalstore.rentals.bizz.PatchRentalParameter;
import com.casumo.hometest.videorentalstore.rentals.rest.PatchRentalRequestBody;


/**
 *
 */
public class PatchRentalParameterMapper
{
    public static PatchRentalParameter map(final long id, final PatchRentalRequestBody requestBody)
    {
        if (requestBody != null)
        {
            final PatchRentalParameter parameter = new PatchRentalParameter();
            parameter.setRentalId(id);
            parameter.setRentalItemsToReturn(requestBody.getRentalItemsToReturn());
            return parameter;
        }
        return null;
    }
}
