package com.casumo.hometest.videorentalstore.rentals.rest.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.casumo.hometest.videorentalstore.rentals.bizz.InsertRentalItemParameter;
import com.casumo.hometest.videorentalstore.rentals.bizz.InsertRentalParameter;
import com.casumo.hometest.videorentalstore.rentals.rest.InsertRentalItemRequestBody;
import com.casumo.hometest.videorentalstore.rentals.rest.InsertRentalRequestBody;


/**
 * InsertRentalParameterMapper class.
 */
public class InsertRentalParameterMapper
{
    public static InsertRentalParameter map(final InsertRentalRequestBody requestBody)
    {
        if (requestBody != null)
        {
            final InsertRentalParameter parameter = new InsertRentalParameter();
            parameter.setCustomerId(requestBody.getCustomerId());

            final List<InsertRentalItemRequestBody> rentalItemsRequestBody = requestBody.getRentalItems();
            parameter.setItemsToRent(rentalItemsRequestBody.stream()
                .map(InsertRentalParameterMapper::map)
                .collect(Collectors.toList()));

            return parameter;
        }
        return null;
    }

    static InsertRentalItemParameter map(final InsertRentalItemRequestBody requestBody)
    {
        if (requestBody != null)
        {
            final InsertRentalItemParameter parameter = new InsertRentalItemParameter();
            parameter.setFilmId(requestBody.getFilmId());
            parameter.setDaysToRent(requestBody.getDaysToRent());
            return parameter;
        }
        return null;
    }
}
