package com.casumo.hometest.videorentalstore.customers.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import com.casumo.hometest.videorentalstore.config.DataSourceTestConfig;


/**
 * CustomerJdbcRepositoryConfig class.
 */
@TestConfiguration
@Profile("repotest")
@Import({DataSourceTestConfig.class})
public class CustomerJdbcRepositoryTestConfig
{
    private final NamedParameterJdbcOperations jdbcTemplate;

    @Autowired
    CustomerJdbcRepositoryTestConfig(final NamedParameterJdbcOperations jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    CustomerRepository customerRepository()
    {
        return new CustomerJdbcRepository(jdbcTemplate);
    }
}
