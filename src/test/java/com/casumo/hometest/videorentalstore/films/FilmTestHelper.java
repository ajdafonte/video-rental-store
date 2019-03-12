package com.casumo.hometest.videorentalstore.films;

import com.casumo.hometest.videorentalstore.films.domain.Film;
import com.casumo.hometest.videorentalstore.films.domain.FilmType;
import com.casumo.hometest.videorentalstore.films.rest.FilmRest;
import com.casumo.hometest.videorentalstore.films.rest.InsertFilmRequestBody;
import com.casumo.hometest.videorentalstore.films.rest.mapper.FilmTypeRestMapper;


/**
 * FilmTestHelper class.
 */
public class FilmTestHelper
{
    public static final long MOCK_ID1;
    public static final long MOCK_ID2;
    public static final long MOCK_UNKNOWN_ID;
    public static final String MOCK_NAME1;
    public static final String MOCK_NAME2;
    public static final FilmType MOCK_FILM_TYPE1;
    public static final FilmType MOCK_FILM_TYPE2;
    public static final Film MOCK_FILM1;
    public static final FilmRest MOCK_FILM_REST1;
    public static final Film MOCK_FILM2;
    public static final FilmRest MOCK_FILM_REST2;
    public static final InsertFilmRequestBody MOCK_INSERT_REQ_BODY1;
    public static final long MOCK_NEW_FILM_ID;
    public static final String MOCK_NEW_FILM_NAME;
    public static final FilmType MOCK_NEW_FILM_TYPE;
    public static final FilmRest MOCK_NEW_FILM_REST;
    public static final Film MOCK_NEW_FILM;

    static
    {
        MOCK_ID1 = 1L;
        MOCK_ID2 = 2L;
        MOCK_UNKNOWN_ID = 400L;
        MOCK_NAME1 = "Saving Private Ryan";
        MOCK_NAME2 = "Green Book";
        MOCK_FILM_TYPE1 = FilmType.OLD;
        MOCK_FILM_TYPE2 = FilmType.NEW_RELEASE;
        MOCK_FILM1 = generateFilm(MOCK_ID1, MOCK_NAME1, MOCK_FILM_TYPE1);
        MOCK_FILM2 = generateFilm(MOCK_ID2, MOCK_NAME2, MOCK_FILM_TYPE2);
        MOCK_FILM_REST1 = generateFilmRest(MOCK_ID1, MOCK_NAME1, MOCK_FILM_TYPE1);
        MOCK_FILM_REST2 = generateFilmRest(MOCK_ID2, MOCK_NAME2, MOCK_FILM_TYPE2);

        MOCK_NEW_FILM_ID = 4L;
        MOCK_NEW_FILM_NAME = "Toy Story";
        MOCK_NEW_FILM_TYPE = FilmType.REGULAR;
        MOCK_INSERT_REQ_BODY1 = generateInsertFilmRequestBody(MOCK_NEW_FILM_NAME, MOCK_NEW_FILM_TYPE);
        MOCK_NEW_FILM_REST = generateFilmRest(MOCK_NEW_FILM_ID, MOCK_NEW_FILM_NAME, MOCK_NEW_FILM_TYPE);
        MOCK_NEW_FILM = generateFilm(MOCK_NEW_FILM_ID, MOCK_NEW_FILM_NAME, MOCK_NEW_FILM_TYPE);
    }

    public static Film generateFilm(final long id, final String name, final FilmType type)
    {
        final Film film = new Film();
        film.setId(id);
        film.setName(name);
        film.setType(type);
        return film;
    }

    public static Film generateFilm(final String name, final FilmType type)
    {
        final Film film = new Film();
        film.setName(name);
        film.setType(type);
        return film;
    }

    public static FilmRest generateFilmRest(final long id, final String name, final FilmType type)
    {
        final FilmRest filmRest = new FilmRest();
        filmRest.setId(id);
        filmRest.setName(name);
        filmRest.setType(FilmTypeRestMapper.map(type));
        return filmRest;
    }

    public static InsertFilmRequestBody generateInsertFilmRequestBody(final String name, final FilmType type)
    {
        final InsertFilmRequestBody requestBody = new InsertFilmRequestBody();
        requestBody.setName(name);
        requestBody.setFilmType(FilmTypeRestMapper.map(type));
        return requestBody;
    }

    public static InsertFilmRequestBody generateInsertFilmRequestBody(final String name)
    {
        final InsertFilmRequestBody requestBody = new InsertFilmRequestBody();
        requestBody.setName(name);
        return requestBody;
    }
}
