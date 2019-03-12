package com.casumo.hometest.videorentalstore.films.repo;

import java.util.List;

import com.casumo.hometest.videorentalstore.films.domain.Film;


/**
 * FilmRepository interface.
 */
public interface FilmRepository
{
    List<Film> findAll();

    Film findBy(long id);

    long save(Film film);
}
