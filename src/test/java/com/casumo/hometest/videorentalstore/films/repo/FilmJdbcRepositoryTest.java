package com.casumo.hometest.videorentalstore.films.repo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.casumo.hometest.videorentalstore.common.error.VideoRentalStoreApiException;
import com.casumo.hometest.videorentalstore.films.FilmTestHelper;
import com.casumo.hometest.videorentalstore.films.domain.Film;


/**
 * FilmJdbcRepositoryTest class - Test FilmJdbcRepository class.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = FilmJdbcRepositoryTestConfig.class)
@ActiveProfiles("repotest")
class FilmJdbcRepositoryTest
{
    private static int INIT_NUM_FILMS = 3;

    private final FilmRepository filmRepository;

    @Autowired
    FilmJdbcRepositoryTest(final FilmRepository filmRepository)
    {
        this.filmRepository = filmRepository;
    }

    // find all
    @Test
    void givenFilmsInInventory_whenFindAll_thenReturnAllFilms()
    {
        // when
        final List<Film> result = filmRepository.findAll();

        // then
        assertFalse(result.isEmpty());
        assertThat(result.size(), is(INIT_NUM_FILMS));
        assertThat(result, Matchers.hasItem(FilmTestHelper.MOCK_FILM1));
        assertThat(result, Matchers.hasItem(FilmTestHelper.MOCK_FILM2));
    }

    // findById - ok
    @Test
    void givenExistentFilmId_whenFindById_thenReturnExistentFilm()
    {
        // given
        final Film existentFilm = FilmTestHelper.MOCK_FILM1;

        // when
        final Film result = filmRepository.findBy(FilmTestHelper.MOCK_ID1);

        // then
        assertNotNull(result);
        assertEquals(existentFilm, result);
        assertThat(result.getId(), is(existentFilm.getId()));
        assertThat(result.getName(), is(existentFilm.getName()));
        assertThat(result.getType(), is(existentFilm.getType()));
    }

    // findById - nok (id not found)
    @Test
    void givenNotExistentFilmId_whenFindById_thenThrowSpecificException()
    {
        // when + then
        assertThrows(VideoRentalStoreApiException.class, () -> filmRepository.findBy(FilmTestHelper.MOCK_UNKNOWN_ID));
    }

    // save - ok
    @Test
    void givenNewFilm_whenSaveFilm_thenReturnNewFilmId()
    {
        // when
        final Film mockFilm = FilmTestHelper.generateFilm(FilmTestHelper.MOCK_NEW_FILM_NAME, FilmTestHelper.MOCK_NEW_FILM_TYPE);
        final long result = filmRepository.save(mockFilm);

        // then
        assertThat(result, notNullValue());
        final List<Film> allFilms = filmRepository.findAll();
        assertThat(allFilms.size(), is(++INIT_NUM_FILMS));
    }

    // save - duplicated
    @Test
    void givenExistentFilm_whenSaveFilm_thenThrowSpecificException()
    {
        // when + then
        assertThrows(VideoRentalStoreApiException.class, () -> filmRepository.save(FilmTestHelper.MOCK_FILM1));
    }
}
