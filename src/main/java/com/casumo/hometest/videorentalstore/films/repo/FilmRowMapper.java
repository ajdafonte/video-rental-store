package com.casumo.hometest.videorentalstore.films.repo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.casumo.hometest.videorentalstore.films.domain.Film;
import com.casumo.hometest.videorentalstore.films.domain.FilmType;


/**
 * FilmRowMapper class.
 */
public class FilmRowMapper implements RowMapper<Film>
{

    @Override
    public Film mapRow(final ResultSet rs, final int rowNum) throws SQLException
    {
        final Film film = new Film();
        film.setId(rs.getLong("id"));
        film.setName(rs.getString("name"));
        film.setType(FilmType.getByValue(rs.getInt("typeid")));

        return film;
    }
}
