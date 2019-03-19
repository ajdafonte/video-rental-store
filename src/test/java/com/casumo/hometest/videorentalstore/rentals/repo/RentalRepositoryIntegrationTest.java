package com.casumo.hometest.videorentalstore.rentals.repo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        final Film mockFilm1 = FilmTestHelper.generateFilm(FilmTestHelper.MOCK_OLD_NAME, oldFilmType);
        final Film mockFilm2 = FilmTestHelper.generateFilm(FilmTestHelper.MOCK_NEW_RELEASE_NAME, newReleaseFilmType);
        final Film mockFilm3 = FilmTestHelper.generateFilm(FilmTestHelper.MOCK_REGULAR_NAME, regularFilmType);
        entityManager.persist(mockFilm1);
        entityManager.persist(mockFilm2);
        entityManager.persist(mockFilm3);

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

        // init rental items
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
        mockRentalItem3 = RentalTestHelper.generateRentalItem(mockFilm3,
            RentalTestHelper.MOCK_DAYS_RENTED2,
            RentalTestHelper.MOCK_PRICE2,
            RentalTestHelper.MOCK_SUBCHARGE2,
            RentalTestHelper.MOCK_START_DATETIME2,
            null);
    }

    // find all
    @Test
    void givenExistentRentals_whenFindAll_thenReturnAllRentals()
    {
        // given
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

    // findById - ok
    @Test
    void givenExistentRentalId_whenFindById_thenReturnExistentRental()
    {
        // given
        final Rental mockRental =
            RentalTestHelper.generateRental(mockCustomer1, RentalTestHelper.MOCK_START_DATETIME1, Arrays.asList(mockRentalItem1, mockRentalItem2));
        entityManager.persistAndFlush(mockRental);
        final Long id = (Long) entityManager.getId(mockRental);

        // when
        final Optional<Rental> result = rentalRepository.findById(id);

        // then
        assertNotNull(result);
        final Rental rental = result.get();
        assertThat(rental.getId(), is(id));
        assertThat(rental.getCustomer(), is(mockCustomer1));
        assertThat(rental.getStartdatetime(), is(mockRental.getStartdatetime()));
        assertThat(rental.getRentalItems(), is(mockRental.getRentalItems()));
        assertThat(rental.getRentalItems(), containsInAnyOrder(mockRentalItem1, mockRentalItem2));
    }

    // findById - nok (id not found)
    @Test
    void givenNonexistentRentalId_whenFindById_thenThrowSpecificException()
    {
        // given
        final Rental mockRental =
            RentalTestHelper.generateRental(mockCustomer1, RentalTestHelper.MOCK_START_DATETIME1, Arrays.asList(mockRentalItem1, mockRentalItem2));
        entityManager.persistAndFlush(mockRental);

        // when
        final Optional<Rental> result = rentalRepository.findById(RentalTestHelper.MOCK_UNKNOWN_ID);

        // + then
        assertFalse(result.isPresent());
    }

    // save - ok
    @Test
    void givenNewRental_whenSaveRental_thenReturnNewRentalId()
    {
        // given
        final Rental rental1 =
            RentalTestHelper.generateRental(mockCustomer1, RentalTestHelper.MOCK_START_DATETIME1, Collections.singletonList(mockRentalItem1));
        final Rental rental2 =
            RentalTestHelper.generateRental(mockCustomer2, RentalTestHelper.MOCK_START_DATETIME2, Collections.singletonList(mockRentalItem2));
        final List<Rental> mockRentals = Arrays.asList(rental1, rental2);
        mockRentals.forEach(rental -> entityManager.persistAndFlush(rental));

        // when
        final Rental mockRental =
            RentalTestHelper.generateRental(mockCustomer1, RentalTestHelper.MOCK_START_DATETIME2, Collections.singletonList(mockRentalItem3));
        final Rental result = rentalRepository.save(mockRental);

        // then
        assertThat(result, notNullValue());
        assertThat(result.getId(), is(mockRental.getId()));
        assertThat(result.getCustomer(), is(mockCustomer1));
        assertThat(result.getStartdatetime(), is(mockRental.getStartdatetime()));
        assertThat(result.getRentalItems(), is(mockRental.getRentalItems()));
        assertThat(result.getRentalItems(), containsInAnyOrder(mockRentalItem3));
        final List<Rental> allRentals = rentalRepository.findAll();
        final int expectedNumRentals = mockRentals.size() + 1;
        assertThat(allRentals.size(), is(expectedNumRentals));
    }
}
