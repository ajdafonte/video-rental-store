package com.casumo.hometest.videorentalstore.films.rest.mapper;

import com.casumo.hometest.videorentalstore.films.domain.Film;
import com.casumo.hometest.videorentalstore.films.rest.FilmRest;


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
}