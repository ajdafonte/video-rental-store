package com.casumo.hometest.videorentalstore.common.rest;

import javax.servlet.http.HttpServletRequest;

import com.casumo.hometest.videorentalstore.common.error.VideoRentalStoreApiError;


/**
 * General error rest response object for the VideoRentalStore API.
 */
public class VideoRentalStoreErrorRest
{
    private final String description;
    private final String url;

    VideoRentalStoreErrorRest(final HttpServletRequest request, final VideoRentalStoreApiError error, final String... errorParameters)
    {
        this.description = error.getErrorDescription(errorParameters);
        this.url = request.getRequestURL().toString();
    }

    public String getDescription()
    {
        return description;
    }

    public String getUrl()
    {
        return url;
    }
}
