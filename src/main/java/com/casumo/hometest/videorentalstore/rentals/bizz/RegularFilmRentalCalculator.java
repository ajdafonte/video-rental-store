package com.casumo.hometest.videorentalstore.rentals.bizz;

/**
 * RegularFilmRentalCalculator class.
 */
class RegularFilmRentalCalculator extends RentalCalculator
{
    private static final int REGULAR_FILM_POINTS = 1;
    private static final int REGULAR_FILM_MIN_RENTAL_DAYS = 3;

    RegularFilmRentalCalculator()
    {
        this.filmPoints = REGULAR_FILM_POINTS;
        this.filmMinRentalDays = REGULAR_FILM_MIN_RENTAL_DAYS;
    }
}
