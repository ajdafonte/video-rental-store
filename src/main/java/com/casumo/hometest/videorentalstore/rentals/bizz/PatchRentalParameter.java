package com.casumo.hometest.videorentalstore.rentals.bizz;

import java.util.List;
import java.util.Objects;


/**
 * PatchRentalParameter class.
 */
public class PatchRentalParameter
{
    private long rentalId;

    private List<Long> rentalItemsToReturn;

    public PatchRentalParameter()
    {
    }

    public long getRentalId()
    {
        return rentalId;
    }

    public void setRentalId(final long rentalId)
    {
        this.rentalId = rentalId;
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
        final PatchRentalParameter that = (PatchRentalParameter) o;
        return rentalId == that.rentalId &&
            Objects.equals(rentalItemsToReturn, that.rentalItemsToReturn);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(rentalId, rentalItemsToReturn);
    }

    @Override
    public String toString()
    {
        return "PatchRentalParameter{" +
            "rentalId=" + rentalId +
            ", rentalItemsToReturn=" + rentalItemsToReturn +
            '}';
    }
}
