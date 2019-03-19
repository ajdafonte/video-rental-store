package com.casumo.hometest.videorentalstore.rentals.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.casumo.hometest.videorentalstore.common.error.VideoRentalStoreApiError;
import com.casumo.hometest.videorentalstore.common.error.VideoRentalStoreApiException;
import com.casumo.hometest.videorentalstore.common.infra.MappingTool;
import com.casumo.hometest.videorentalstore.customers.CustomerTestHelper;
import com.casumo.hometest.videorentalstore.customers.domain.Customer;
import com.casumo.hometest.videorentalstore.films.FilmTestHelper;
import com.casumo.hometest.videorentalstore.rentals.RentalTestHelper;
import com.casumo.hometest.videorentalstore.rentals.bizz.InsertRentalParameter;
import com.casumo.hometest.videorentalstore.rentals.bizz.PatchRentalParameter;
import com.casumo.hometest.videorentalstore.rentals.bizz.RentalServiceImpl;
import com.casumo.hometest.videorentalstore.rentals.domain.Rental;
import com.casumo.hometest.videorentalstore.rentals.domain.RentalItem;
import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;


/**
 * RentalRestControllerTest class - RentalRestController test class.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(RentalRestController.class)
class RentalRestControllerTest
{
    private static final String RENTALS_URI = "/rentals";
    private static final String RENTAL_BY_ID_URI = "/rentals/{id}";

    private static final String INVALID_REQUEST = "Invalid request";
    private static final String UNKNOWN_RESOURCE = "Resource not found";
    private static final String RENTAL_ITEM_ALREADY_RETURNED = "The following Rental Item was already returned";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RentalServiceImpl rentalService;

    // get all rentals - ok
    @Test
    void givenInventoryOfRentals_whenGetAllRentals_thenReturnAllRentals() throws Exception
    {
        // given
        final RentalItem mockRentalItem1 =
            RentalTestHelper.generateRentalItem(RentalTestHelper.MOCK_ID1,
                FilmTestHelper.MOCK_REGULAR_FILM,
                RentalTestHelper.MOCK_DAYS_RENTED1,
                BigDecimal.valueOf(90),
                BigDecimal.valueOf(0),
                RentalTestHelper.MOCK_START_DATETIME1,
                null);
        // old film with 3 days for rent
        final RentalItem mockRentalItem2 =
            RentalTestHelper.generateRentalItem(RentalTestHelper.MOCK_ID2,
                FilmTestHelper.MOCK_OLD_FILM,
                RentalTestHelper.MOCK_DAYS_RENTED2,
                BigDecimal.valueOf(30),
                BigDecimal.valueOf(0),
                RentalTestHelper.MOCK_START_DATETIME1,
                null);
        final Rental mockRental1 =
            RentalTestHelper.generateRental(RentalTestHelper.MOCK_ID1,
                CustomerTestHelper.MOCK_CUSTOMER1,
                RentalTestHelper.MOCK_START_DATETIME1,
                Arrays.asList(mockRentalItem1, mockRentalItem2));
        final RentalItem mockRentalItem3 =
            RentalTestHelper.generateRentalItem(RentalTestHelper.MOCK_ID3,
                FilmTestHelper.MOCK_NEW_RELEASE_FILM,
                RentalTestHelper.MOCK_DAYS_RENTED3,
                BigDecimal.valueOf(80),
                BigDecimal.valueOf(0),
                RentalTestHelper.MOCK_START_DATETIME1,
                null);
        final Rental mockRental2 =
            RentalTestHelper.generateRental(RentalTestHelper.MOCK_ID2,
                CustomerTestHelper.MOCK_CUSTOMER2,
                RentalTestHelper.MOCK_START_DATETIME2,
                Collections.singletonList(mockRentalItem3));
        final List<Rental> allRentals = Arrays.asList(mockRental1, mockRental2);

        final RentalItemRest rentalItemRest1 =
            RentalTestHelper.generateRentalItemRest(mockRentalItem1.getId(),
                FilmTestHelper.MOCK_REGULAR_FILM_REST,
                mockRentalItem1.getDaysrented(),
                mockRentalItem1.getPrice(),
                mockRentalItem1.getSurcharge(),
                MappingTool.offsetDateTimeOrNull(mockRentalItem1.getStartdatetime()),
                MappingTool.offsetDateTimeOrNull(mockRentalItem1.getEnddatetime()));
        final RentalItemRest rentalItemRest2 =
            RentalTestHelper.generateRentalItemRest(mockRentalItem2.getId(),
                FilmTestHelper.MOCK_OLD_FILM_REST,
                mockRentalItem2.getDaysrented(),
                mockRentalItem2.getPrice(),
                mockRentalItem2.getSurcharge(),
                MappingTool.offsetDateTimeOrNull(mockRentalItem2.getStartdatetime()),
                MappingTool.offsetDateTimeOrNull(mockRentalItem2.getEnddatetime()));
        final List<RentalItemRest> rentalItemsRest = Arrays.asList(rentalItemRest1, rentalItemRest2);
        final RentalRest expectedRentalRest1 =
            RentalTestHelper.generateRentalRest(mockRental1.getId(),
                MappingTool.offsetDateTimeOrNull(mockRental1.getStartdatetime()), BigDecimal.valueOf(120), BigDecimal.valueOf(0), rentalItemsRest);
        final RentalItemRest rentalItemRest3 =
            RentalTestHelper.generateRentalItemRest(mockRentalItem3.getId(),
                FilmTestHelper.MOCK_NEW_RELEASE_FILM_REST,
                mockRentalItem3.getDaysrented(),
                mockRentalItem3.getPrice(),
                mockRentalItem3.getSurcharge(),
                MappingTool.offsetDateTimeOrNull(mockRentalItem3.getStartdatetime()),
                MappingTool.offsetDateTimeOrNull(mockRentalItem3.getEnddatetime()));
        final RentalRest expectedRentalRest2 =
            RentalTestHelper.generateRentalRest(mockRental2.getId(),
                MappingTool.offsetDateTimeOrNull(mockRental2.getStartdatetime()), BigDecimal.valueOf(80), BigDecimal.valueOf(0),
                Collections.singletonList(rentalItemRest3));
        final List<RentalRest> expectedResult = Arrays.asList(expectedRentalRest1, expectedRentalRest2);

        // given
//        final List<Rental> allRentals = Arrays.asList(RentalTestHelper.MOCK_RENTAL1, RentalTestHelper.MOCK_RENTAL2);
//        final List<RentalRest> expectedResult = Arrays.asList(RentalTestHelper.MOCK_RENTAL_REST1, RentalTestHelper.MOCK_RENTAL_REST2);
        doReturn(allRentals).when(rentalService).findAll();
        final String expectedContent = generateSuccessBody(expectedResult);

        // when
        final ResultActions result = mvc.perform(get(RENTALS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.content().string(expectedContent));
        verify(rentalService, times(1)).findAll();
        verifyNoMoreInteractions(rentalService);
    }

    // get all rentals - empty data
    @Test
    void givenEmptyInventoryOfRentals_whenGetAllRentals_thenReturnEmptyResult() throws Exception
    {
        // given
        final List emptyList = Collections.emptyList();
        doReturn(emptyList).when(rentalService).findAll();
        final String expectedContent = generateSuccessBody(emptyList);

        // when
        final ResultActions result = mvc.perform(get(RENTALS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.content().string(expectedContent));
        verify(rentalService, times(1)).findAll();
        verifyNoMoreInteractions(rentalService);
    }

    // save rental - ok
    @Test
    void givenValidRequest_whenInsertNewRental_thenReturnNewRental() throws Exception
    {
        // given
        final RentalItem mockRentalItem1 =
            RentalTestHelper.generateRentalItem(RentalTestHelper.MOCK_ID1,
                FilmTestHelper.MOCK_REGULAR_FILM,
                RentalTestHelper.MOCK_DAYS_RENTED1,
                BigDecimal.valueOf(90),
                BigDecimal.valueOf(0),
                RentalTestHelper.MOCK_START_DATETIME1,
                null);
        // old film with 3 days for rent
        final RentalItem mockRentalItem2 =
            RentalTestHelper.generateRentalItem(RentalTestHelper.MOCK_ID2,
                FilmTestHelper.MOCK_OLD_FILM,
                RentalTestHelper.MOCK_DAYS_RENTED2,
                BigDecimal.valueOf(30),
                BigDecimal.valueOf(0),
                RentalTestHelper.MOCK_START_DATETIME1,
                null);
        // new release film with 2 days for rent
        final RentalItem mockRentalItem3 =
            RentalTestHelper.generateRentalItem(RentalTestHelper.MOCK_ID3,
                FilmTestHelper.MOCK_NEW_RELEASE_FILM,
                RentalTestHelper.MOCK_DAYS_RENTED3,
                BigDecimal.valueOf(80),
                BigDecimal.valueOf(0),
                RentalTestHelper.MOCK_START_DATETIME1,
                null);
        final Rental mockRental =
            RentalTestHelper.generateRental(RentalTestHelper.MOCK_ID1,
                CustomerTestHelper.MOCK_CUSTOMER1,
                RentalTestHelper.MOCK_START_DATETIME1,
                Arrays.asList(mockRentalItem1, mockRentalItem2, mockRentalItem3));

        doReturn(mockRental).when(rentalService).insert(any(InsertRentalParameter.class));

        final InsertRentalItemRequestBody rentalItemRequestBody1 =
            RentalTestHelper.generateInsertRentalItemRequestBody(FilmTestHelper.MOCK_REGULAR_FILM.getId(), mockRentalItem1.getDaysrented());
        final InsertRentalItemRequestBody rentalItemRequestBody2 =
            RentalTestHelper.generateInsertRentalItemRequestBody(FilmTestHelper.MOCK_OLD_FILM.getId(), mockRentalItem1.getDaysrented());
        final InsertRentalItemRequestBody rentalItemRequestBody3 =
            RentalTestHelper.generateInsertRentalItemRequestBody(FilmTestHelper.MOCK_NEW_RELEASE_FILM.getId(), mockRentalItem1.getDaysrented());
        final List<InsertRentalItemRequestBody> rentalItems = Arrays.asList(rentalItemRequestBody1, rentalItemRequestBody2, rentalItemRequestBody3);
        final InsertRentalRequestBody mockRequestBody =
            RentalTestHelper.generateInsertRentalRequestBody(mockRental.getCustomer().getId(), rentalItems);
        final String requestBody = generateRequestBody(mockRequestBody);

        final RentalItemRest rentalItemRest1 =
            RentalTestHelper.generateRentalItemRest(mockRentalItem1.getId(),
                FilmTestHelper.MOCK_REGULAR_FILM_REST,
                mockRentalItem1.getDaysrented(),
                mockRentalItem1.getPrice(),
                mockRentalItem1.getSurcharge(),
                MappingTool.offsetDateTimeOrNull(mockRentalItem1.getStartdatetime()),
                MappingTool.offsetDateTimeOrNull(mockRentalItem1.getEnddatetime()));
        final RentalItemRest rentalItemRest2 =
            RentalTestHelper.generateRentalItemRest(mockRentalItem2.getId(),
                FilmTestHelper.MOCK_OLD_FILM_REST,
                mockRentalItem2.getDaysrented(),
                mockRentalItem2.getPrice(),
                mockRentalItem2.getSurcharge(),
                MappingTool.offsetDateTimeOrNull(mockRentalItem2.getStartdatetime()),
                MappingTool.offsetDateTimeOrNull(mockRentalItem2.getEnddatetime()));
        final RentalItemRest rentalItemRest3 =
            RentalTestHelper.generateRentalItemRest(mockRentalItem3.getId(),
                FilmTestHelper.MOCK_NEW_RELEASE_FILM_REST,
                mockRentalItem3.getDaysrented(),
                mockRentalItem3.getPrice(),
                mockRentalItem3.getSurcharge(),
                MappingTool.offsetDateTimeOrNull(mockRentalItem3.getStartdatetime()),
                MappingTool.offsetDateTimeOrNull(mockRentalItem3.getEnddatetime()));
        final List<RentalItemRest> rentalItemsRest = Arrays.asList(rentalItemRest1, rentalItemRest2, rentalItemRest3);
        final RentalRest expectedRentalRest =
            RentalTestHelper.generateRentalRest(mockRental.getId(),
                MappingTool.offsetDateTimeOrNull(mockRental.getStartdatetime()), BigDecimal.valueOf(200), BigDecimal.valueOf(0), rentalItemsRest);
        final String expectedContent = generateRequestBody(expectedRentalRest);

        // when
        final ResultActions result = mvc.perform(post(RENTALS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isCreated());
        result.andExpect(MockMvcResultMatchers.content().string(expectedContent));
        verify(rentalService, times(1)).insert(any(InsertRentalParameter.class));
        verifyNoMoreInteractions(rentalService);
    }

    // save rental - nok (rental with invalid customer)
    @Test
    void givenRequestBodyWithInvalidCustomer_whenInsertNewRental_thenReturnNotFound() throws Exception
    {
        // given
        final long unknownCustomerId = RentalTestHelper.MOCK_UNKNOWN_ID;
        final RentalItem mockRentalItem1 =
            RentalTestHelper.generateRentalItem(RentalTestHelper.MOCK_ID1,
                FilmTestHelper.MOCK_REGULAR_FILM,
                RentalTestHelper.MOCK_DAYS_RENTED1,
                BigDecimal.valueOf(90),
                BigDecimal.valueOf(0),
                RentalTestHelper.MOCK_START_DATETIME1,
                null);
        final InsertRentalItemRequestBody rentalItemRequestBody1 =
            RentalTestHelper.generateInsertRentalItemRequestBody(FilmTestHelper.MOCK_REGULAR_FILM.getId(), mockRentalItem1.getDaysrented());
        final List<InsertRentalItemRequestBody> rentalItems = Collections.singletonList(rentalItemRequestBody1);
        final InsertRentalRequestBody mockRequestBody =
            RentalTestHelper.generateInsertRentalRequestBody(unknownCustomerId, rentalItems);
        final String requestBody = generateRequestBody(mockRequestBody);

        doThrow(new VideoRentalStoreApiException(VideoRentalStoreApiError.UNKNOWN_RESOURCE,
            "Customer was not found."))
            .when(rentalService)
            .insert(any(InsertRentalParameter.class));

        // when
        final ResultActions result = mvc.perform(post(RENTALS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isNotFound());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(UNKNOWN_RESOURCE)));
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString("Customer was not found.")));
        verify(rentalService, times(1)).insert(any(InsertRentalParameter.class));
        verifyNoMoreInteractions(rentalService);
    }

    // save rental - nok (rental with invalid film)
    @Test
    void givenRequestBodyWithInvalidFilm_whenInsertNewRental_thenReturnNotFound() throws Exception
    {
        // given
        final long unknownFilmId = RentalTestHelper.MOCK_UNKNOWN_ID;
        final RentalItem mockRentalItem1 =
            RentalTestHelper.generateRentalItem(RentalTestHelper.MOCK_ID1,
                FilmTestHelper.MOCK_REGULAR_FILM,
                RentalTestHelper.MOCK_DAYS_RENTED1,
                BigDecimal.valueOf(90),
                BigDecimal.valueOf(0),
                RentalTestHelper.MOCK_START_DATETIME1,
                null);
        final RentalItem mockRentalItem2 =
            RentalTestHelper.generateRentalItem(RentalTestHelper.MOCK_ID2,
                FilmTestHelper.MOCK_OLD_FILM,
                RentalTestHelper.MOCK_DAYS_RENTED2,
                BigDecimal.valueOf(30),
                BigDecimal.valueOf(0),
                RentalTestHelper.MOCK_START_DATETIME1,
                null);
        final Rental mockRental =
            RentalTestHelper.generateRental(RentalTestHelper.MOCK_ID1,
                CustomerTestHelper.MOCK_CUSTOMER1,
                RentalTestHelper.MOCK_START_DATETIME1,
                Arrays.asList(mockRentalItem1, mockRentalItem2));
        final InsertRentalItemRequestBody rentalItemRequestBody1 =
            RentalTestHelper.generateInsertRentalItemRequestBody(FilmTestHelper.MOCK_REGULAR_FILM.getId(), mockRentalItem1.getDaysrented());
        final InsertRentalItemRequestBody rentalItemRequestBody2 =
            RentalTestHelper.generateInsertRentalItemRequestBody(unknownFilmId, mockRentalItem2.getDaysrented());
        final List<InsertRentalItemRequestBody> rentalItems = Arrays.asList(rentalItemRequestBody1, rentalItemRequestBody2);
        final InsertRentalRequestBody mockRequestBody =
            RentalTestHelper.generateInsertRentalRequestBody(mockRental.getCustomer().getId(), rentalItems);
        final String requestBody = generateRequestBody(mockRequestBody);

        doThrow(new VideoRentalStoreApiException(VideoRentalStoreApiError.UNKNOWN_RESOURCE,
            "Film with id " + unknownFilmId + " does not exist."))
            .when(rentalService)
            .insert(any(InsertRentalParameter.class));

        // when
        final ResultActions result = mvc.perform(post(RENTALS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isNotFound());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(UNKNOWN_RESOURCE)));
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString("Film with id " + unknownFilmId + " does not exist.")));
        verify(rentalService, times(1)).insert(any(InsertRentalParameter.class));
        verifyNoMoreInteractions(rentalService);
    }

    // save rental - invalid body (null body)
    @Test
    void givenNullRequestBody_whenInsertNewRental_thenReturnBadRequest() throws Exception
    {
        // given
        final String requestBody = "{}";

        // when
        final ResultActions result = mvc.perform(post(RENTALS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST)));
        verifyZeroInteractions(rentalService);
    }

    // save rental - invalid body (body with missing values)
    @Test
    void givenIncompleteRequestBody_whenInsertNewRental_thenReturnBadRequest() throws Exception
    {
        // given
        final InsertRentalRequestBody parameter =
            RentalTestHelper.generateInsertRentalRequestBody(RentalTestHelper.MOCK_ID1, null);
        final String requestBody = generateRequestBody(parameter);

        // when
        final ResultActions result = mvc.perform(post(RENTALS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST)));
        verifyZeroInteractions(rentalService);
    }

    // save rental - invalid body (body with invalid values)
    @Test
    void givenInvalidRentalItemRequestBody_whenInsertNewRental_thenReturnBadRequest() throws Exception
    {
        // given
        final long invalidFilmId = -1;
        final InsertRentalItemRequestBody rentalItemRequestBody1 =
            RentalTestHelper.generateInsertRentalItemRequestBody(FilmTestHelper.MOCK_REGULAR_FILM.getId(), RentalTestHelper.MOCK_DAYS_RENTED1);
        final InsertRentalItemRequestBody rentalItemRequestBody2 =
            RentalTestHelper.generateInsertRentalItemRequestBody(invalidFilmId, RentalTestHelper.MOCK_DAYS_RENTED2);
        final List<InsertRentalItemRequestBody> rentalItems = Arrays.asList(rentalItemRequestBody1, rentalItemRequestBody2);
        final InsertRentalRequestBody parameter =
            RentalTestHelper.generateInsertRentalRequestBody(RentalTestHelper.MOCK_ID1, rentalItems);
        final String requestBody = generateRequestBody(parameter);

        // when
        final ResultActions result = mvc.perform(post(RENTALS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST)));
        verifyZeroInteractions(rentalService);
    }

    // save rental - invalid body (body with invalid customer id) -- this should be replaced in future
    @Test
    void givenInvalidCustomerId_whenInsertNewRental_thenReturnBadRequest() throws Exception
    {
        // given
        final long invalidCustomerId = -1;
        final InsertRentalItemRequestBody rentalItemRequestBody1 =
            RentalTestHelper.generateInsertRentalItemRequestBody(FilmTestHelper.MOCK_REGULAR_FILM.getId(), RentalTestHelper.MOCK_DAYS_RENTED1);
        final List<InsertRentalItemRequestBody> rentalItems = Collections.singletonList(rentalItemRequestBody1);
        final InsertRentalRequestBody parameter =
            RentalTestHelper.generateInsertRentalRequestBody(invalidCustomerId, rentalItems);
        final String requestBody = generateRequestBody(parameter);

        // when
        final ResultActions result = mvc.perform(post(RENTALS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST)));
        verifyZeroInteractions(rentalService);
    }

    // patch rental - ok
    @Test
    void givenValidRequest_whenPatchExistentRental_thenReturnPatchedRental() throws Exception
    {
        // given
        final Customer mockCustomer = CustomerTestHelper.MOCK_CUSTOMER1;
        final RentalItem mockRentalItem1 =
            RentalTestHelper.generateRentalItem(RentalTestHelper.MOCK_ID1,
                FilmTestHelper.MOCK_REGULAR_FILM,
                RentalTestHelper.MOCK_DAYS_RENTED1,
                BigDecimal.valueOf(90),
                BigDecimal.valueOf(0),
                RentalTestHelper.MOCK_START_DATETIME1,
                RentalTestHelper.MOCK_START_DATETIME1);
        // old film with 3 days for rent
        final RentalItem mockRentalItem2 =
            RentalTestHelper.generateRentalItem(RentalTestHelper.MOCK_ID2,
                FilmTestHelper.MOCK_OLD_FILM,
                RentalTestHelper.MOCK_DAYS_RENTED2,
                BigDecimal.valueOf(30),
                BigDecimal.valueOf(0),
                RentalTestHelper.MOCK_START_DATETIME1,
                null);
        final RentalItem mockRentalItem3 =
            RentalTestHelper.generateRentalItem(RentalTestHelper.MOCK_ID3,
                FilmTestHelper.MOCK_NEW_RELEASE_FILM,
                RentalTestHelper.MOCK_DAYS_RENTED3,
                BigDecimal.valueOf(80),
                BigDecimal.valueOf(40),
                RentalTestHelper.MOCK_START_DATETIME1,
                RentalTestHelper.MOCK_END_DATETIME1);
        final List<RentalItem> mockRentalItems = Arrays.asList(mockRentalItem1, mockRentalItem2, mockRentalItem3);
        final Rental mockRental =
            RentalTestHelper.generateRental(RentalTestHelper.MOCK_ID1,
                mockCustomer,
                RentalTestHelper.MOCK_START_DATETIME1,
                mockRentalItems);

        doReturn(mockRental).when(rentalService).patch(any(PatchRentalParameter.class));

        final List<Long> mockItemsIds = Arrays.asList(mockRentalItem1.getId(), mockRentalItem3.getId());
        final PatchRentalRequestBody mockRequestBody = RentalTestHelper.generatePatchRentalRequestBody(mockItemsIds);
        final String requestBody = generateRequestBody(mockRequestBody);

        final RentalItemRest rentalItemRest1 =
            RentalTestHelper.generateRentalItemRest(mockRentalItem1.getId(),
                FilmTestHelper.MOCK_REGULAR_FILM_REST,
                mockRentalItem1.getDaysrented(),
                mockRentalItem1.getPrice(),
                mockRentalItem1.getSurcharge(),
                MappingTool.offsetDateTimeOrNull(mockRentalItem1.getStartdatetime()),
                MappingTool.offsetDateTimeOrNull(mockRentalItem1.getEnddatetime()));
        final RentalItemRest rentalItemRest2 =
            RentalTestHelper.generateRentalItemRest(mockRentalItem2.getId(),
                FilmTestHelper.MOCK_OLD_FILM_REST,
                mockRentalItem2.getDaysrented(),
                mockRentalItem2.getPrice(),
                mockRentalItem2.getSurcharge(),
                MappingTool.offsetDateTimeOrNull(mockRentalItem2.getStartdatetime()),
                MappingTool.offsetDateTimeOrNull(mockRentalItem2.getEnddatetime()));
        final RentalItemRest rentalItemRest3 =
            RentalTestHelper.generateRentalItemRest(mockRentalItem3.getId(),
                FilmTestHelper.MOCK_NEW_RELEASE_FILM_REST,
                mockRentalItem3.getDaysrented(),
                mockRentalItem3.getPrice(),
                mockRentalItem3.getSurcharge(),
                MappingTool.offsetDateTimeOrNull(mockRentalItem3.getStartdatetime()),
                MappingTool.offsetDateTimeOrNull(mockRentalItem3.getEnddatetime()));
        final List<RentalItemRest> rentalItemsRest = Arrays.asList(rentalItemRest1, rentalItemRest2, rentalItemRest3);
        final RentalRest expectedRentalRest =
            RentalTestHelper.generateRentalRest(mockRental.getId(),
                MappingTool.offsetDateTimeOrNull(mockRental.getStartdatetime()), BigDecimal.valueOf(200), BigDecimal.valueOf(40), rentalItemsRest);
        final String expectedContent = generateRequestBody(expectedRentalRest);

        // when
        final ResultActions result = mvc.perform(patch(RENTAL_BY_ID_URI, mockCustomer.getId())
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.content().string(expectedContent));
        verify(rentalService, times(1)).patch(any(PatchRentalParameter.class));
        verifyNoMoreInteractions(rentalService);
    }

    // patch rental - nok (rental not found)
    @Test
    void givenInvalidRentalId_whenPatchExistentRental_thenReturnNotFound() throws Exception
    {
        // given
        final long unknownRentalId = RentalTestHelper.MOCK_UNKNOWN_ID;
        final List<Long> mockItemsIds = Arrays.asList(RentalTestHelper.MOCK_ID1, RentalTestHelper.MOCK_ID3);
        final PatchRentalRequestBody mockRequestBody = RentalTestHelper.generatePatchRentalRequestBody(mockItemsIds);
        final String requestBody = generateRequestBody(mockRequestBody);

        doThrow(new VideoRentalStoreApiException(VideoRentalStoreApiError.UNKNOWN_RESOURCE,
            "Rental was not found."))
            .when(rentalService)
            .patch(any(PatchRentalParameter.class));

        // when
        final ResultActions result = mvc.perform(patch(RENTAL_BY_ID_URI, unknownRentalId)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isNotFound());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(UNKNOWN_RESOURCE)));
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString("Rental was not found.")));
        verify(rentalService, times(1)).patch(any(PatchRentalParameter.class));
        verifyNoMoreInteractions(rentalService);
    }

    // patch rental - nok (item to return not found)
    @Test
    void givenNotExistentRentalItemId_whenPatchExistentRental_thenReturnNotFound() throws Exception
    {
        // given
        final long mockRentalId = RentalTestHelper.MOCK_ID1;
        final List<Long> mockItemsIds = Arrays.asList(RentalTestHelper.MOCK_ID1, RentalTestHelper.MOCK_UNKNOWN_ID);
        final PatchRentalRequestBody mockRequestBody = RentalTestHelper.generatePatchRentalRequestBody(mockItemsIds);
        final String requestBody = generateRequestBody(mockRequestBody);

        doThrow(new VideoRentalStoreApiException(VideoRentalStoreApiError.UNKNOWN_RESOURCE,
            "Rental Item with id " + RentalTestHelper.MOCK_UNKNOWN_ID + " does not exist in this Rental."))
            .when(rentalService)
            .patch(any(PatchRentalParameter.class));

        // when
        final ResultActions result = mvc.perform(patch(RENTAL_BY_ID_URI, mockRentalId)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isNotFound());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(UNKNOWN_RESOURCE)));
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(
            "Rental Item with id " + RentalTestHelper.MOCK_UNKNOWN_ID + " does not exist in this Rental.")));
        verify(rentalService, times(1)).patch(any(PatchRentalParameter.class));
        verifyNoMoreInteractions(rentalService);
    }

    // patch rental - nok (one item already returned)
    @Test
    void givenRentalItemIdAlreadyReturned_whenPatchExistentRental_thenReturnNotFound() throws Exception
    {
        // given
        final long mockRentalId = RentalTestHelper.MOCK_ID1;
        final List<Long> mockItemsIds = Arrays.asList(RentalTestHelper.MOCK_ID1, RentalTestHelper.MOCK_ID3);
        final PatchRentalRequestBody mockRequestBody = RentalTestHelper.generatePatchRentalRequestBody(mockItemsIds);
        final String requestBody = generateRequestBody(mockRequestBody);

        doThrow(new VideoRentalStoreApiException(VideoRentalStoreApiError.RENTAL_ITEM_ALREADY_RETURNED,
            "Rental Item was already returned."))
            .when(rentalService)
            .patch(any(PatchRentalParameter.class));

        // when
        final ResultActions result = mvc.perform(patch(RENTAL_BY_ID_URI, mockRentalId)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isConflict());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(RENTAL_ITEM_ALREADY_RETURNED)));
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString("Rental Item was already returned.")));
        verify(rentalService, times(1)).patch(any(PatchRentalParameter.class));
        verifyNoMoreInteractions(rentalService);
    }

    // patch rental - invalid body (null body)
    @Test
    void givenNullRequestBody_whenPatchExistentRental_thenReturnBadRequest() throws Exception
    {
        // given
        final long mockRentalId = RentalTestHelper.MOCK_ID1;
        final String requestBody = "{}";

        // when
        final ResultActions result = mvc.perform(patch(RENTAL_BY_ID_URI, mockRentalId)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST)));
        verifyZeroInteractions(rentalService);
    }

    // patch rental - body with invalid values
    @Test
    void givenRequestBodyWithInvalidItems_whenPatchExistentRental_thenReturnBadRequest() throws Exception
    {
        // given
        final long mockRentalId = RentalTestHelper.MOCK_ID1;
        final List<Long> mockItemsIds = Arrays.asList(RentalTestHelper.MOCK_ID1, -1L);
        final PatchRentalRequestBody mockRequestBody = RentalTestHelper.generatePatchRentalRequestBody(mockItemsIds);
        final String requestBody = generateRequestBody(mockRequestBody);

        // when
        final ResultActions result = mvc.perform(patch(RENTAL_BY_ID_URI, mockRentalId)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST)));
        verifyZeroInteractions(rentalService);
    }

    private String generateRequestBody(final RentalRest rentalRest)
    {
        return generateJsonObject(rentalRest).toString();
    }

    private String generateRequestBody(final InsertRentalRequestBody requestBody)
    {
        return generateJsonObject(requestBody).toString();
    }

    private String generateRequestBody(final PatchRentalRequestBody requestBody)
    {
        return generateJsonObject(requestBody).toString();
    }

    private JsonElement generateJsonObject(final PatchRentalRequestBody requestBody)
    {
        return new Gson().toJsonTree(requestBody);
    }

    private JsonElement generateJsonObject(final InsertRentalRequestBody requestBody)
    {
        return new Gson().toJsonTree(requestBody);
    }

    private JsonElement generateJsonObject(final RentalRest rentalRest)
    {
        final Gson gson = Converters.registerOffsetDateTime(new GsonBuilder()).create();
        return gson.toJsonTree(rentalRest);
    }

    private String generateSuccessBody(final List<RentalRest> rentals)
    {
        final JsonArray jsonArray = new JsonArray();
        rentals.forEach(rentalRest -> jsonArray.add(generateJsonObject(rentalRest)));
        return jsonArray.toString();
    }
}
