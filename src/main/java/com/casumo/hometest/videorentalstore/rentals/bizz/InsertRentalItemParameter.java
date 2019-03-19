package com.casumo.hometest.videorentalstore.rentals.bizz;

import java.util.Objects;


/**
 * InsertRentalItemParameter class.
 */
public class InsertRentalItemParameter
{
    private long filmId;
    private int daysToRent;

    public InsertRentalItemParameter()
    {
    }

    long getFilmId()
    {
        return filmId;
    }

    public void setFilmId(final long filmId)
    {
        this.filmId = filmId;
    }

    int getDaysToRent()
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
        final InsertRentalItemParameter that = (InsertRentalItemParameter) o;
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
        return "InsertRentalItemParameter{" +
            "filmId=" + filmId +
            ", daysToRent=" + daysToRent +
            '}';
    }
}
