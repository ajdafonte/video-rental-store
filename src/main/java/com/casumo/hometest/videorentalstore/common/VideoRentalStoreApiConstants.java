package com.casumo.hometest.videorentalstore.common;

/**
 * VideoRentalStoreApiConstants class.
 */
public class VideoRentalStoreApiConstants
{
    // films
    public static final String FILMS_RESOURCE = "films";
    public static final String FILMS_ID_PATH_PARAM = "{id}";

    // customers
    public static final String CUSTOMERS_RESOURCE = "customers";
    public static final String CUSTOMERS_ID_PATH_PARAM = "{id}";

    // rentals
    public static final String RENTALS_RESOURCE = "rentals";
    public static final String RENTALS_ID_PATH_PARAM = "{id}";

    private VideoRentalStoreApiConstants()
    {
        // Suppresses default constructor, ensuring non-instantiability.
    }
}
