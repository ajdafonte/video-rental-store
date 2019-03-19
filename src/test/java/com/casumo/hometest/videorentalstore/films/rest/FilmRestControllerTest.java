package com.casumo.hometest.videorentalstore.films.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.casumo.hometest.videorentalstore.common.error.VideoRentalStoreApiError;
import com.casumo.hometest.videorentalstore.common.error.VideoRentalStoreApiException;
import com.casumo.hometest.videorentalstore.customers.CustomerTestHelper;
import com.casumo.hometest.videorentalstore.films.FilmTestHelper;
import com.casumo.hometest.videorentalstore.films.bizz.FilmService;
import com.casumo.hometest.videorentalstore.films.bizz.InsertFilmParameter;
import com.casumo.hometest.videorentalstore.films.domain.Film;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;


/**
 * FilmRestControllerTest class - FilmRestController test class.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(FilmRestController.class)
class FilmRestControllerTest
{
    private static final String FILMS_URI = "/films";
    private static final String FILM_BY_ID_URI = "/films/{id}";

    private static final String INVALID_REQUEST = "Invalid request";
    private static final String INVALID_REQUEST_PARAMETER = "Invalid request parameter";
    private static final String RESOURCE_NOT_FOUND = "Resource not found";
    private static final String RESOURCE_ALREADY_EXISTS = "Resource already exists";
    private static final String UNKNOWN_RESOURCE = "Resource not found";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FilmService filmService;

    // get all films - ok
    @Test
    void givenInventoryOfFilms_whenGetAllFilms_thenReturnAllFilms() throws Exception
    {
        // given
        final List<Film> allFilms = Arrays.asList(FilmTestHelper.MOCK_NEW_RELEASE_FILM, FilmTestHelper.MOCK_OLD_FILM);
        final List<FilmRest> expectedResult = Arrays.asList(FilmTestHelper.MOCK_NEW_RELEASE_FILM_REST, FilmTestHelper.MOCK_OLD_FILM_REST);
        doReturn(allFilms).when(filmService).findAll();
        final String expectedContent = generateSuccessBody(expectedResult);

        // when
        final ResultActions result = mvc.perform(get(FILMS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.content().string(expectedContent));
        verify(filmService, times(1)).findAll();
        verifyNoMoreInteractions(filmService);
    }

    // get all films - empty data
    @Test
    void givenEmptyInventoryOfFilms_whenGetAllFilms_thenReturnEmptyResult() throws Exception
    {
        // given
        final List emptyList = Collections.emptyList();
        doReturn(emptyList).when(filmService).findAll();
        final String expectedContent = generateSuccessBody(emptyList);

        // when
        final ResultActions result = mvc.perform(get(FILMS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.content().string(expectedContent));
        verify(filmService, times(1)).findAll();
        verifyNoMoreInteractions(filmService);
    }

    // get film by id - ok
    @Test
    void givenExistentId_whenGetFilmById_thenReturnExistentFilm() throws Exception
    {
        // given
        final Film mockFilm = FilmTestHelper.MOCK_OLD_FILM;
        final FilmRest expectedFilm = FilmTestHelper.MOCK_OLD_FILM_REST;
        doReturn(mockFilm).when(filmService).findBy(anyLong());
        final String expectedContent = generateSuccessBody(expectedFilm);

        // when
        final ResultActions result = mvc.perform(get(FILM_BY_ID_URI, expectedFilm.getId())
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.content().string(expectedContent));
        verify(filmService, times(1)).findBy(mockFilm.getId());
        verifyNoMoreInteractions(filmService);
    }

    // get film by id - invalid id (not a number)
    @Test
    void givenInvalidId_whenGetFilmById_thenReturnBadRequest() throws Exception
    {
        // given
        final String unknownFilmId = "lol";

        // then
        final ResultActions result = mvc.perform(get(FILM_BY_ID_URI, unknownFilmId)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST_PARAMETER)));
        verifyZeroInteractions(filmService);
    }

    // get film by id - not found in db
    @Test
    void givenNonExistentId_whenGetFilmById_thenReturnNotFound() throws Exception
    {
        // given
        final long unknownFilmId = FilmTestHelper.MOCK_UNKNOWN_ID;
        doThrow(new VideoRentalStoreApiException(VideoRentalStoreApiError.UNKNOWN_RESOURCE, "Entity was not found."))
            .when(filmService)
            .findBy(unknownFilmId);

        // when
        final ResultActions result = mvc.perform(get(FILM_BY_ID_URI, unknownFilmId)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isNotFound());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(RESOURCE_NOT_FOUND)));
        verify(filmService, times(1)).findBy(unknownFilmId);
        verifyNoMoreInteractions(filmService);
    }

    // save film - ok
    @Test
    void givenValidRequest_whenInsertNewFilm_thenReturnNewFilm() throws Exception
    {
        // given
        final Film newFilm = FilmTestHelper.MOCK_NEW_RELEASE_FILM;
        doReturn(newFilm).when(filmService).insert(any(InsertFilmParameter.class));
        final InsertFilmRequestBody mockRequestBody = FilmTestHelper.MOCK_INSERT_REQ_BODY1;
        final String requestBody = generateRequestBody(mockRequestBody);
        final FilmRest expectedResult = FilmTestHelper.MOCK_NEW_RELEASE_FILM_REST;
        final String expectedContent = generateSuccessBody(expectedResult);

        // when
        final ResultActions result = mvc.perform(post(FILMS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isCreated());
        result.andExpect(MockMvcResultMatchers.content().string(expectedContent));
        verify(filmService, times(1)).insert(any(InsertFilmParameter.class));
        verifyNoMoreInteractions(filmService);
    }

    // save film - invalid body (null body)
    @Test
    void givenNullRequestBody_whenInsertNewFilm_thenReturnBadRequest() throws Exception
    {
        // given
        final String requestBody = "{}";

        // when
        final ResultActions result = mvc.perform(post(FILMS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST)));
        verifyZeroInteractions(filmService);
    }

    // save film - invalid body (body with missing values)
    @Test
    void givenIncompleteRequestBody_whenInsertNewFilm_thenReturnBadRequest() throws Exception
    {
        // given
        final InsertFilmRequestBody parameter = FilmTestHelper.generateInsertFilmRequestBody(CustomerTestHelper.MOCK_USERNAME1);
        final String requestBody = generateRequestBody(parameter);

        // when
        final ResultActions result = mvc.perform(post(FILMS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST)));
        verifyZeroInteractions(filmService);
    }

    // save film - invalid body (body with invalid values)
    @Test
    void givenRequestBodyWithInvalidValues_whenInsertNewFilm_thenReturnBadRequest() throws Exception
    {
        // given
        final InsertFilmRequestBody parameter = FilmTestHelper.generateInsertFilmRequestBody("", FilmTestHelper.MOCK_ID1);
        final String requestBody = generateRequestBody(parameter);

        // when
        final ResultActions result = mvc.perform(post(FILMS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST)));
        verifyZeroInteractions(filmService);
    }

    // save film - nok (film already existent)
    @Test
    void givenRequestBodyWithExistentFilm_whenInsertNewFilm_thenReturnConflict() throws Exception
    {
        // given
        final InsertFilmRequestBody parameter = FilmTestHelper.MOCK_INSERT_REQ_BODY1;
        final String requestBody = generateRequestBody(parameter);
        doThrow(new VideoRentalStoreApiException(VideoRentalStoreApiError.RESOURCE_ALREADY_EXISTS,
            "Violation of primary key or unique constraint."))
            .when(filmService)
            .insert(any(InsertFilmParameter.class));

        // when
        final ResultActions result = mvc.perform(post(FILMS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isConflict());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(RESOURCE_ALREADY_EXISTS)));
        verify(filmService, times(1)).insert(any(InsertFilmParameter.class));
        verifyNoMoreInteractions(filmService);
    }

    // save film - nok (film type id unknown)
    @Test
    void givenRequestBodyWithUnknownFilmType_whenInsertNewFilm_thenReturnConflict() throws Exception
    {
        // given
        final InsertFilmRequestBody parameter = FilmTestHelper.MOCK_INSERT_REQ_BODY1;
        final String requestBody = generateRequestBody(parameter);
        doThrow(new VideoRentalStoreApiException(VideoRentalStoreApiError.UNKNOWN_RESOURCE,
            "FilmType with id " + parameter.getFilmTypeId() + " was not found."))
            .when(filmService)
            .insert(any(InsertFilmParameter.class));

        // when
        final ResultActions result = mvc.perform(post(FILMS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isNotFound());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(UNKNOWN_RESOURCE)));
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(
            "FilmType with id " + parameter.getFilmTypeId() + " was not found.")));
        verify(filmService, times(1)).insert(any(InsertFilmParameter.class));
        verifyNoMoreInteractions(filmService);
    }

    private JsonElement generateJsonObject(final FilmRest filmRest)
    {
        return new Gson().toJsonTree(filmRest);
    }

    private JsonElement generateJsonObject(final InsertFilmRequestBody requestBody)
    {
        return new Gson().toJsonTree(requestBody);
    }

    private String generateSuccessBody(final List<FilmRest> films)
    {
        final JsonArray jsonArray = new JsonArray();
        films.forEach(filmRest -> jsonArray.add(generateJsonObject(filmRest)));
        return jsonArray.toString();
    }

    private String generateSuccessBody(final FilmRest film)
    {
        return generateJsonObject(film).toString();
    }

    private String generateRequestBody(final InsertFilmRequestBody requestBody)
    {
        return generateJsonObject(requestBody).toString();
    }

}
