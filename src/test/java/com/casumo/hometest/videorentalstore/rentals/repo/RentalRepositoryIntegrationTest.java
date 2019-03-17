package com.casumo.hometest.videorentalstore.rentals.repo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.casumo.hometest.videorentalstore.customers.CustomerTestHelper;
import com.casumo.hometest.videorentalstore.customers.domain.Customer;
import com.casumo.hometest.videorentalstore.films.FilmTestHelper;
import com.casumo.hometest.videorentalstore.films.domain.Film;
import com.casumo.hometest.videorentalstore.films.domain.FilmType;
import com.casumo.hometest.videorentalstore.films.domain.Price;
import com.casumo.hometest.videorentalstore.rentals.RentalTestHelper;
import com.casumo.hometest.videorentalstore.rentals.domain.Rental;
import com.casumo.hometest.videorentalstore.rentals.domain.RentalItem;


/**
 * RentalRepositoryIntegrationTest class - Integration test for RentalRepository.
 */
@DataJpaTest
class RentalRepositoryIntegrationTest
{
    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Film mockFilm1;
    private Film mockFilm2;
    private Customer mockCustomer1;
    private Customer mockCustomer2;
    private RentalItem mockRentalItem1;
    private RentalItem mockRentalItem2;
    private RentalItem mockRentalItem3;

    @BeforeEach
    void setUp()
    {
        // price + film type
        final Price premiumPrice = FilmTestHelper.generatePrice(FilmTestHelper.MOCK_PREMIUM_PRICE_NAME, FilmTestHelper.MOCK_PREMIUM_PRICE_VALUE);
        final Price basicPrice = FilmTestHelper.generatePrice(FilmTestHelper.MOCK_BASIC_PRICE_NAME, FilmTestHelper.MOCK_BASIC_PRICE_VALUE);
        entityManager.persist(premiumPrice);
        entityManager.persist(basicPrice);
        final FilmType newReleaseFilmType = FilmTestHelper.generateFilmType(FilmTestHelper.MOCK_NEW_FILM_TYPE_NAME, premiumPrice);
        final FilmType regularFilmType = FilmTestHelper.generateFilmType(FilmTestHelper.MOCK_REGULAR_FILM_TYPE_NAME, basicPrice);
        final FilmType oldFilmType = FilmTestHelper.generateFilmType(FilmTestHelper.MOCK_OLD_FILM_TYPE_NAME, basicPrice);
        entityManager.persist(newReleaseFilmType);
        entityManager.persist(regularFilmType);
        entityManager.persist(oldFilmType);

        // film
        mockFilm1 = FilmTestHelper.generateFilm(FilmTestHelper.MOCK_NAME1, oldFilmType);
        mockFilm2 = FilmTestHelper.generateFilm(FilmTestHelper.MOCK_NAME2, newReleaseFilmType);
        entityManager.persist(mockFilm1);
        entityManager.persist(mockFilm2);

        // customer
        mockCustomer1 = CustomerTestHelper.generateCustomer(CustomerTestHelper.MOCK_USERNAME1,
            CustomerTestHelper.MOCK_EMAIL1,
            CustomerTestHelper.MOCK_BONUSPOINTS1);
        mockCustomer2 = CustomerTestHelper.generateCustomer(CustomerTestHelper.MOCK_USERNAME2,
            CustomerTestHelper.MOCK_EMAIL2,
            CustomerTestHelper.MOCK_BONUSPOINTS2);
        entityManager.persist(mockCustomer1);
        entityManager.persist(mockCustomer2);

        entityManager.flush();
    }

    // find all
    @Test
    void givenExistentRentals_whenFindAll_thenReturnAllRentals()
    {
        // given
        mockRentalItem1 = RentalTestHelper.generateRentalItem(mockFilm1,
            RentalTestHelper.MOCK_DAYS_RENTED1,
            RentalTestHelper.MOCK_PRICE1,
            RentalTestHelper.MOCK_SUBCHARGE1,
            RentalTestHelper.MOCK_START_DATETIME1,
            null);
        mockRentalItem2 = RentalTestHelper.generateRentalItem(mockFilm2,
            RentalTestHelper.MOCK_DAYS_RENTED2,
            RentalTestHelper.MOCK_PRICE2,
            RentalTestHelper.MOCK_SUBCHARGE2,
            RentalTestHelper.MOCK_START_DATETIME2,
            null);

        final Rental rental1 =
            RentalTestHelper.generateRental(mockCustomer1, RentalTestHelper.MOCK_START_DATETIME1, Collections.singletonList(mockRentalItem1));
        final Rental rental2 =
            RentalTestHelper.generateRental(mockCustomer2, RentalTestHelper.MOCK_START_DATETIME2, Collections.singletonList(mockRentalItem2));
        final List<Rental> mockRentals = Arrays.asList(rental1, rental2);
        mockRentals.forEach(rental -> entityManager.persistAndFlush(rental));

        // when
        final List<Rental> result = rentalRepository.findAll();

        // then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertThat(result.size(), is(mockRentals.size()));
        assertThat(result, hasItem(rental1));
        assertThat(result, hasItem(rental2));
    }

    // find all - empty collection
    @Test
    void givenEmptyRentals_whenFindAll_thenReturnEmptyCollection()
    {
        // given
        // here empty records in db

        // when
        final List<Rental> result = rentalRepository.findAll();

        // then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
