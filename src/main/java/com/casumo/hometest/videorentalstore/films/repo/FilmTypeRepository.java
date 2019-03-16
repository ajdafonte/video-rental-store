package com.casumo.hometest.videorentalstore.films.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casumo.hometest.videorentalstore.films.domain.FilmType;


/**
 * FilmTypeRepository interface.
 */
public interface FilmTypeRepository extends JpaRepository<FilmType, Long>
{
}
