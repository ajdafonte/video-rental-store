package com.casumo.hometest.videorentalstore.films.bizz;

import java.util.List;

import org.springframework.stereotype.Service;

import com.casumo.hometest.videorentalstore.films.domain.Film;


/**
 * FilmService class.
 */
@Service
public interface FilmService
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
    Film insert(Film film);
}
