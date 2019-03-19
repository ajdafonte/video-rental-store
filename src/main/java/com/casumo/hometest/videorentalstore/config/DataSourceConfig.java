package com.casumo.hometest.videorentalstore.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;


/**
 * DataSourceConfig class.
 */
@Configuration
public class DataSourceConfig
{
    @Bean
    public DataSource getDataSource()
    {
        return new EmbeddedDatabaseBuilder()
            .setName("videorentalstoredb")
            .setType(EmbeddedDatabaseType.H2)
            .addScript("scripts/sql/schema.sql")
            .addScripts("scripts/sql/data.sql")
            .build();
    }
}
