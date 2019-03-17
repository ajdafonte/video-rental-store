package com.casumo.hometest.videorentalstore.rentals.rest;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

import com.casumo.hometest.videorentalstore.rentals.RentalTestHelper;
import com.casumo.hometest.videorentalstore.rentals.bizz.RentalService;
import com.casumo.hometest.videorentalstore.rentals.domain.Rental;
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
    private static final String INVALID_REQUEST_PARAMETER = "Invalid request parameter";
    private static final String RESOURCE_NOT_FOUND = "Resource not found";
    private static final String RESOURCE_ALREADY_EXISTS = "Resource already exists";
    private static final String UNKNOWN_RESOURCE = "Resource not found";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RentalService rentalService;

    // get all rentals - ok
    @Test
    void givenInventoryOfRentals_whenGetAllRentals_thenReturnAllRentals() throws Exception
    {
        // given
        final List<Rental> allRentals = Arrays.asList(RentalTestHelper.MOCK_RENTAL1, RentalTestHelper.MOCK_RENTAL2);
        final List<RentalRest> expectedResult = Arrays.asList(RentalTestHelper.MOCK_RENTAL_REST1, RentalTestHelper.MOCK_RENTAL_REST2);
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

    private String generateSuccessBody(final RentalRest rental)
    {
        return generateJsonObject(rental).toString();
    }

}
