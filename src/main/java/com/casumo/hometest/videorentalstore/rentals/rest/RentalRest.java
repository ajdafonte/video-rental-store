package com.casumo.hometest.videorentalstore.rentals.rest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * RentalRest class.
 */
@ApiModel(
    value = "Rental",
    description = "A Rental on the Video Rental Store system."
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RentalRest
{
    @ApiModelProperty(value = "The ID of the rental.")
    private long id;

    @ApiModelProperty(value = "The start date time of the rental.")
    private OffsetDateTime startDateTime;

    @ApiModelProperty(value = "The total price of the rental.")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "The total subcharge of the rental")
    private BigDecimal totalSubcharge;

    @ApiModelProperty(value = "The items of the rental")
    private List<RentalItemRest> rentalItems;

    public RentalRest()
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

    public OffsetDateTime getStartDateTime()
    {
        return startDateTime;
    }

    public void setStartDateTime(final OffsetDateTime startDateTime)
    {
        this.startDateTime = startDateTime;
    }

    public BigDecimal getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(final BigDecimal totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalSubcharge()
    {
        return totalSubcharge;
    }

    public void setTotalSubcharge(final BigDecimal totalSubcharge)
    {
        this.totalSubcharge = totalSubcharge;
    }

    public List<RentalItemRest> getRentalItems()
    {
        return rentalItems;
    }

    public void setRentalItems(final List<RentalItemRest> rentalItems)
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
        final RentalRest that = (RentalRest) o;
        return id == that.id &&
            Objects.equals(startDateTime, that.startDateTime) &&
            Objects.equals(totalPrice, that.totalPrice) &&
            Objects.equals(totalSubcharge, that.totalSubcharge) &&
            Objects.equals(rentalItems, that.rentalItems);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id,
            startDateTime,
            totalPrice, totalSubcharge, rentalItems);
    }

    @Override
    public String toString()
    {
        return "RentalRest{" +
            "id=" + id +
            ", startDateTime=" + startDateTime +
            ", totalPrice=" + totalPrice +
            ", totalSubcharge=" + totalSubcharge +
            ", rentalItems=" + rentalItems +
            '}';
    }
}
