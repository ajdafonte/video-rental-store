package com.casumo.hometest.videorentalstore.rentals.rest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

import com.casumo.hometest.videorentalstore.films.rest.FilmRest;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * RentalItemRest class.
 */
@ApiModel(
    value = "RentalItem",
    description = "A Rental Item on the Video Rental Store system."
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RentalItemRest
{
    @ApiModelProperty(value = "The ID of the rental item.")
    private long id;

    @ApiModelProperty(value = "The film of the rental item.")
    private FilmRest film;

    @ApiModelProperty(value = "The number of rented days of the rental item.")
    private int daysRented;

    @ApiModelProperty(value = "The price of the rental item.")
    private BigDecimal price;

    @ApiModelProperty(value = "The subcharge associated to the rental item.")
    private BigDecimal subcharge;

    @ApiModelProperty(value = "The start date time of the rental.")
    private OffsetDateTime startDateTime;

    @ApiModelProperty(value = "The end date time of the rental.")
    private OffsetDateTime endDateTime;

    public RentalItemRest()
    {
    }

    public long getId()
    {
        return id;
    }

    public void setId(final long id)
    {
        this.id = id;
    }

    public FilmRest getFilm()
    {
        return film;
    }

    public void setFilm(final FilmRest film)
    {
        this.film = film;
    }

    public int getDaysRented()
    {
        return daysRented;
    }

    public void setDaysRented(final int daysRented)
    {
        this.daysRented = daysRented;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(final BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getSubcharge()
    {
        return subcharge;
    }

    public void setSubcharge(final BigDecimal subcharge)
    {
        this.subcharge = subcharge;
    }

    public OffsetDateTime getStartDateTime()
    {
        return startDateTime;
    }

    public void setStartDateTime(final OffsetDateTime startDateTime)
    {
        this.startDateTime = startDateTime;
    }

    public OffsetDateTime getEndDateTime()
    {
        return endDateTime;
    }

    public void setEndDateTime(final OffsetDateTime endDateTime)
    {
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString()
    {
        return "RentalItemRest{" +
            "id=" + id +
            ", film=" + film +
            ", daysRented=" + daysRented +
            ", price=" + price +
            ", subcharge=" + subcharge +
            ", startDateTime=" + startDateTime +
            ", endDateTime=" + endDateTime +
            '}';
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
        final RentalItemRest that = (RentalItemRest) o;
        return id == that.id &&
            daysRented == that.daysRented &&
            Objects.equals(film, that.film) &&
            Objects.equals(price, that.price) &&
            Objects.equals(subcharge, that.subcharge) &&
            Objects.equals(startDateTime, that.startDateTime) &&
            Objects.equals(endDateTime, that.endDateTime);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, film, daysRented, price, subcharge, startDateTime, endDateTime);
    }
}
