package com.casumo.hometest.videorentalstore.rentals.bizz;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.films.FilmTestHelper;
import com.casumo.hometest.videorentalstore.films.domain.FilmType;


/**
 * RentalCalculatorFactoryTest class - RentalCalculatorFactory test class.
 */
class RentalCalculatorFactoryTest
{
    @Test
    void givenNewReleaseFilmType_whenGetSpecificRentalCalculator_thenReturnCorrectCalculator()
    {
        // given
        final FilmType mockFilmType = FilmTestHelper.MOCK_NEW_RELEASE_FILM_TYPE;

        // when
        final RentalCalculator result = RentalCalculatorFactory.getRentalCalculator(mockFilmType);

        // then
        assertNotNull(result);
        assertThat(result, instanceOf(NewReleaseRentalCalculator.class));
    }

    @Test
    void givenRegularFilmType_whenGetSpecificRentalCalculator_thenReturnCorrectCalculator()
    {
        // given
        final FilmType mockFilmType = FilmTestHelper.MOCK_REGULAR_FILM_TYPE;

        // when
        final RentalCalculator result = RentalCalculatorFactory.getRentalCalculator(mockFilmType);

        // then
        assertNotNull(result);
        assertThat(result, instanceOf(RegularFilmRentalCalculator.class));
    }

    @Test
    void givenOldFilmType_whenGetSpecificRentalCalculator_thenReturnCorrectCalculator()
    {
        // given
        final FilmType mockFilmType = FilmTestHelper.MOCK_OLD_FILM_TYPE;

        // when
        final RentalCalculator result = RentalCalculatorFactory.getRentalCalculator(mockFilmType);

        // then
        assertNotNull(result);
        assertThat(result, instanceOf(OldFilmRentalCalculator.class));
    }

    @Test
    void givenInvalidFilmType_whenGetSpecificRentalCalculator_thenReturnNullValue()
    {
        // given
        final FilmType[] mockFilmTypes = {null, FilmTestHelper.generateFilmType("unknown", FilmTestHelper.MOCK_PREMIUM_PRICE)};

        // when + then
        for (final FilmType filmType : mockFilmTypes)
        {
            assertNull(RentalCalculatorFactory.getRentalCalculator(filmType));
        }
    }
}
