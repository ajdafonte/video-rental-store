package com.casumo.hometest.videorentalstore.films.repo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;

import com.casumo.hometest.videorentalstore.films.FilmTestHelper;
import com.casumo.hometest.videorentalstore.films.domain.Film;
import com.casumo.hometest.videorentalstore.films.domain.FilmType;
import com.casumo.hometest.videorentalstore.films.domain.Price;


/**
 * FilmRepositoryIntegrationTest class - Integration test for FilmRepository.
 */
@DataJpaTest()
class FilmRepositoryIntegrationTest
{
    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private TestEntityManager entityManager;

    private FilmType newReleaseFilmType;
    private FilmType regularFilmType;
    private FilmType oldFilmType;

    @BeforeEach
    void setUp()
    {
        final Price premiumPrice = FilmTestHelper.generatePrice(FilmTestHelper.MOCK_PREMIUM_PRICE_NAME, FilmTestHelper.MOCK_PREMIUM_PRICE_VALUE);
        final Price basicPrice = FilmTestHelper.generatePrice(FilmTestHelper.MOCK_BASIC_PRICE_NAME, FilmTestHelper.MOCK_BASIC_PRICE_VALUE);
        entityManager.persist(premiumPrice);
        entityManager.persist(basicPrice);
        newReleaseFilmType = FilmTestHelper.generateFilmType(FilmTestHelper.MOCK_NEW_FILM_TYPE_NAME, premiumPrice);
        regularFilmType = FilmTestHelper.generateFilmType(FilmTestHelper.MOCK_REGULAR_FILM_TYPE_NAME, basicPrice);
        oldFilmType = FilmTestHelper.generateFilmType(FilmTestHelper.MOCK_OLD_FILM_TYPE_NAME, basicPrice);
        entityManager.persist(newReleaseFilmType);
        entityManager.persist(regularFilmType);
        entityManager.persist(oldFilmType);
        entityManager.flush();
    }

    // find all
    @Test
    void givenExistentFilms_whenFindAll_thenReturnAllFilms()
    {
        // given
        final Film film1 = FilmTestHelper.generateFilm(FilmTestHelper.MOCK_OLD_NAME, oldFilmType);
        final Film film2 = FilmTestHelper.generateFilm(FilmTestHelper.MOCK_NEW_RELEASE_NAME, newReleaseFilmType);
        final List<Film> mockFilms = Arrays.asList(film1, film2);
        mockFilms.forEach(film -> entityManager.persistAndFlush(film));

        // when
        final List<Film> result = filmRepository.findAll();

        // then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertThat(result.size(), is(mockFilms.size()));
        assertThat(result, hasItem(film1));
        assertThat(result, hasItem(film2));
    }

    // find all - empty collection
    @Test
    void givenEmptyFilms_whenFindAll_thenReturnEmptyCollection()
    {
        // given
        // here empty records in db

        // when
        final List<Film> result = filmRepository.findAll();

        // then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    // findById - ok
    @Test
    void givenExistentFilmId_whenFindById_thenReturnExistentFilm()
    {
        // given
        final Film mockFilm = FilmTestHelper.generateFilm(FilmTestHelper.MOCK_OLD_NAME, oldFilmType);
        entityManager.persistAndFlush(mockFilm);
        final Long id = (Long) entityManager.getId(mockFilm);

        // when
        final Optional<Film> result = filmRepository.findById(id);

        // then
        assertNotNull(result);
        final Film film = result.get();
        assertThat(film.getId(), is(id));
        assertThat(film.getName(), is(mockFilm.getName()));
        assertThat(film.getType(), is(mockFilm.getType()));
    }

    // findById - nok (id not found)
    @Test
    void givenNonexistentFilmId_whenFindById_thenThrowSpecificException()
    {
        // given
        final Film mockFilm = FilmTestHelper.generateFilm(FilmTestHelper.MOCK_OLD_NAME, oldFilmType);
        entityManager.persistAndFlush(mockFilm);

        // when
        final Optional<Film> result = filmRepository.findById(FilmTestHelper.MOCK_UNKNOWN_ID);

        // + then
        assertFalse(result.isPresent());
    }

    // save - ok
    @Test
    void givenNewFilm_whenSaveFilm_thenReturnNewFilmId()
    {
        // given
        final Film film1 = FilmTestHelper.generateFilm(FilmTestHelper.MOCK_OLD_NAME, oldFilmType);
        final Film film2 = FilmTestHelper.generateFilm(FilmTestHelper.MOCK_NEW_RELEASE_NAME, newReleaseFilmType);
        final List<Film> mockFilms = Arrays.asList(film1, film2);
        mockFilms.forEach(film -> entityManager.persistAndFlush(film));

        // when
        final Film mockFilm = FilmTestHelper.generateFilm(FilmTestHelper.MOCK_NEW_FILM_NAME, regularFilmType);
        final Film result = filmRepository.save(mockFilm);

        // then
        assertThat(result, notNullValue());
        assertEquals(mockFilm.getName(), result.getName());
        assertEquals(mockFilm.getType(), result.getType());
        final List<Film> allFilms = filmRepository.findAll();
        final int expectedNumFilms = mockFilms.size() + 1;
        assertThat(allFilms.size(), is(expectedNumFilms));
    }

    // save - duplicated (unique constraint)
    @Test
    void givenExistentFilm_whenSaveFilm_thenThrowSpecificException()
    {
        // given
        final Film mockFilm = FilmTestHelper.generateFilm(FilmTestHelper.MOCK_OLD_NAME, oldFilmType);
        entityManager.persistAndFlush(mockFilm);
        final Film duplicatedFilm = FilmTestHelper.generateFilm(FilmTestHelper.MOCK_OLD_NAME, oldFilmType);

        // when + then
        assertThrows(DataIntegrityViolationException.class, () -> filmRepository.save(duplicatedFilm));
    }

    // save - not null name
    @Test
    void givenFilmWithNullName_whenSaveCustomer_thenThrowSpecificException()
    {
        // given
        final Film mockFilm = FilmTestHelper.generateFilm(null, oldFilmType);

        // when + then
        assertThrows(DataIntegrityViolationException.class, () -> filmRepository.save(mockFilm));
    }
}
