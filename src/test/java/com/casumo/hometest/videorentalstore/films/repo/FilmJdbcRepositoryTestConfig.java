package com.casumo.hometest.videorentalstore.films.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import com.casumo.hometest.videorentalstore.config.DataSourceTestConfig;


/**
 * FilmJdbcRepositoryTestConfig class.
 */
@Configuration
@Profile("repotest")
@Import({DataSourceTestConfig.class})
public class FilmJdbcRepositoryTestConfig
{
    private final NamedParameterJdbcOperations jdbcTemplate;

    @Autowired
    FilmJdbcRepositoryTestConfig(final NamedParameterJdbcOperations jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    FilmRepository filmRepository()
    {
        return new FilmJdbcRepository(jdbcTemplate);
    }
}
