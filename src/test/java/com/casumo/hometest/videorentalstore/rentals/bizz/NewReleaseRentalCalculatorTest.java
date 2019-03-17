package com.casumo.hometest.videorentalstore.rentals.bizz;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.films.FilmTestHelper;
import com.casumo.hometest.videorentalstore.films.domain.Film;


/**
 * NewReleaseRentalCalculatorTest class - NewReleaseRentalCalculator test class.
 */
class NewReleaseRentalCalculatorTest
{
    private NewReleaseRentalCalculator rentalCalculator;

    @BeforeEach
    void setUp()
    {
        this.rentalCalculator = new NewReleaseRentalCalculator();
    }

    // rentalPrice
    @Test
    void givenNewReleaseFilm_whenCalculatingRentalPrice_thenReturnExpectedPrice()
    {
        // given
        final Film mockFilm = FilmTestHelper.generateFilm("Gringo", FilmTestHelper.MOCK_NEW_RELEASE_FILM_TYPE);
        final BigDecimal mockPrice = mockFilm.getType().getPrice().getValue();
        final int mockNumDays = 2;
        final BigDecimal expectedRentalPrice = mockPrice.multiply(BigDecimal.valueOf(mockNumDays));

        // when
        final BigDecimal result = rentalCalculator.rentalPrice(mockFilm, mockNumDays);

        // then
        assertThat(result, is(expectedRentalPrice));
    }

    @Test
    void givenInvalidNewReleaseFilm_whenCalculatingRentalPrice_thenReturnZeroPrice()
    {
        // given
        final Film mockFilm = null;
        final BigDecimal expectedRentalPrice = BigDecimal.valueOf(0);

        // when
        final BigDecimal result = rentalCalculator.rentalPrice(mockFilm, 2);

        // then
        assertThat(result, is(expectedRentalPrice));
    }

    // rentalSubcharge
    @Test
    void givenNewReleaseFilm_whenCalculatingRentalSubcharge_thenReturnExpectedSubcharge()
    {
        // given
        final Film mockFilm = FilmTestHelper.generateFilm("Gringo", FilmTestHelper.MOCK_NEW_RELEASE_FILM_TYPE);
        final BigDecimal mockPrice = mockFilm.getType().getPrice().getValue();
        final int mockNumDays = 3;
        final BigDecimal expectedRentalPrice = mockPrice.multiply(BigDecimal.valueOf(mockNumDays));

        // when
        final BigDecimal result = rentalCalculator.rentalSubcharge(mockFilm, mockNumDays);

        // then
        assertThat(result, is(expectedRentalPrice));
    }

    @Test
    void givenInvalidNewReleaseFilm_whenCalculatingRentalSubcharge_thenReturnZeroSubcharge()
    {
        // given
        final Film mockFilm = null;
        final BigDecimal expectedRentalSubcharge = BigDecimal.valueOf(0);

        // when
        final BigDecimal result = rentalCalculator.rentalSubcharge(mockFilm, 2);

        // then
        assertThat(result, is(expectedRentalSubcharge));
    }

    // rentalBonusPoints
    @Test
    void givenNewReleaseFilm_whenCalculatingBonusPoints_thenReturnExpectedBonusPoints()
    {
        // given
        final Film mockFilm = FilmTestHelper.generateFilm("Gringo", FilmTestHelper.MOCK_NEW_RELEASE_FILM_TYPE);
        final int expectedBonusPoints = 2;

        // when
        final int result = rentalCalculator.rentalBonusPoints(mockFilm);

        // then
        assertThat(result, is(expectedBonusPoints));
    }
}
