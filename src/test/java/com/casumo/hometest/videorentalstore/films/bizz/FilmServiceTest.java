package com.casumo.hometest.videorentalstore.films.bizz;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import com.casumo.hometest.videorentalstore.common.error.VideoRentalStoreApiError;
import com.casumo.hometest.videorentalstore.common.error.VideoRentalStoreApiException;
import com.casumo.hometest.videorentalstore.films.FilmTestHelper;
import com.casumo.hometest.videorentalstore.films.domain.Film;
import com.casumo.hometest.videorentalstore.films.repo.FilmRepository;
import com.casumo.hometest.videorentalstore.films.repo.FilmTypeRepository;


/**
 * FilmServiceTest class - Test FilmService class.
 */
@ExtendWith(MockitoExtension.class)
class FilmServiceTest
{

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private FilmTypeRepository filmTypeRepository;

    private FilmService filmService;

    @BeforeEach
    void setUp()
    {
        this.filmService = new FilmServiceImpl(filmRepository, filmTypeRepository);
    }

    // getFilms - with data
    @Test
    void givenExistentFilms_whenFindAll_thenReturnAllFilms()
    {
        // given
        final List<Film> expected = Arrays.asList(FilmTestHelper.MOCK_FILM1, FilmTestHelper.MOCK_FILM2);
        when(filmRepository.findAll()).thenReturn(expected);

        // when
        final List<Film> result = filmService.findAll();

        // then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertThat(result.size(), is(expected.size()));
        assertThat(result, containsInAnyOrder(FilmTestHelper.MOCK_FILM1, FilmTestHelper.MOCK_FILM2));
        verify(filmRepository, times(1)).findAll();
        verifyNoMoreInteractions(filmRepository);
    }

    // getFilms - without data
    @Test
    void givenNoFilms_whenFindAll_thenReturnEmptyCollection()
    {
        // given
        when(filmRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        final List<Film> result = filmService.findAll();

        // then
        assertNotNull(result);
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
        when(filmRepository.findById(anyLong())).thenReturn(Optional.of(expected));

        // when
        final Film result = filmService.findBy(FilmTestHelper.MOCK_ID1);

        // then
        assertNotNull(result);
        assertEquals(expected, result);
        assertThat(result.getId(), is(expected.getId()));
        assertThat(result.getName(), is(expected.getName()));
        assertThat(result.getType(), is(expected.getType()));
        verify(filmRepository, times(1)).findById(FilmTestHelper.MOCK_ID1);
        verifyNoMoreInteractions(filmRepository);
    }

    // getFilmById - id not existent
    @Test
    void givenFilmsInInventoryAndNonexistentId_whenFindById_thenThrowSpecificException()
    {
        // given
        when(filmRepository.findById(anyLong())).thenThrow(VideoRentalStoreApiException.class);

        // when + then
        assertThrows(VideoRentalStoreApiException.class, () -> filmService.findBy(FilmTestHelper.MOCK_UNKNOWN_ID));
        verify(filmRepository, times(1)).findById(FilmTestHelper.MOCK_UNKNOWN_ID);
        verifyNoMoreInteractions(filmRepository);
    }

    // getFilmById - forbidden ??

    // insertFilm - ok
    @Test
    void givenValidParameter_whenInsertFilm_thenReturnFilmInserted()
    {
        // given
        final Film expected = FilmTestHelper.MOCK_NEW_FILM;
        final InsertFilmParameter mockParameter = FilmTestHelper.generateInsertFilmParameter(expected.getName(), expected.getType().getId());
        when(filmTypeRepository.findById(anyLong())).thenReturn(Optional.of(expected.getType()));
        when(filmRepository.save(any(Film.class))).thenReturn(expected);

        // when
        final Film result = filmService.insert(mockParameter);

        // then
        assertNotNull(result);
        assertThat(result, is(expected));
        assertThat(result.getId(), is(expected.getId()));
        assertThat(result.getName(), is(expected.getName()));
        assertThat(result.getType(), is(expected.getType()));
        verify(filmTypeRepository, times(1)).findById(mockParameter.getFilmTypeId());
        verifyNoMoreInteractions(filmTypeRepository);
        verify(filmRepository, times(1)).save(any(Film.class));
        verifyNoMoreInteractions(filmRepository);
    }

    // insertFilm - already exist a film with same name in db
    @Test
    void givenParameterWithExistentFilmName_whenInsertFilm_thenThrowSpecificException()
    {
        // given
        final Film mockFilm = FilmTestHelper.MOCK_FILM2;
        final InsertFilmParameter mockParameter = FilmTestHelper.generateInsertFilmParameter(mockFilm.getName(), mockFilm.getType().getId());
        when(filmTypeRepository.findById(anyLong())).thenReturn(Optional.of(mockFilm.getType()));
        when(filmRepository.save(any(Film.class))).thenThrow(DataIntegrityViolationException.class);

        // when + then
        final VideoRentalStoreApiException ex = assertThrows(VideoRentalStoreApiException.class, () -> filmService.insert(mockParameter));
        assertThat(ex.getError(), is(VideoRentalStoreApiError.RESOURCE_ALREADY_EXISTS));
        verify(filmTypeRepository, times(1)).findById(mockParameter.getFilmTypeId());
        verifyNoMoreInteractions(filmTypeRepository);
        verify(filmRepository, times(1)).save(any(Film.class));
        verifyNoMoreInteractions(filmRepository);
    }

    // insertFilm - invalid film type id
    @Test
    void givenParameterWithUnknownFilmTypeId_whenInsertFilm_thenThrowSpecificException()
    {
        // given
        final InsertFilmParameter mockParameter =
            FilmTestHelper.generateInsertFilmParameter(FilmTestHelper.MOCK_NEW_FILM_NAME, FilmTestHelper.MOCK_UNKNOWN_ID);
        when(filmTypeRepository.findById(anyLong())).thenReturn(Optional.empty());

        // when + then
        final VideoRentalStoreApiException ex = assertThrows(VideoRentalStoreApiException.class, () -> filmService.insert(mockParameter));
        assertThat(ex.getError(), is(VideoRentalStoreApiError.UNKNOWN_RESOURCE));
        verify(filmTypeRepository, times(1)).findById(mockParameter.getFilmTypeId());
        verifyNoMoreInteractions(filmTypeRepository);
        verifyZeroInteractions(filmRepository);
    }
}
