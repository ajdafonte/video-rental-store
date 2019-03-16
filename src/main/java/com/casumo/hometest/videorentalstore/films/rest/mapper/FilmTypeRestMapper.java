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
//            filmTypeRest.setPrice(PriceRestMapper.map(filmType.getPrice()));
            return filmTypeRest;
        }
        return null;
    }

//    public static FilmType mapToBizz(final InsertFilmRequestBody parameterRest)
//    {
//        if (parameterRest != null)
//        {
//            final Film film = new Film();
//            film.setName(parameterRest.getName());
//            film.setType(FilmTypeRestMapper.mapToBizz(parameterRest.getFilmTypeId()));
//            return film;
//        }
//        return null;
//    }
}
