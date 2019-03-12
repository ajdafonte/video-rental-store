package com.casumo.hometest.videorentalstore.films.bizz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casumo.hometest.videorentalstore.films.domain.Film;
import com.casumo.hometest.videorentalstore.films.repo.FilmRepository;


/**
 * FilmServiceImpl class.
 */
@Service
public class FilmServiceImpl implements FilmService
{
    private final FilmRepository filmRepository;

    @Autowired
    public FilmServiceImpl(final FilmRepository filmRepository)
    {
        this.filmRepository = filmRepository;
    }

    @Override
    public List<Film> findAll()
    {
        return filmRepository.findAll();
    }

    @Override
    public Film findBy(final long id)
    {
        return filmRepository.findBy(id);
    }

    @Override
    public Film insert(final Film film)
    {
        final long filmId = filmRepository.save(film);

        return filmRepository.findBy(filmId);
    }
}
