package com.casumo.hometest.videorentalstore.films.rest.mapper;

import com.casumo.hometest.videorentalstore.films.domain.Film;
import com.casumo.hometest.videorentalstore.films.rest.FilmRest;
import com.casumo.hometest.videorentalstore.films.rest.InsertFilmRequestBody;


/**
 * FilmRestMapper class.
 */
public class FilmRestMapper
{
    public static FilmRest map(final Film film)
    {
        if (film != null)
        {
            final FilmRest filmRest = new FilmRest();
            filmRest.setId(film.getId());
            filmRest.setName(film.getName());
            filmRest.setType(FilmTypeRestMapper.map(film.getType()));
            return filmRest;
        }
        return null;
    }

    public static Film mapToBizz(final InsertFilmRequestBody parameterRest)
    {
        if (parameterRest != null)
        {
            final Film film = new Film();
            film.setName(parameterRest.getName());
            film.setType(FilmTypeRestMapper.mapToBizz(parameterRest.getFilmType()));
            return film;
        }
        return null;
    }

}