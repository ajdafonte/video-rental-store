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
    public static final long MOCK_ID1 = 1L;
    public static final long MOCK_ID2 = 2L;
    public static final long MOCK_ID3 = 3L;
    public static final long MOCK_UNKNOWN_ID = 400L;
    public static final String MOCK_OLD_NAME = "Saving Private Ryan";
    public static final String MOCK_NEW_RELEASE_NAME = "Green Book";
    public static final String MOCK_REGULAR_NAME = "The Equalizer";
    public static final String MOCK_OLD_FILM_TYPE_NAME = "Old";
    public static final String MOCK_NEW_FILM_TYPE_NAME = "New Release";
    public static final String MOCK_REGULAR_FILM_TYPE_NAME = "Regular";
    public static final String MOCK_BASIC_PRICE_NAME = "Premium";
    public static final String MOCK_PREMIUM_PRICE_NAME = "Basic";
    public static final BigDecimal MOCK_BASIC_PRICE_VALUE = BigDecimal.valueOf(40);
    public static final BigDecimal MOCK_PREMIUM_PRICE_VALUE = BigDecimal.valueOf(30);
    public static final long MOCK_NEW_FILM_ID = 4L;
    public static final String MOCK_NEW_FILM_NAME = "Toy Story";

    public static final InsertFilmRequestBody MOCK_INSERT_REQ_BODY1;
    public static final InsertFilmParameter MOCK_INSERT_PARAMETER1;
//    public static final FilmRest MOCK_NEW_FILM_REST;
//    public static final FilmTypeRest MOCK_NEW_FILM_TYPE_REST;
//    public static final Film MOCK_NEW_FILM;

    //    public static final Film MOCK_FILM1;
//    public static final Film MOCK_FILM2;
//    public static final FilmRest MOCK_FILM_REST1;
//    public static final FilmRest MOCK_FILM_REST2;
//    public static final FilmTypeRest MOCK_FILM_TYPE_REST1;
//    public static final FilmTypeRest MOCK_FILM_TYPE_REST2;

    ///////////////////////////////////////

    public static final Price MOCK_PREMIUM_PRICE;
    public static final Price MOCK_BASIC_PRICE;

    public static final FilmType MOCK_NEW_RELEASE_FILM_TYPE;
    public static final FilmType MOCK_REGULAR_FILM_TYPE;
    public static final FilmType MOCK_OLD_FILM_TYPE;

    public static final Film MOCK_NEW_RELEASE_FILM;
    public static final Film MOCK_REGULAR_FILM;
    public static final Film MOCK_OLD_FILM;

    public static final FilmTypeRest MOCK_NEW_RELEASE_FILM_TYPE_REST;
    public static final FilmTypeRest MOCK_REGULAR_FILM_TYPE_REST;
    public static final FilmTypeRest MOCK_OLD_FILM_TYPE_REST;

    public static final FilmRest MOCK_NEW_RELEASE_FILM_REST;
    public static final FilmRest MOCK_REGULAR_FILM_REST;
    public static final FilmRest MOCK_OLD_FILM_REST;

    static
    {
        MOCK_PREMIUM_PRICE = generatePrice(MOCK_ID1, MOCK_BASIC_PRICE_NAME, MOCK_BASIC_PRICE_VALUE);
        MOCK_BASIC_PRICE = generatePrice(MOCK_ID2, MOCK_PREMIUM_PRICE_NAME, MOCK_PREMIUM_PRICE_VALUE);

        MOCK_NEW_RELEASE_FILM_TYPE = generateFilmType(MOCK_ID1, MOCK_NEW_FILM_TYPE_NAME, MOCK_PREMIUM_PRICE);
        MOCK_REGULAR_FILM_TYPE = generateFilmType(MOCK_ID2, MOCK_REGULAR_FILM_TYPE_NAME, MOCK_BASIC_PRICE);
        MOCK_OLD_FILM_TYPE = generateFilmType(MOCK_ID1, MOCK_OLD_FILM_TYPE_NAME, MOCK_BASIC_PRICE);

        MOCK_NEW_RELEASE_FILM =
            generateFilm(FilmTestHelper.MOCK_ID1, FilmTestHelper.MOCK_NEW_RELEASE_NAME, FilmTestHelper.MOCK_NEW_RELEASE_FILM_TYPE);
        MOCK_REGULAR_FILM =
            generateFilm(FilmTestHelper.MOCK_ID2, FilmTestHelper.MOCK_REGULAR_NAME, FilmTestHelper.MOCK_REGULAR_FILM_TYPE);
        MOCK_OLD_FILM =
            generateFilm(FilmTestHelper.MOCK_ID3, FilmTestHelper.MOCK_OLD_NAME, FilmTestHelper.MOCK_OLD_FILM_TYPE);

        MOCK_NEW_RELEASE_FILM_TYPE_REST =
            generateFilmTypeRest(MOCK_NEW_RELEASE_FILM_TYPE.getId(), MOCK_NEW_RELEASE_FILM_TYPE.getName());
        MOCK_REGULAR_FILM_TYPE_REST =
            generateFilmTypeRest(MOCK_REGULAR_FILM_TYPE.getId(), MOCK_REGULAR_FILM_TYPE.getName());
        MOCK_OLD_FILM_TYPE_REST = generateFilmTypeRest(MOCK_OLD_FILM_TYPE.getId(), MOCK_OLD_FILM_TYPE.getName());

        MOCK_NEW_RELEASE_FILM_REST =
            generateFilmRest(MOCK_NEW_RELEASE_FILM.getId(), MOCK_NEW_RELEASE_FILM.getName(), MOCK_NEW_RELEASE_FILM_TYPE_REST);
        MOCK_REGULAR_FILM_REST =
            generateFilmRest(MOCK_REGULAR_FILM.getId(), MOCK_REGULAR_FILM.getName(), MOCK_REGULAR_FILM_TYPE_REST);
        MOCK_OLD_FILM_REST =
            generateFilmRest(MOCK_OLD_FILM.getId(), MOCK_OLD_FILM.getName(), MOCK_OLD_FILM_TYPE_REST);

//        MOCK_FILM1 = generateFilm(MOCK_ID1, MOCK_OLD_NAME, MOCK_OLD_FILM_TYPE);
//        MOCK_FILM2 = generateFilm(MOCK_ID2, MOCK_NEW_RELEASE_NAME, MOCK_NEW_RELEASE_FILM_TYPE);
//
//        MOCK_FILM_TYPE_REST1 = generateFilmTypeRest(MOCK_ID1, MOCK_OLD_FILM_TYPE_NAME);
//        MOCK_FILM_TYPE_REST2 = generateFilmTypeRest(MOCK_ID3, MOCK_NEW_FILM_TYPE_NAME);
//        MOCK_FILM_REST1 = generateFilmRest(MOCK_ID1, MOCK_OLD_NAME, MOCK_FILM_TYPE_REST1);
//        MOCK_FILM_REST2 = generateFilmRest(MOCK_ID2, MOCK_NEW_RELEASE_NAME, MOCK_FILM_TYPE_REST2);

        MOCK_INSERT_REQ_BODY1 = generateInsertFilmRequestBody(MOCK_NEW_FILM_NAME, MOCK_REGULAR_FILM_TYPE.getId());
        MOCK_INSERT_PARAMETER1 = generateInsertFilmParameter(MOCK_NEW_FILM_NAME, MOCK_REGULAR_FILM_TYPE.getId());

//        MOCK_NEW_FILM = generateFilm(MOCK_NEW_FILM_ID, MOCK_NEW_FILM_NAME, MOCK_REGULAR_FILM_TYPE);
//        MOCK_NEW_FILM_TYPE_REST = generateFilmTypeRest(MOCK_ID2, MOCK_REGULAR_FILM_TYPE_NAME);
//        MOCK_NEW_FILM_REST = generateFilmRest(MOCK_NEW_FILM_ID, MOCK_NEW_FILM_NAME, MOCK_NEW_FILM_TYPE_REST);

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
}
