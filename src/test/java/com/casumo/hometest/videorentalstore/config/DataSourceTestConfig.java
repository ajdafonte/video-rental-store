package com.casumo.hometest.videorentalstore.config;

import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;


/**
 * DataSourceTestConfig class - Configuration needed for repository tests only.
 */
@Configuration
@Profile("repotest")
public class DataSourceTestConfig
{
    @Bean
    public DataSource getDataSource()
    {
        //set unique name so that each spring test context uses a different DB
        final String databaseName = UUID.randomUUID().toString();

        return new EmbeddedDatabaseBuilder()
            .setName(databaseName)
            .setType(EmbeddedDatabaseType.H2)
            .addScript("scripts/sql/schema-test.sql")
            .addScripts("scripts/sql/data-test.sql")
            .build();
    }

    @Bean
    public NamedParameterJdbcOperations jdbcTemplate()
    {
        return new NamedParameterJdbcTemplate(getDataSource());
    }
}
