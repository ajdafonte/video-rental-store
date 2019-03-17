package com.casumo.hometest.videorentalstore.rentals.bizz;

import org.springframework.stereotype.Component;

import com.casumo.hometest.videorentalstore.films.domain.FilmType;


/**
 * RentalCalculatorFactory
 */
@Component
public class RentalCalculatorFactory
{
    private static final NewReleaseRentalCalculator NEW_RELEASE_CALCULATOR = new NewReleaseRentalCalculator();
    private static final RegularFilmRentalCalculator REGULAR_FILM_CALCULATOR = new RegularFilmRentalCalculator();
    private static final OldFilmRentalCalculator OLD_FILM_CALCULATOR = new OldFilmRentalCalculator();

    static RentalCalculator getRentalCalculator(final FilmType type)
    {
        if (type != null)
        {
            switch (type.getName())
            {
                case "New Release":
                    return NEW_RELEASE_CALCULATOR;
                case "Regular":
                    return REGULAR_FILM_CALCULATOR;
                case "Old":
                    return OLD_FILM_CALCULATOR;
            }
        }
        return null;
    }
}
