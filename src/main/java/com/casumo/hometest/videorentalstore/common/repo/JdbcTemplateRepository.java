package com.casumo.hometest.videorentalstore.common.repo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.casumo.hometest.videorentalstore.common.error.VideoRentalStoreApiError;
import com.casumo.hometest.videorentalstore.common.error.VideoRentalStoreApiException;


/**
 * JdbcTemplateRepository class - Base class for common repo functionality.
 */
public abstract class JdbcTemplateRepository
{
    private static final Logger LOG = LoggerFactory.getLogger(JdbcTemplateRepository.class);

    private final NamedParameterJdbcOperations jdbcTemplate;

    protected JdbcTemplateRepository(final NamedParameterJdbcOperations jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Throws a {@link VideoRentalStoreApiException} with {@link VideoRentalStoreApiError#UNKNOWN_RESOURCE} if the result is empty or not a single
     * row.
     */
    protected <T> T single(final String stmt, final SqlParameterSource parameters, final RowMapper<T> rowMapper)
    {
        try
        {
            final T singleResult = DataAccessUtils.requiredSingleResult(jdbcTemplate.query(stmt, parameters, rowMapper));

            LOG.debug("Returning single result '{}'", singleResult);

            return singleResult;
        }
        catch (final EmptyResultDataAccessException e)
        {
            throw new VideoRentalStoreApiException(VideoRentalStoreApiError.UNKNOWN_RESOURCE, "Entity was not found.");
        }
    }

    /**
     * Throws a {@link VideoRentalStoreApiException} with {@link VideoRentalStoreApiError#UNKNOWN_RESOURCE} if the result is empty or not a single
     * row.
     */
    protected <T> T single(final String stmt, final RowMapper<T> rowMapper)
    {
        try
        {
            final T singleResult = DataAccessUtils.requiredSingleResult(jdbcTemplate.query(stmt, rowMapper));

            LOG.debug("Returning single result '{}'", singleResult);

            return singleResult;
        }
        catch (final EmptyResultDataAccessException e)
        {
            throw new VideoRentalStoreApiException(VideoRentalStoreApiError.UNKNOWN_RESOURCE, "Entity was not found.");
        }
    }

    /**
     * Returns a single result found or null
     */
    protected <T> T singleOrNull(final String stmt, final SqlParameterSource parameters, final RowMapper<T> rowMapper)
    {
        try
        {
            final T singleResult = DataAccessUtils.requiredSingleResult(jdbcTemplate.query(stmt, parameters, rowMapper));

            LOG.debug("Returning single result '{}'", singleResult);

            return singleResult;
        }
        catch (final EmptyResultDataAccessException e)
        {
            LOG.debug("Returning null, since nothing was found.");
            return null;
        }
    }

    /**
     * Returns a single result found or null
     */
    protected <T> T singleOrNull(final String stmt, final RowMapper<T> rowMapper)
    {
        try
        {
            final T singleResult = DataAccessUtils.requiredSingleResult(jdbcTemplate.query(stmt, rowMapper));

            LOG.debug("Returning single result '{}'", singleResult);

            return singleResult;
        }
        catch (final EmptyResultDataAccessException e)
        {
            LOG.debug("Returning null, since nothing was found.");
            return null;
        }
    }

    /**
     * Returns a list of entities based on the <code>select</code>.
     */
    protected <T> List<T> list(final String stmt, final SqlParameterSource parameters, final RowMapper<T> rowMapper)
    {
        final List<T> list = jdbcTemplate.query(stmt, parameters, rowMapper);

        LOG.debug("Returning list result with {} elements", list.size());

        return list;
    }

    /**
     * Returns a list of entities based on the <code>select</code>.
     */
    protected <T> List<T> list(final String stmt, final RowMapper<T> rowMapper)
    {
        final List<T> list = jdbcTemplate.query(stmt, rowMapper);

        LOG.debug("Returning list result with {} elements", list.size());

        return list;
    }

    //    protected long insert(final String stmt, final SqlParameterSource parameters, final Pair<VideoRentalStoreApiError, String> errorHandler)

    /**
     * Throws a {@link VideoRentalStoreApiException} with {@link VideoRentalStoreApiError#RESOURCE_ALREADY_EXISTS} if occurs a violation of primary
     * key or unique constraint.
     */
    protected long insert(final String stmt, final SqlParameterSource parameters)
    {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        final long entityId;

        try
        {
            final int rows = jdbcTemplate.update(stmt, parameters, keyHolder);

            entityId = keyHolder.getKey().longValue();

            LOG.debug("Inserted " + rows + " new row(s) with ID '{}'", entityId);
        }
        catch (final DuplicateKeyException e)
        {
            throw new VideoRentalStoreApiException(VideoRentalStoreApiError.RESOURCE_ALREADY_EXISTS,
                "Violation of primary key or unique constraint.");
        }

        return entityId;
    }

//    private void handleException(final Exception e, final Pair<VideoRentalStoreApiError, String> errorHandler)
//    {
//        if (errorHandler == null)
//        {
//            LOG.debug("DML statement failed, no error handler specified.", e);
//        }
//        else
//        {
//            LOG.debug("DML statement failed, trying to resolve reason.", e);
//
//            throw new VideoRentalStoreApiException(errorHandler.getKey(), errorHandler.getValue());
//        }
//    }
}
