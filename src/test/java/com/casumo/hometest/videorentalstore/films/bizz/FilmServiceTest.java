package com.casumo.hometest.videorentalstore.films.bizz;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.casumo.hometest.videorentalstore.common.error.VideoRentalStoreApiException;
import com.casumo.hometest.videorentalstore.films.FilmTestHelper;
import com.casumo.hometest.videorentalstore.films.domain.Film;
import com.casumo.hometest.videorentalstore.films.repo.FilmRepository;


/**
 * FilmService class - Test FilmService class.
 */
@ExtendWith(MockitoExtension.class)
class FilmServiceTest
{
//    private static final long MOCK_ID1;
//    private static final long MOCK_ID2;
//    private static final long MOCK_UNKNOWN_ID;
//    private static final String MOCK_NAME1;
//    private static final String MOCK_NAME2;
//    private static final FilmType MOCK_FILM_TYPE1;
//    private static final FilmType MOCK_FILM_TYPE2;
//    private static final Film MOCK_FILM1;
//    private static final Film MOCK_FILM2;
//
//    static
//    {
//        MOCK_ID1 = 1L;
//        MOCK_ID2 = 2L;
//        MOCK_UNKNOWN_ID = 0L;
//        MOCK_NAME1 = "Saving Private Ryan";
//        MOCK_NAME2 = "Green Book";
//        MOCK_FILM_TYPE1 = FilmType.OLD;
//        MOCK_FILM_TYPE2 = FilmType.NEW_RELEASE;
//        MOCK_FILM1 = FilmTestHelper.generateFilm(MOCK_ID1, MOCK_NAME1, MOCK_FILM_TYPE1);
//        MOCK_FILM2 = FilmTestHelper.generateFilm(MOCK_ID2, MOCK_NAME2, MOCK_FILM_TYPE2);
//    }

    @Mock
    private FilmRepository filmRepository;

    private FilmService filmService;

    @BeforeEach
    void setUp()
    {
        this.filmService = new FilmServiceImpl(filmRepository);
    }

    // getFilms - with data
    @Test
    void givenFilmsInInventory_whenFindAll_thenReturnAllFilms()
    {
        // given
        final List<Film> expected = Arrays.asList(FilmTestHelper.MOCK_FILM1, FilmTestHelper.MOCK_FILM2);
        when(filmRepository.findAll()).thenReturn(expected);

        // when
        final List<Film> result = filmService.findAll();

        // then
        assertThat(result.size(), is(expected.size()));
        assertThat(result, containsInAnyOrder(FilmTestHelper.MOCK_FILM1, FilmTestHelper.MOCK_FILM2));
        verify(filmRepository, times(1)).findAll();
        verifyNoMoreInteractions(filmRepository);
    }

    // getFilms - without data
    @Test
    void givenNoFilmsInInventory_whenFindAll_thenReturnEmptyCollection()
    {
        // given
        when(filmRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        final List<Film> result = filmService.findAll();

        // then
        assertTrue(result.isEmpty());
        verify(filmRepository, times(1)).findAll();
        verifyNoMoreInteractions(filmRepository);
    }

    // getFilms - forbidden ??

    // getFilmById - ok
    @Test
    void givenFilmsInInventoryAndExistentId_whenFindById_thenReturnSpecificFilm()
    {
        // given
        final Film expected = FilmTestHelper.MOCK_FILM1;
        when(filmRepository.findBy(anyLong())).thenReturn(expected);

        // when
        final Film result = filmService.findBy(FilmTestHelper.MOCK_ID1);

        // then
        assertNotNull(result);
        assertEquals(expected, result);
        assertThat(result.getId(), is(expected.getId()));
        assertThat(result.getName(), is(expected.getName()));
        assertThat(result.getType(), is(expected.getType()));
        verify(filmRepository, times(1)).findBy(FilmTestHelper.MOCK_ID1);
        verifyNoMoreInteractions(filmRepository);
    }

    // getFilmById - id not existent
    @Test
    void givenFilmsInInventoryAndNotExistentId_whenFindById_thenThrowSpecificException()
    {
        // given
        when(filmRepository.findBy(anyLong())).thenThrow(VideoRentalStoreApiException.class);

        // when + then
        assertThrows(VideoRentalStoreApiException.class, () -> filmService.findBy(FilmTestHelper.MOCK_UNKNOWN_ID));
        verify(filmRepository, times(1)).findBy(FilmTestHelper.MOCK_UNKNOWN_ID);
        verifyNoMoreInteractions(filmRepository);
    }

    // getFilmById - forbidden ??

    // insertFilm - ok
    @Test
    void givenValidFilm_whenInsertFilm_thenReturnFilmInserted()
    {
        // given
        final Film expected = FilmTestHelper.generateFilm(FilmTestHelper.MOCK_ID1, FilmTestHelper.MOCK_NAME1, FilmTestHelper.MOCK_FILM_TYPE1);
        when(filmRepository.save(any(Film.class))).thenReturn(FilmTestHelper.MOCK_ID1);
        when(filmRepository.findBy(anyLong())).thenReturn(expected);

        // when
        final Film result = filmService.insert(expected);

        // then
        assertNotNull(result);
        assertThat(result, is(expected));
        assertThat(result.getId(), is(expected.getId()));
        assertThat(result.getName(), is(expected.getName()));
        assertThat(result.getType(), is(expected.getType()));
        verify(filmRepository, times(1)).save(expected);
        verify(filmRepository, times(1)).findBy(FilmTestHelper.MOCK_ID1);
        verifyNoMoreInteractions(filmRepository);
    }

    // insertFilm - already exist in db
    @Test
    void givenExistentFilmName_whenInsertFilm_thenThrowSpecificException()
    {
        // given
        final Film mockFilm = FilmTestHelper.generateFilm(FilmTestHelper.MOCK_ID2, FilmTestHelper.MOCK_NAME2, FilmTestHelper.MOCK_FILM_TYPE2);
        when(filmRepository.save(any(Film.class))).thenThrow(VideoRentalStoreApiException.class);

        // when + then
        assertThrows(VideoRentalStoreApiException.class, () -> filmService.insert(mockFilm));
        verify(filmRepository, times(1)).save(mockFilm);
        verify(filmRepository, times(0)).findBy(anyLong());
        verifyNoMoreInteractions(filmRepository);
    }
}
