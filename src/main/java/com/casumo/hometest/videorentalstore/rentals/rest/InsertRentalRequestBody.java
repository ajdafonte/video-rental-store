package com.casumo.hometest.videorentalstore.rentals.rest;

import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * InsertRentalRequestBody class.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(
    value = "InsertRentalRequestBody",
    description = "Request body parameter to insert a new rental."
)
public class InsertRentalRequestBody
{
    @ApiModelProperty(value = "The ID of the customer.", required = true)
    @NotNull
    private long customerId;

    @ApiModelProperty("The list of items that should be rented.")
    @NotNull
    private List<InsertRentalItemRequestBody> rentalItems;

    public InsertRentalRequestBody()
    {
    }

    public long getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(final long customerId)
    {
        this.customerId = customerId;
    }

    public List<InsertRentalItemRequestBody> getRentalItems()
    {
        return rentalItems;
    }

    public void setRentalItems(final List<InsertRentalItemRequestBody> rentalItems)
    {
        this.rentalItems = rentalItems;
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
        final InsertRentalRequestBody that = (InsertRentalRequestBody) o;
        return customerId == that.customerId &&
            Objects.equals(rentalItems, that.rentalItems);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(customerId, rentalItems);
    }

    @Override
    public String toString()
    {
        return "InsertRentalRequestBody{" +
            "customerId=" + customerId +
            ", rentalItems=" + rentalItems +
            '}';
    }
}
