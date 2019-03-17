package com.casumo.hometest.videorentalstore.rentals.bizz;

/**
 * NewReleaseRentalCalculator class.
 */
class OldFilmRentalCalculator extends RentalCalculator
{
    private static final int OLD_FILM_POINTS = 1;
    private static final int OLD_FILM_MIN_RENTAL_DAYS = 5;

    OldFilmRentalCalculator()
    {
        this.filmPoints = OLD_FILM_POINTS;
        this.filmMinRentalDays = OLD_FILM_MIN_RENTAL_DAYS;
    }
}
