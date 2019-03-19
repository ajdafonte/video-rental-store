package com.casumo.hometest.videorentalstore.rentals.bizz;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

import com.casumo.hometest.videorentalstore.common.infra.MappingTool;
import com.casumo.hometest.videorentalstore.films.domain.Film;
import com.casumo.hometest.videorentalstore.films.domain.FilmType;
import com.casumo.hometest.videorentalstore.rentals.domain.RentalItem;


/**
 * RentalCalculator interface.
 */
abstract class RentalCalculator
{
    int filmPoints;
    int filmMinRentalDays;

    BigDecimal calculatePrice(final Film film, final int numDays)
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

    BigDecimal calculateSurcharge(final Film film, final int extraDays)
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

    long calculateNumberOfExtraDays(final RentalItem rentalItem, final OffsetDateTime endDateTime)
    {
        final OffsetDateTime startRentalTime = MappingTool.offsetDateTimeOrNull(rentalItem.getStartdatetime());
        final OffsetDateTime limitDeliverRentalTime = startRentalTime.plus(rentalItem.getDaysrented(), ChronoUnit.DAYS);
        return ChronoUnit.DAYS.between(limitDeliverRentalTime, endDateTime);
    }

    int calculateBonusPoints()
    {
        return filmPoints;
    }
}
