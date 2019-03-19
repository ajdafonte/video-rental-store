package com.casumo.hometest.videorentalstore.films.bizz;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.casumo.hometest.videorentalstore.common.error.VideoRentalStoreApiError;
import com.casumo.hometest.videorentalstore.common.error.VideoRentalStoreApiException;
import com.casumo.hometest.videorentalstore.films.domain.Film;
import com.casumo.hometest.videorentalstore.films.domain.FilmType;
import com.casumo.hometest.videorentalstore.films.repo.FilmRepository;
import com.casumo.hometest.videorentalstore.films.repo.FilmTypeRepository;


/**
 * FilmServiceImpl class.
 */
@Service
public class FilmServiceImpl implements FilmService
{
    private final FilmRepository filmRepository;
    private final FilmTypeRepository filmTypeRepository;

    @Autowired
    public FilmServiceImpl(final FilmRepository filmRepository,
                           final FilmTypeRepository filmTypeRepository)
    {
        this.filmRepository = filmRepository;
        this.filmTypeRepository = filmTypeRepository;
    }

    @Override
    public List<Film> findAll()
    {
        return filmRepository.findAll();
    }

    @Override
    public Film findBy(final long id)
    {
        return filmRepository.findById(id)
            .orElseThrow(() -> new VideoRentalStoreApiException(VideoRentalStoreApiError.UNKNOWN_RESOURCE, "Film was not found."));
    }

    @Override
    public Film insert(final InsertFilmParameter parameter)
    {
        final Optional<FilmType> filmType = filmTypeRepository.findById(parameter.getFilmTypeId());
        if (!filmType.isPresent())
        {
            throw new VideoRentalStoreApiException(
                VideoRentalStoreApiError.UNKNOWN_RESOURCE,
                "FilmType with id " + parameter.getFilmTypeId() + " was not found.");
        }

        final Film toInsert = new Film();
        toInsert.setName(parameter.getName());
        toInsert.setType(filmType.get());

        try
        {
            return filmRepository.save(toInsert);
        }
        catch (final DataAccessException e)
        {
            throw new VideoRentalStoreApiException(VideoRentalStoreApiError.RESOURCE_ALREADY_EXISTS,
                "Already exists a film with name: " + toInsert.getName());
        }
    }
}
