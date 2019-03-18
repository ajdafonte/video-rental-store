package com.casumo.hometest.videorentalstore.films;

import java.math.BigDecimal;

import com.casumo.hometest.videorentalstore.films.bizz.InsertFilmParameter;
import com.casumo.hometest.videorentalstore.films.domain.Film;
import com.casumo.hometest.videorentalstore.films.domain.FilmType;
import com.casumo.hometest.videorentalstore.films.domain.Price;
import com.casumo.hometest.videorentalstore.films.rest.FilmRest;
import com.casumo.hometest.videorentalstore.films.rest.FilmTypeRest;
import com.casumo.hometest.videorentalstore.films.rest.InsertFilmRequestBody;


/**
 * FilmTestHelper class.
 */
public class FilmTestHelper
{
    public static final long MOCK_ID1;
    public static final long MOCK_ID2;
    public static final long MOCK_ID3;
    public static final long MOCK_UNKNOWN_ID;
    public static final String MOCK_NAME1;
    public static final String MOCK_NAME2;
    public static final String MOCK_NAME3;
    public static final String MOCK_OLD_FILM_TYPE_NAME;
    public static final String MOCK_REGULAR_FILM_TYPE_NAME;
    public static final String MOCK_NEW_FILM_TYPE_NAME;
    public static final String MOCK_BASIC_PRICE_NAME;
    public static final String MOCK_PREMIUM_PRICE_NAME;
    public static final BigDecimal MOCK_BASIC_PRICE_VALUE;
    public static final BigDecimal MOCK_PREMIUM_PRICE_VALUE;
    public static final FilmType MOCK_OLD_FILM_TYPE;
    public static final FilmType MOCK_REGULAR_FILM_TYPE;
    public static final Price MOCK_PREMIUM_PRICE;
    public static final Price MOCK_BASIC_PRICE;
    public static final Film MOCK_FILM1;
    public static final Film MOCK_FILM2;
    public static final FilmRest MOCK_FILM_REST1;
    public static final FilmRest MOCK_FILM_REST2;
    public static final FilmTypeRest MOCK_FILM_TYPE_REST1;
    public static final FilmTypeRest MOCK_FILM_TYPE_REST2;
    public static final InsertFilmRequestBody MOCK_INSERT_REQ_BODY1;
    public static final InsertFilmParameter MOCK_INSERT_PARAMETER1;
    public static final long MOCK_NEW_FILM_ID;
    public static final String MOCK_NEW_FILM_NAME;

    public static final FilmType MOCK_NEW_RELEASE_FILM_TYPE;
    public static final FilmRest MOCK_NEW_FILM_REST;
    public static final FilmTypeRest MOCK_NEW_FILM_TYPE_REST;
    public static final Film MOCK_NEW_FILM;

    static
    {
        MOCK_ID1 = 1L;
        MOCK_ID2 = 2L;
        MOCK_ID3 = 3L;
        MOCK_UNKNOWN_ID = 400L;
        MOCK_NAME1 = "Saving Private Ryan";
        MOCK_NAME2 = "Green Book";
        MOCK_NAME3 = "The Equalizer";
        MOCK_OLD_FILM_TYPE_NAME = "Old";
        MOCK_NEW_FILM_TYPE_NAME = "New Release";
        MOCK_REGULAR_FILM_TYPE_NAME = "Regular";
        MOCK_BASIC_PRICE_NAME = "Premium";
        MOCK_PREMIUM_PRICE_NAME = "Basic";
        MOCK_BASIC_PRICE_VALUE = BigDecimal.valueOf(40);
        MOCK_PREMIUM_PRICE_VALUE = BigDecimal.valueOf(30);

        MOCK_PREMIUM_PRICE = generatePrice(MOCK_ID1, MOCK_BASIC_PRICE_NAME, MOCK_BASIC_PRICE_VALUE);
        MOCK_BASIC_PRICE = generatePrice(MOCK_ID2, MOCK_PREMIUM_PRICE_NAME, MOCK_PREMIUM_PRICE_VALUE);

        MOCK_OLD_FILM_TYPE = generateFilmType(MOCK_ID1, MOCK_OLD_FILM_TYPE_NAME, MOCK_BASIC_PRICE);
        MOCK_REGULAR_FILM_TYPE = generateFilmType(MOCK_ID2, MOCK_REGULAR_FILM_TYPE_NAME, MOCK_BASIC_PRICE);
        MOCK_NEW_RELEASE_FILM_TYPE = generateFilmType(MOCK_ID3, MOCK_NEW_FILM_TYPE_NAME, MOCK_PREMIUM_PRICE);

        MOCK_FILM1 = generateFilm(MOCK_ID1, MOCK_NAME1, MOCK_OLD_FILM_TYPE);
        MOCK_FILM2 = generateFilm(MOCK_ID2, MOCK_NAME2, MOCK_NEW_RELEASE_FILM_TYPE);

        MOCK_FILM_TYPE_REST1 = generateFilmTypeRest(MOCK_ID1, MOCK_OLD_FILM_TYPE_NAME);
        MOCK_FILM_TYPE_REST2 = generateFilmTypeRest(MOCK_ID3, MOCK_NEW_FILM_TYPE_NAME);
        MOCK_FILM_REST1 = generateFilmRest(MOCK_ID1, MOCK_NAME1, MOCK_FILM_TYPE_REST1);
        MOCK_FILM_REST2 = generateFilmRest(MOCK_ID2, MOCK_NAME2, MOCK_FILM_TYPE_REST2);

        MOCK_NEW_FILM_ID = 3L;
        MOCK_NEW_FILM_NAME = "Toy Story";

        MOCK_INSERT_PARAMETER1 = generateInsertFilmParameter(MOCK_NEW_FILM_NAME, MOCK_ID3);
        MOCK_INSERT_REQ_BODY1 = generateInsertFilmRequestBody(MOCK_NEW_FILM_NAME, MOCK_ID3);

        MOCK_NEW_FILM = generateFilm(MOCK_NEW_FILM_ID, MOCK_NEW_FILM_NAME, MOCK_REGULAR_FILM_TYPE);
        MOCK_NEW_FILM_TYPE_REST = generateFilmTypeRest(MOCK_ID2, MOCK_REGULAR_FILM_TYPE_NAME);
        MOCK_NEW_FILM_REST = generateFilmRest(MOCK_NEW_FILM_ID, MOCK_NEW_FILM_NAME, MOCK_NEW_FILM_TYPE_REST);

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

    public static FilmType generateFilmType(final long id, final String name, final Price price)
    {
        final FilmType filmType = new FilmType();
        filmType.setId(id);
        filmType.setName(name);
        filmType.setPrice(price);
        return filmType;
    }

    public static FilmType generateFilmType(final String name, final Price price)
    {
        final FilmType filmType = new FilmType();
        filmType.setName(name);
        filmType.setPrice(price);
        return filmType;
    }

    public static Price generatePrice(final long id, final String name, final BigDecimal value)
    {
        final Price price = new Price();
        price.setId(id);
        price.setName(name);
        price.setValue(value);
        return price;
    }

    public static Price generatePrice(final String name, final BigDecimal value)
    {
        final Price price = new Price();
        price.setName(name);
        price.setValue(value);
        return price;
    }

    public static FilmRest generateFilmRest(final long id, final String name, final FilmTypeRest filmTypeRest)
    {
        final FilmRest filmRest = new FilmRest();
        filmRest.setId(id);
        filmRest.setName(name);
        filmRest.setType(filmTypeRest);
        return filmRest;
    }

    public static FilmTypeRest generateFilmTypeRest(final long id, final String name)
    {
        final FilmTypeRest filmTypeRest = new FilmTypeRest();
        filmTypeRest.setId(id);
        filmTypeRest.setName(name);
        return filmTypeRest;
    }

    public static InsertFilmRequestBody generateInsertFilmRequestBody(final String name, final long filmTypeId)
    {
        final InsertFilmRequestBody requestBody = new InsertFilmRequestBody();
        requestBody.setName(name);
        requestBody.setFilmTypeId(filmTypeId);
        return requestBody;
    }

    public static InsertFilmRequestBody generateInsertFilmRequestBody(final String name)
    {
        final InsertFilmRequestBody requestBody = new InsertFilmRequestBody();
        requestBody.setName(name);
        return requestBody;
    }

    public static InsertFilmParameter generateInsertFilmParameter(final String name, final long filmTypeId)
    {
        final InsertFilmParameter parameter = new InsertFilmParameter();
        parameter.setName(name);
        parameter.setFilmTypeId(filmTypeId);
        return parameter;
    }

//    public static FilmTypeRest generateFilmTypeRest(final long id, final String name)
//    {
//        final FilmTypeRest filmType = new FilmTypeRest();
//        filmType.setId(id);
//        filmType.setName(name);
//        return filmType;
//    }
}
