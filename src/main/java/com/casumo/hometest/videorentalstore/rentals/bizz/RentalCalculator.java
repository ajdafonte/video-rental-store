package com.casumo.hometest.videorentalstore.rentals.bizz;

import java.math.BigDecimal;

import com.casumo.hometest.videorentalstore.films.domain.Film;
import com.casumo.hometest.videorentalstore.films.domain.FilmType;


/**
 * RentalCalculator interface.
 */
abstract class RentalCalculator
{
    int filmPoints;
    int filmMinRentalDays;

    BigDecimal rentalPrice(final Film film, final int numDays)
    {
        if (film != null)
        {
            final int extraDays;
            final FilmType filmType = film.getType();
            final BigDecimal filmPrice = filmType.getPrice().getValue();
            if (numDays > filmMinRentalDays)
            {
                extraDays = numDays - filmMinRentalDays;
                return filmPrice.add(filmPrice.multiply(BigDecimal.valueOf(extraDays)));
            }
            return filmPrice;
        }
        return BigDecimal.valueOf(0);
    }

    BigDecimal rentalSubcharge(final Film film, final int extraDays)
    {
        return defaultRentalCalculation(film, extraDays);
    }

    BigDecimal defaultRentalCalculation(final Film film, final int numDays)
    {
        if (film != null)
        {
            final FilmType filmType = film.getType();
            final BigDecimal filmPrice = filmType.getPrice().getValue();

            return filmPrice.multiply(BigDecimal.valueOf(numDays));
        }

        return BigDecimal.valueOf(0);
    }

    int rentalBonusPoints(final Film film)
    {
        return filmPoints;
    }
}
