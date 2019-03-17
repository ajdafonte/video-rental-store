package com.casumo.hometest.videorentalstore.rentals.bizz;

import java.util.List;
import java.util.Objects;


/**
 * InsertRentalParameter class.
 */
public class InsertRentalParameter
{
    private long customerId;

    private List<InsertRentalItemParameter> itemsToRent;

    public InsertRentalParameter()
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

    public List<InsertRentalItemParameter> getItemsToRent()
    {
        return itemsToRent;
    }

    public void setItemsToRent(final List<InsertRentalItemParameter> itemsToRent)
    {
        this.itemsToRent = itemsToRent;
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
        final InsertRentalParameter that = (InsertRentalParameter) o;
        return customerId == that.customerId &&
            Objects.equals(itemsToRent, that.itemsToRent);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(customerId, itemsToRent);
    }

    @Override
    public String toString()
    {
        return "InsertRentalParameter{" +
            "customerId=" + customerId +
            ", itemsToRent=" + itemsToRent +
            '}';
    }
}
