package com.casumo.hometest.videorentalstore.common.error;

import org.springframework.http.HttpStatus;

import com.google.common.base.MoreObjects;


/**
 * Holds all specific errors returned by Video Rental Store API.
 */
public enum VideoRentalStoreApiError
{
    // Default error code
    INTERNAL_SERVER_ERROR("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),

    // Common error codes for request handling
    UNKNOWN_RESOURCE("Resource not found. %s", HttpStatus.NOT_FOUND),
    INVALID_REQUEST("Invalid request: %s", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST_PARAMETER("Invalid request parameter '%s'", HttpStatus.BAD_REQUEST),
    RESOURCE_ALREADY_EXISTS("Resource already exists. %s", HttpStatus.CONFLICT),
    RENTAL_ITEM_ALREADY_RETURNED("The following Rental Item was already returned: %s", HttpStatus.CONFLICT);

    private final String errorText;
    private final HttpStatus httpStatus;

    VideoRentalStoreApiError(final String errorText, final HttpStatus httpStatus)
    {
        this.errorText = errorText;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode()
    {
        return name();
    }

    public String getErrorDescription(final String... parameters)
    {
        return String.format(errorText, (Object[]) parameters);
    }

    public HttpStatus getHttpStatus()
    {
        return httpStatus;
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this)
            .add("errorText", errorText)
            .add("httpStatus", httpStatus)
            .toString();
    }
}
