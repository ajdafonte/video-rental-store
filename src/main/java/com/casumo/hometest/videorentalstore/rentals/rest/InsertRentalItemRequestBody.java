package com.casumo.hometest.videorentalstore.rentals.rest;

import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * InsertRentalItemRequestBody class.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(
    value = "InsertRentalItemRequestBody",
    description = "Request body parameter to insert a new rental item."
)
public class InsertRentalItemRequestBody
{
    @ApiModelProperty(value = "The ID of the film.", required = true)
    @Min(value = 1)
    @NotNull
    private long filmId;

    @ApiModelProperty(value = "The number of days to rent the film.", required = true)
    @Min(value = 1)
    @NotNull
    private int daysToRent;

    public InsertRentalItemRequestBody()
    {
    }

    public long getFilmId()
    {
        return filmId;
    }

    public void setFilmId(final long filmId)
    {
        this.filmId = filmId;
    }

    public int getDaysToRent()
    {
        return daysToRent;
    }

    public void setDaysToRent(final int daysToRent)
    {
        this.daysToRent = daysToRent;
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
        final InsertRentalItemRequestBody that = (InsertRentalItemRequestBody) o;
        return filmId == that.filmId &&
            daysToRent == that.daysToRent;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(filmId, daysToRent);
    }

    @Override
    public String toString()
    {
        return "InsertRentalItemRequestBody{" +
            "filmId=" + filmId +
            ", daysToRent=" + daysToRent +
            '}';
    }
}
