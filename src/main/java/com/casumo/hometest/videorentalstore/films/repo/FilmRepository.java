package com.casumo.hometest.videorentalstore.films.repo;

import java.util.List;

import com.casumo.hometest.videorentalstore.films.domain.Film;


/**
 * FilmRepository interface.
 */
public interface FilmRepository
{
    /**
     * @return
     */
    List<Film> findAll();

    /**
     * @param id
     * @return
     */
    Film findBy(long id);

    /**
     * @param film
     * @return
     */
    long save(Film film);
}
