package com.casumo.hometest.videorentalstore.customers.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import com.casumo.hometest.videorentalstore.common.repo.JdbcTemplateRepository;
import com.casumo.hometest.videorentalstore.customers.domain.Customer;


/**
 * CustomerJdbcRepository class.
 */
@Repository
public class CustomerJdbcRepository extends JdbcTemplateRepository implements CustomerRepository
{
    // Queries
    private static final String GET_STMT = "SELECT id, username, email, bonuspoints FROM customer";
    private static final String GET_BY_ID_STMT = GET_STMT + " WHERE id = :customerid";
    private static final String INSERT_STMT = "INSERT INTO customer (username, email, bonuspoints) VALUES (:username, :email, :bonuspoints)";

    @Autowired
    protected CustomerJdbcRepository(final NamedParameterJdbcOperations jdbcTemplate)
    {
        super(jdbcTemplate);
    }

    @Override
    public List<Customer> findAll()
    {
        return list(GET_STMT, new CustomerRowMapper());
    }

    @Override
    public Customer findBy(final long id)
    {
        final MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("customerid", id);
        return single(GET_BY_ID_STMT, parameters, new CustomerRowMapper());
    }

    @Override
    public long save(final Customer customer)
    {
        final MapSqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("username", customer.getUsername())
            .addValue("email", customer.getEmail())
            .addValue("bonuspoints", customer.getBonuspoints());
        return insert(INSERT_STMT, parameters);
    }

    class CustomerRowMapper implements RowMapper<Customer>
    {
        @Override
        public Customer mapRow(final ResultSet rs, final int rowNum) throws SQLException
        {
            final Customer customer = new Customer();
            customer.setId(rs.getLong("id"));
            customer.setUsername(rs.getString("username"));
            customer.setEmail(rs.getString("email"));
            customer.setBonuspoints(rs.getLong("bonuspoints"));

            return customer;
        }
    }
}
