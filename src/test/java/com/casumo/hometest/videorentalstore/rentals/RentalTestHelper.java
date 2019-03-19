package com.casumo.hometest.videorentalstore.rentals;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.casumo.hometest.videorentalstore.common.infra.MappingTool;
import com.casumo.hometest.videorentalstore.customers.CustomerTestHelper;
import com.casumo.hometest.videorentalstore.customers.domain.Customer;
import com.casumo.hometest.videorentalstore.films.FilmTestHelper;
import com.casumo.hometest.videorentalstore.films.domain.Film;
import com.casumo.hometest.videorentalstore.films.rest.FilmRest;
import com.casumo.hometest.videorentalstore.rentals.bizz.InsertRentalItemParameter;
import com.casumo.hometest.videorentalstore.rentals.bizz.InsertRentalParameter;
import com.casumo.hometest.videorentalstore.rentals.bizz.PatchRentalParameter;
import com.casumo.hometest.videorentalstore.rentals.domain.Rental;
import com.casumo.hometest.videorentalstore.rentals.domain.RentalItem;
import com.casumo.hometest.videorentalstore.rentals.rest.InsertRentalItemRequestBody;
import com.casumo.hometest.videorentalstore.rentals.rest.InsertRentalRequestBody;
import com.casumo.hometest.videorentalstore.rentals.rest.PatchRentalRequestBody;
import com.casumo.hometest.videorentalstore.rentals.rest.RentalItemRest;
import com.casumo.hometest.videorentalstore.rentals.rest.RentalRest;


/**
 * RentalTestHelper class.
 */
public class RentalTestHelper
{
    public static final long MOCK_ID1 = 1L;
    public static final long MOCK_ID2 = 2L;
    public static final long MOCK_ID3 = 3L;
    public static final long MOCK_UNKNOWN_ID = 400L;

    public static final long MOCK_START_DATETIME1 = 1552608000000L; /* 15-03-2019 */
    public static final long MOCK_START_DATETIME2 = 1552694400000L; /* 16-03-2019 */
    public static final long MOCK_START_DATETIME3 = 1552780800000L; /* 17-03-2019 */
    public static final long MOCK_END_DATETIME1 = 1552867200000L; /* 18-03-2019 */
    public static final long MOCK_END_DATETIME2 = 1553040000000L; /* 20-03-2019 */

    public static final int MOCK_DAYS_RENTED1 = 5;
    public static final int MOCK_DAYS_RENTED2 = 3;
    public static final int MOCK_DAYS_RENTED3 = 2;

    public static final BigDecimal MOCK_PRICE1 = BigDecimal.valueOf(60);
    public static final BigDecimal MOCK_PRICE2 = BigDecimal.valueOf(80);

    public static final BigDecimal MOCK_SUBCHARGE1 = BigDecimal.valueOf(0);
    public static final BigDecimal MOCK_SUBCHARGE2 = BigDecimal.valueOf(30);

    public static final BigDecimal MOCK_TOTAL_PRICE1 = BigDecimal.valueOf(60);
    public static final BigDecimal MOCK_TOTAL_PRICE2 = BigDecimal.valueOf(140);

    public static final BigDecimal MOCK_TOTAL_SUBCHARGE1 = BigDecimal.valueOf(0);
    public static final BigDecimal MOCK_TOTAL_SUBCHARGE2 = BigDecimal.valueOf(30);

    public static final RentalItem MOCK_RENTAL_ITEM1;
    public static final RentalItem MOCK_RENTAL_ITEM2;

    public static final List<RentalItem> MOCK_RENTAL_ITEMS1;
    public static final List<RentalItem> MOCK_RENTAL_ITEMS2;

    public static final Rental MOCK_RENTAL1;
    public static final Rental MOCK_RENTAL2;

    public static final RentalItemRest MOCK_RENTAL_ITEM_REST1;
    public static final RentalItemRest MOCK_RENTAL_ITEM_REST2;
//    public static final RentalItemRest MOCK_RENTAL_ITEM_REST3;
//    public static final RentalItemRest MOCK_RENTAL_ITEM_REST4;

    public static final List<RentalItemRest> MOCK_RENTAL_ITEMS_REST1;
    public static final List<RentalItemRest> MOCK_RENTAL_ITEMS_REST2;

    public static final RentalRest MOCK_RENTAL_REST1;
    public static final RentalRest MOCK_RENTAL_REST2;

    //////////////

    public static final InsertRentalRequestBody MOCK_INSERT_RENTAL_REQUEST_BODY1;
    public static final InsertRentalItemRequestBody MOCK_INSERT_RENTAL_ITEM_REQUEST_BODY1;
    public static final InsertRentalItemRequestBody MOCK_INSERT_RENTAL_ITEM_REQUEST_BODY2;

    public static final InsertRentalParameter MOCK_INSERT_RENTAL_PARAMETER1;
    public static final InsertRentalItemParameter MOCK_INSERT_RENTAL_ITEM_PARAMETER1;
    public static final InsertRentalItemParameter MOCK_INSERT_RENTAL_ITEM_PARAMETER2;

    public static final PatchRentalRequestBody MOCK_PATCH_RENTAL_REQUEST_BODY1;
    public static final PatchRentalParameter MOCK_PATCH_RENTAL_PARAMETER1;

    // regular film with 5 days for rent
    public static final RentalItem MOCK_NEW_RENTAL_ITEM1 =
        generateRentalItem(MOCK_ID1, FilmTestHelper.MOCK_REGULAR_FILM, MOCK_DAYS_RENTED1, BigDecimal.valueOf(90),
            BigDecimal.valueOf(0), MOCK_START_DATETIME1, null);
    // old film with 3 days for rent
    public static final RentalItem MOCK_NEW_RENTAL_ITEM2 =
        generateRentalItem(MOCK_ID2, FilmTestHelper.MOCK_OLD_FILM, MOCK_DAYS_RENTED2, BigDecimal.valueOf(30),
            BigDecimal.valueOf(0), MOCK_START_DATETIME1, null);
    // new release film with 2 days for rent
    public static final RentalItem MOCK_NEW_RENTAL_ITEM3 =
        generateRentalItem(MOCK_ID3, FilmTestHelper.MOCK_NEW_RELEASE_FILM, MOCK_DAYS_RENTED3, BigDecimal.valueOf(80),
            BigDecimal.valueOf(0), MOCK_START_DATETIME1, null);
    public static final Rental MOCK_NEW_RENTAL1 =
        generateRental(MOCK_ID1,
            CustomerTestHelper.MOCK_CUSTOMER1,
            MOCK_START_DATETIME1,
            Arrays.asList(MOCK_NEW_RENTAL_ITEM1, MOCK_NEW_RENTAL_ITEM2, MOCK_NEW_RENTAL_ITEM3));

    public static final RentalItem MOCK_PATCHED_RENTAL_ITEM1 =
        generateRentalItem(MOCK_ID1, FilmTestHelper.MOCK_REGULAR_FILM, MOCK_DAYS_RENTED1, BigDecimal.valueOf(90),
            BigDecimal.valueOf(0), MOCK_START_DATETIME1, MOCK_END_DATETIME1);
    public static final RentalItem MOCK_PATCHED_RENTAL_ITEM3 =
        generateRentalItem(MOCK_ID3, FilmTestHelper.MOCK_NEW_RELEASE_FILM, MOCK_DAYS_RENTED3, BigDecimal.valueOf(80),
            BigDecimal.valueOf(40), MOCK_START_DATETIME1, MOCK_END_DATETIME1);
    public static final Rental MOCK_PATCHED_RENTAL1 = generateRental(MOCK_ID1,
        CustomerTestHelper.MOCK_CUSTOMER1,
        MOCK_START_DATETIME1,
        Arrays.asList(MOCK_PATCHED_RENTAL_ITEM1, MOCK_NEW_RENTAL_ITEM2, MOCK_PATCHED_RENTAL_ITEM3));

    public static final RentalItem MOCK_RETURNED_RENTAL_ITEM2 =
        generateRentalItem(MOCK_ID2, FilmTestHelper.MOCK_OLD_FILM, MOCK_DAYS_RENTED2, BigDecimal.valueOf(30),
            BigDecimal.valueOf(0), MOCK_START_DATETIME2, MOCK_END_DATETIME2);
    public static final Rental MOCK_RENTAL_WITH_ITEM_RETURNED =
        generateRental(MOCK_ID1,
            CustomerTestHelper.MOCK_CUSTOMER1,
            MOCK_START_DATETIME1,
            Arrays.asList(MOCK_NEW_RENTAL_ITEM1, MOCK_RETURNED_RENTAL_ITEM2));

    static
    {
        // domain objects
        MOCK_RENTAL_ITEM1 =
            generateRentalItem(MOCK_ID1, FilmTestHelper.MOCK_FILM1, MOCK_DAYS_RENTED1, MOCK_PRICE1, MOCK_SUBCHARGE1, MOCK_START_DATETIME1, null);
        MOCK_RENTAL_ITEM2 =
            generateRentalItem(MOCK_ID2, FilmTestHelper.MOCK_FILM2, MOCK_DAYS_RENTED2, MOCK_PRICE2, MOCK_SUBCHARGE2, MOCK_START_DATETIME2, null);
//        MOCK_RENTAL_ITEM3 =;
//        MOCK_RENTAL_ITEM4 =;

        MOCK_RENTAL_ITEMS1 = Collections.singletonList(MOCK_RENTAL_ITEM1);
        MOCK_RENTAL_ITEMS2 = Arrays.asList(MOCK_RENTAL_ITEM1, MOCK_RENTAL_ITEM2);

        MOCK_RENTAL1 = generateRental(MOCK_ID1, CustomerTestHelper.MOCK_CUSTOMER1, MOCK_START_DATETIME1, MOCK_RENTAL_ITEMS1);
        MOCK_RENTAL2 = generateRental(MOCK_ID2, CustomerTestHelper.MOCK_CUSTOMER2, MOCK_START_DATETIME2, MOCK_RENTAL_ITEMS2);

        /*
         * rest objects
         */

        MOCK_RENTAL_ITEM_REST1 = generateRentalItemRest(MOCK_ID1, FilmTestHelper.MOCK_FILM_REST1, MOCK_DAYS_RENTED1, MOCK_PRICE1, MOCK_SUBCHARGE1,
            MappingTool.offsetDateTimeOrNull(MOCK_START_DATETIME1), null);
        MOCK_RENTAL_ITEM_REST2 = generateRentalItemRest(MOCK_ID2, FilmTestHelper.MOCK_FILM_REST2, MOCK_DAYS_RENTED2, MOCK_PRICE2, MOCK_SUBCHARGE2,
            MappingTool.offsetDateTimeOrNull(MOCK_START_DATETIME2), null);

//     MOCK_RENTAL_ITEM_REST3 = ;
//     MOCK_RENTAL_ITEM_REST4 = ;

        MOCK_RENTAL_ITEMS_REST1 = Collections.singletonList(MOCK_RENTAL_ITEM_REST1);
        MOCK_RENTAL_ITEMS_REST2 = Arrays.asList(MOCK_RENTAL_ITEM_REST1, MOCK_RENTAL_ITEM_REST2);

        MOCK_RENTAL_REST1 = generateRentalRest(MOCK_ID1,
            MappingTool.offsetDateTimeOrNull(MOCK_START_DATETIME1),
            MOCK_TOTAL_PRICE1,
            MOCK_TOTAL_SUBCHARGE1,
            MOCK_RENTAL_ITEMS_REST1);
        MOCK_RENTAL_REST2 = generateRentalRest(MOCK_ID2,
            MappingTool.offsetDateTimeOrNull(MOCK_START_DATETIME2),
            MOCK_TOTAL_PRICE2,
            MOCK_TOTAL_SUBCHARGE2,
            MOCK_RENTAL_ITEMS_REST2);

        ///////////////
        MOCK_INSERT_RENTAL_ITEM_REQUEST_BODY1 = generateInsertRentalItemRequestBody(MOCK_ID1, MOCK_DAYS_RENTED1);
        MOCK_INSERT_RENTAL_ITEM_REQUEST_BODY2 = generateInsertRentalItemRequestBody(MOCK_ID2, MOCK_DAYS_RENTED2);
        MOCK_INSERT_RENTAL_REQUEST_BODY1 =
            generateInsertRentalRequestBody(MOCK_ID1, Arrays.asList(MOCK_INSERT_RENTAL_ITEM_REQUEST_BODY1, MOCK_INSERT_RENTAL_ITEM_REQUEST_BODY2));

        MOCK_INSERT_RENTAL_ITEM_PARAMETER1 = generateInsertRentalItemParameter(MOCK_ID1, MOCK_DAYS_RENTED1);
        MOCK_INSERT_RENTAL_ITEM_PARAMETER2 = generateInsertRentalItemParameter(MOCK_ID2, MOCK_DAYS_RENTED2);
        MOCK_INSERT_RENTAL_PARAMETER1 =
            generateInsertRentalParameter(MOCK_ID1, Arrays.asList(MOCK_INSERT_RENTAL_ITEM_PARAMETER1, MOCK_INSERT_RENTAL_ITEM_PARAMETER2));

        MOCK_PATCH_RENTAL_REQUEST_BODY1 = generatePatchRentalRequestBody(Arrays.asList(MOCK_ID1, MOCK_ID2));
        MOCK_PATCH_RENTAL_PARAMETER1 = generatePatchRentalParameter(MOCK_ID1, Arrays.asList(MOCK_ID1, MOCK_ID2));

    }

    public static Rental generateRental(final long id,
                                        final Customer customer,
                                        final Long startdatetime,
                                        final List<RentalItem> rentalItems)
    {
        final Rental rental = new Rental();
        rental.setId(id);
        rental.setCustomer(customer);
        rental.setStartdatetime(startdatetime);
        rental.setRentalItems(rentalItems);
        return rental;
    }

    public static Rental generateRental(final Customer customer,
                                        final Long startdatetime,
                                        final List<RentalItem> rentalItems)
    {
        final Rental rental = new Rental();
        rental.setCustomer(customer);
        rental.setStartdatetime(startdatetime);
        rental.setRentalItems(rentalItems);
        return rental;
    }

    public static RentalItem generateRentalItem(final long id,
                                                final Film film,
                                                final int daysrented,
                                                final BigDecimal price,
                                                final BigDecimal surcharge, final Long startdatettime, final Long enddatettime)
    {
        final RentalItem rentalItem = new RentalItem();
        rentalItem.setId(id);
        rentalItem.setFilm(film);
        rentalItem.setDaysrented(daysrented);
        rentalItem.setPrice(price);
        rentalItem.setSurcharge(surcharge);
        rentalItem.setStartdatetime(startdatettime);
        rentalItem.setEnddatetime(enddatettime);
//        rentalItem.setRental(rental);
        return rentalItem;
    }

    public static RentalItem generateRentalItem(final Film film,
                                                final int daysrented,
                                                final BigDecimal price,
                                                final BigDecimal surcharge, final Long startdatettime, final Long enddatettime)
    {
        final RentalItem rentalItem = new RentalItem();
        rentalItem.setFilm(film);
        rentalItem.setDaysrented(daysrented);
        rentalItem.setPrice(price);
        rentalItem.setSurcharge(surcharge);
        rentalItem.setStartdatetime(startdatettime);
        rentalItem.setEnddatetime(enddatettime);
//        rentalItem.setRental(rental);
        return rentalItem;
    }

    public static RentalRest generateRentalRest(final long id,
                                                final OffsetDateTime startdatetime,
                                                final BigDecimal totalPrice,
                                                final BigDecimal totalSurcharge,
                                                final List<RentalItemRest> rentalItemsRest)
    {
        final RentalRest rentalRest = new RentalRest();
        rentalRest.setId(id);
        rentalRest.setStartDateTime(startdatetime);
        rentalRest.setTotalPrice(totalPrice);
        rentalRest.setTotalSurcharge(totalSurcharge);
        rentalRest.setRentalItems(rentalItemsRest);
        return rentalRest;
    }

    public static RentalItemRest generateRentalItemRest(final long id,
                                                        final FilmRest filmRest,
                                                        final int daysrented,
                                                        final BigDecimal price,
                                                        final BigDecimal surcharge,
                                                        final OffsetDateTime startdatetime, final OffsetDateTime enddatetime)
    {
        final RentalItemRest rentalItemRest = new RentalItemRest();
        rentalItemRest.setId(id);
        rentalItemRest.setFilm(filmRest);
        rentalItemRest.setDaysRented(daysrented);
        rentalItemRest.setPrice(price);
        rentalItemRest.setSurcharge(surcharge);
        rentalItemRest.setStartDateTime(startdatetime);
        rentalItemRest.setEndDateTime(enddatetime);
        return rentalItemRest;
    }

    public static InsertRentalItemRequestBody generateInsertRentalItemRequestBody(final long filmId, final int daysToRent)
    {
        final InsertRentalItemRequestBody itemRequestBody = new InsertRentalItemRequestBody();
        itemRequestBody.setFilmId(filmId);
        itemRequestBody.setDaysToRent(daysToRent);
        return itemRequestBody;
    }

    public static InsertRentalRequestBody generateInsertRentalRequestBody(final long customerId,
                                                                          final List<InsertRentalItemRequestBody> rentalItems)
    {
        final InsertRentalRequestBody rentalRequestBody = new InsertRentalRequestBody();
        rentalRequestBody.setCustomerId(customerId);
        rentalRequestBody.setRentalItems(rentalItems);
        return rentalRequestBody;
    }

    public static InsertRentalParameter generateInsertRentalParameter(final long customerId,
                                                                      final List<InsertRentalItemParameter> itemsToRent)
    {
        final InsertRentalParameter parameter = new InsertRentalParameter();
        parameter.setCustomerId(customerId);
        parameter.setItemsToRent(itemsToRent);
        return parameter;
    }

    public static InsertRentalItemParameter generateInsertRentalItemParameter(final long filmId, final int daysToRent)
    {
        final InsertRentalItemParameter parameter = new InsertRentalItemParameter();
        parameter.setFilmId(filmId);
        parameter.setDaysToRent(daysToRent);
        return parameter;
    }

    public static PatchRentalParameter generatePatchRentalParameter(final long rentalId, final List<Long> rentalItemsToReturn)
    {
        final PatchRentalParameter parameter = new PatchRentalParameter();
        parameter.setRentalId(rentalId);
        parameter.setRentalItemsToReturn(rentalItemsToReturn);
        return parameter;
    }

    public static PatchRentalRequestBody generatePatchRentalRequestBody(final List<Long> rentalItemsToReturn)
    {
        final PatchRentalRequestBody requestBody = new PatchRentalRequestBody();
        requestBody.setRentalItemsToReturn(rentalItemsToReturn);
        return requestBody;
    }

}
