package com.casumo.hometest.videorentalstore.films.rest.mapper;

import com.casumo.hometest.videorentalstore.films.domain.FilmType;
import com.casumo.hometest.videorentalstore.films.rest.FilmTypeRest;


/**
 * FilmTypeRestMapper class.
 */
public class FilmTypeRestMapper
{
    public static FilmTypeRest map(final FilmType filmType)
    {
        if (filmType != null)
        {
            final FilmTypeRest filmTypeRest = new FilmTypeRest();
            filmTypeRest.setId(filmType.getId());
            filmTypeRest.setName(filmType.getName());
            return filmTypeRest;
        }
        return null;
    }
}
