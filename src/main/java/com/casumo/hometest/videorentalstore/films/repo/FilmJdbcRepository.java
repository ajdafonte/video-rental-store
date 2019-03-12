package com.casumo.hometest.videorentalstore.films.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import com.casumo.hometest.videorentalstore.common.repo.JdbcTemplateRepository;
import com.casumo.hometest.videorentalstore.films.domain.Film;


/**
 * JdbcFilmRepository class.
 */
@Repository
public class FilmJdbcRepository extends JdbcTemplateRepository implements FilmRepository
{
    // Queries
    private static final String GET_STMT = "SELECT id, name, typeid FROM film";
    private static final String GET_BY_ID_STMT = GET_STMT + " WHERE id = :filmId";
    private static final String INSERT_STMT = "INSERT INTO film (name, typeid) VALUES (:name, :typeId)";

    @Autowired
    public FilmJdbcRepository(final NamedParameterJdbcOperations jdbcTemplate)
    {
        super(jdbcTemplate);
    }

    @Override
    public List<Film> findAll()
    {
        return list(GET_STMT, new FilmRowMapper());
    }

    @Override
    public Film findBy(final long id)
    {
        final MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("filmId", id);
        return single(GET_BY_ID_STMT, parameters, new FilmRowMapper());
    }

    @Override
    public long save(final Film film)
    {
        final MapSqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("name", film.getName())
            .addValue("typeId", film.getType().getValue());

        return insert(INSERT_STMT, parameters);
    }
}

