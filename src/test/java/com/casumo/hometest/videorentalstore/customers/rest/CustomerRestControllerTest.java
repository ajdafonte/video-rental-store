package com.casumo.hometest.videorentalstore.customers.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
import com.casumo.hometest.videorentalstore.customers.CustomerTestHelper;
import com.casumo.hometest.videorentalstore.customers.bizz.CustomerService;
import com.casumo.hometest.videorentalstore.customers.bizz.InsertCustomerParameter;
import com.casumo.hometest.videorentalstore.customers.domain.Customer;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;


/**
 * CustomerRestControllerTest class - CustomerRestController test class.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerRestController.class)
class CustomerRestControllerTest
{
    private static final String CUSTOMERS_URI = "/customers";
    private static final String CUSTOMER_BY_ID_URI = "/customers/{id}";

    private static final String INVALID_REQUEST = "Invalid request";
    private static final String INVALID_REQUEST_PARAMETER = "Invalid request parameter";
    private static final String RESOURCE_NOT_FOUND = "Resource not found";
    private static final String RESOURCE_ALREADY_EXISTS = "Resource already exists";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService customerService;

    // get all customers - ok
    @Test
    void givenExistentCustomers_whenGetAllCustomers_thenReturnAllCustomers() throws Exception
    {
        // given
        final List<Customer> allCustomers = Arrays.asList(CustomerTestHelper.MOCK_CUSTOMER1, CustomerTestHelper.MOCK_CUSTOMER2);
        final List<CustomerRest> expectedResult = Arrays.asList(CustomerTestHelper.MOCK_CUSTOMER_REST1, CustomerTestHelper.MOCK_CUSTOMER_REST2);
        doReturn(allCustomers).when(customerService).findAll();
        final String expectedContent = generateSuccessBody(expectedResult);

        // when
        final ResultActions result = mvc.perform(get(CUSTOMERS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.content().string(expectedContent));
        verify(customerService, times(1)).findAll();
        verifyNoMoreInteractions(customerService);
    }

    // get all customers - empty data
    @Test
    void givenEmptyCustomers_whenGetAllCustomers_thenReturnEmptyResult() throws Exception
    {
        // given
        final List emptyList = Collections.emptyList();
        doReturn(emptyList).when(customerService).findAll();
        final String expectedContent = generateSuccessBody(emptyList);

        // when
        final ResultActions result = mvc.perform(get(CUSTOMERS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.content().string(expectedContent));
        verify(customerService, times(1)).findAll();
        verifyNoMoreInteractions(customerService);
    }

    // get customer by id - ok
    @Test
    void givenExistentId_whenGetCustomerById_thenReturnExistentCustomer() throws Exception
    {
        // given
        final Customer mockCustomer = CustomerTestHelper.MOCK_CUSTOMER1;
        final CustomerRest expectedCustomer = CustomerTestHelper.MOCK_CUSTOMER_REST1;
        doReturn(mockCustomer).when(customerService).findBy(anyLong());
        final String expectedContent = generateSuccessBody(expectedCustomer);

        // when
        final ResultActions result = mvc.perform(get(CUSTOMER_BY_ID_URI, expectedCustomer.getId())
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.content().string(expectedContent));
        verify(customerService, times(1)).findBy(mockCustomer.getId());
        verifyNoMoreInteractions(customerService);
    }

    // get customer by id - invalid id (not a number)
    @Test
    void givenInvalidId_whenGetCustomerById_thenReturnBadRequest() throws Exception
    {
        // given
        final String unknownCustomerId = "lol";

        // then
        final ResultActions result = mvc.perform(get(CUSTOMER_BY_ID_URI, unknownCustomerId)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST_PARAMETER)));
        verifyZeroInteractions(customerService);
    }

    // get customer by id - not found in db
    @Test
    void givenNonExistentId_whenGetCustomerById_thenReturnNotFound() throws Exception
    {
        // given
        final long unknownCustomerId = CustomerTestHelper.MOCK_UNKNOWN_ID;
        doThrow(new VideoRentalStoreApiException(VideoRentalStoreApiError.UNKNOWN_RESOURCE, "Entity was not found."))
            .when(customerService)
            .findBy(unknownCustomerId);

        // when
        final ResultActions result = mvc.perform(get(CUSTOMER_BY_ID_URI, unknownCustomerId)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        // then
        result.andExpect(MockMvcResultMatchers.status().isNotFound());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(RESOURCE_NOT_FOUND)));
        verify(customerService, times(1)).findBy(unknownCustomerId);
        verifyNoMoreInteractions(customerService);
    }

    // save customer - ok
    @Test
    void givenValidRequest_whenInsertNewCustomer_thenReturnNewCustomer() throws Exception
    {
        // given
        final Customer newCustomer = CustomerTestHelper.MOCK_NEW_CUSTOMER;
        doReturn(newCustomer).when(customerService).insert(any(InsertCustomerParameter.class));
        final InsertCustomerRequestBody mockRequestBody = CustomerTestHelper.MOCK_INSERT_REQ_BODY1;
        final String requestBody = generateRequestBody(mockRequestBody);
        final CustomerRest expectedResult = CustomerTestHelper.MOCK_NEW_CUSTOMER_REST;
        final String expectedContent = generateSuccessBody(expectedResult);

        // when
        final ResultActions result = mvc.perform(post(CUSTOMERS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isCreated());
        result.andExpect(MockMvcResultMatchers.content().string(expectedContent));
        verify(customerService, times(1)).insert(any(InsertCustomerParameter.class));
        verifyNoMoreInteractions(customerService);
    }

    // save customer - invalid body (null body)
    @Test
    void givenNullRequestBody_whenInsertNewCustomer_thenReturnBadRequest() throws Exception
    {
        // given
        final String requestBody = "{}";

        // when
        final ResultActions result = mvc.perform(post(CUSTOMERS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST)));
        verifyZeroInteractions(customerService);
    }

    // save customer - invalid body (body with missing values)
    @Test
    void givenIncompleteRequestBody_whenInsertNewCustomer_thenReturnBadRequest() throws Exception
    {
        // given
        final InsertCustomerRequestBody parameter = CustomerTestHelper.generateInsertCustomerRequestBody(CustomerTestHelper.MOCK_USERNAME1);
        final String requestBody = generateRequestBody(parameter);

        // when
        final ResultActions result = mvc.perform(post(CUSTOMERS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST)));
        verifyZeroInteractions(customerService);
    }

    // save customer - invalid body (body with invalid values)
    @Test
    void givenRequestBodyWithInvalidValues_whenInsertNewCustomer_thenReturnBadRequest() throws Exception
    {
        // given
        final InsertCustomerRequestBody parameter = CustomerTestHelper.generateInsertCustomerRequestBody("", "");
        final String requestBody = generateRequestBody(parameter);

        // when
        final ResultActions result = mvc.perform(post(CUSTOMERS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(INVALID_REQUEST)));
        verifyZeroInteractions(customerService);
    }

    // save customer - nok (customer already existent)
    @Test
    void givenRequestBodyWithExistentCustomer_whenInsertNewCustomer_thenReturnConflict() throws Exception
    {
        // given
        final InsertCustomerRequestBody parameter = CustomerTestHelper.MOCK_INSERT_REQ_BODY1;
        final String requestBody = generateRequestBody(parameter);
        doThrow(new VideoRentalStoreApiException(VideoRentalStoreApiError.RESOURCE_ALREADY_EXISTS,
            "Violation of primary key or unique constraint."))
            .when(customerService)
            .insert(any(InsertCustomerParameter.class));

        // when
        final ResultActions result = mvc.perform(post(CUSTOMERS_URI)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isConflict());
        result.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString(RESOURCE_ALREADY_EXISTS)));
        verify(customerService, times(1)).insert(any(InsertCustomerParameter.class));
        verify(customerService, times(0)).findBy(anyLong());
        verifyNoMoreInteractions(customerService);
    }

    private JsonElement generateJsonObject(final CustomerRest customerRest)
    {
        return new Gson().toJsonTree(customerRest);
    }

    private JsonElement generateJsonObject(final InsertCustomerRequestBody requestBody)
    {
        return new Gson().toJsonTree(requestBody);
    }

    private String generateSuccessBody(final List<CustomerRest> customers)
    {
        final JsonArray jsonArray = new JsonArray();
        customers.forEach(customerRest -> jsonArray.add(generateJsonObject(customerRest)));
        return jsonArray.toString();
    }

    private String generateSuccessBody(final CustomerRest customer)
    {
        return generateJsonObject(customer).toString();
    }

    private String generateRequestBody(final InsertCustomerRequestBody parameter)
    {
        return generateJsonObject(parameter).toString();
    }

}
