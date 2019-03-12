package com.casumo.hometest.videorentalstore.films.rest.mapper;

import com.casumo.hometest.videorentalstore.films.domain.FilmType;
import com.casumo.hometest.videorentalstore.films.rest.FilmTypeRest;


/**
 * FilmTypeRestMapper class.
 */
public class FilmTypeRestMapper
{
    private FilmTypeRestMapper()
    {
        // to prevent instantiation
    }

    public static FilmTypeRest map(final FilmType filmType)
    {
        if (filmType != null)
        {
            switch (filmType)
            {
                case NEW_RELEASE:
                    return FilmTypeRest.NEW_RELEASE;
                case REGULAR:
                    return FilmTypeRest.REGULAR;
                case OLD:
                    return FilmTypeRest.OLD;
            }
        }
        return null;
    }

    public static FilmType mapToBizz(final FilmTypeRest filmType)
    {
        if (filmType != null)
        {
            switch (filmType)
            {
                case NEW_RELEASE:
                    return FilmType.NEW_RELEASE;
                case REGULAR:
                    return FilmType.REGULAR;
                case OLD:
                    return FilmType.OLD;
            }
        }
        return null;
    }
}
