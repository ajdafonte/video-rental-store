package com.casumo.hometest.videorentalstore.rentals.rest;

import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * PatchRentalRequestBody class.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(
    value = "PatchRentalRequestBody",
    description = "Request body parameter to perform a patch operation in a rental object."
)
public class PatchRentalRequestBody
{
    @ApiModelProperty(value = "The list of rental items ID that should be return from renting.", required = true)
    @NotNull
    private List<@Positive Long> rentalItemsToReturn;

    public PatchRentalRequestBody()
    {
    }

    public List<Long> getRentalItemsToReturn()
    {
        return rentalItemsToReturn;
    }

    public void setRentalItemsToReturn(final List<Long> rentalItemsToReturn)
    {
        this.rentalItemsToReturn = rentalItemsToReturn;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        final PatchRentalRequestBody that = (PatchRentalRequestBody) o;
        return Objects.equals(rentalItemsToReturn, that.rentalItemsToReturn);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(rentalItemsToReturn);
    }

    @Override
    public String toString()
    {
        return "PatchRentalRequestBody{" +
            "rentalItemsToReturn=" + rentalItemsToReturn +
            '}';
    }
}
