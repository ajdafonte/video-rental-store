package com.casumo.hometest.videorentalstore.rentals.bizz;

import java.math.BigDecimal;

import com.casumo.hometest.videorentalstore.films.domain.Film;


/**
 * NewReleaseRentalCalculator class.
 */
public class NewReleaseRentalCalculator extends RentalCalculator
{
    private static final int NEW_RELEASE_POINTS = 2;
    private static final int NEW_RELEASE_MIN_RENTAL_DAYS = 5;

    NewReleaseRentalCalculator()
    {
        this.filmPoints = NEW_RELEASE_POINTS;
        this.filmMinRentalDays = NEW_RELEASE_MIN_RENTAL_DAYS;
    }

    @Override
    public BigDecimal rentalPrice(final Film film, final int numDays)
    {
        return defaultRentalCalculation(film, numDays);
    }
}
