package com.casumo.hometest.videorentalstore.films.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casumo.hometest.videorentalstore.films.domain.Film;


/**
 * FilmRepository interface.
 */
public interface FilmRepository extends JpaRepository<Film, Long>
{
}
