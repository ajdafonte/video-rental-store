package com.casumo.hometest.videorentalstore.customers.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.casumo.hometest.videorentalstore.common.VideoRentalStoreApiConstants;
import com.casumo.hometest.videorentalstore.customers.bizz.CustomerService;
import com.casumo.hometest.videorentalstore.customers.rest.mapper.CustomerRestMapper;
import com.casumo.hometest.videorentalstore.customers.rest.mapper.InsertCustomerParameterMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * CustomerRestController class.
 */
@RestController
@RequestMapping(value = VideoRentalStoreApiConstants.CUSTOMERS_RESOURCE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(
    tags = "Customers",
    value = "Resources for accessing and processing customers on the video rental system."
)
public class CustomerRestController
{
    private static final Logger LOG = LoggerFactory.getLogger(CustomerRestController.class);

    private final CustomerService customerService;

    @Autowired
    public CustomerRestController(final CustomerService customerService)
    {
        this.customerService = customerService;
    }

    @GetMapping
    @ApiOperation(value = "Retrieve all available customers.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Returns a collection with all the available customers.")})
    List<CustomerRest> getCustomers()
    {
        LOG.info(">> Request received in order to retrieve all the available customers.");
        return customerService.findAll()
            .stream()
            .map(CustomerRestMapper::map)
            .collect(Collectors.toList());
    }

    @GetMapping(value = VideoRentalStoreApiConstants.CUSTOMERS_ID_PATH_PARAM)
    @ApiOperation(value = "Retrieve a customer specified by the ID.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns a collection with all the available customers."),
        @ApiResponse(code = 400, message = "Bad Request."),
        @ApiResponse(code = 404, message = "The customer specified by the ID was not found.")})
    CustomerRest getCustomer(@PathVariable
                             @ApiParam(value = "The ID of the customer.", required = true) final long id)
    {
        LOG.info(">> Request received in order to retrieve a customer with ID {}", id);
        return CustomerRestMapper.map(customerService.findBy(id));
    }

    @PostMapping
    @ApiOperation(value = "Insert a new customer in the system.")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "A customer was created with success."),
        @ApiResponse(code = 400, message = "Bad Request."),
        @ApiResponse(code = 409, message = "Conflict."),
    })
    @ResponseStatus(HttpStatus.CREATED)
    CustomerRest insertCustomer(@RequestBody
                                @ApiParam(value = "Request body parameter to insert a new customer.", required = true)
                                @Valid final InsertCustomerRequestBody requestBody)
    {
        LOG.info(">> Request received to insert a new customer in the system.");
        return CustomerRestMapper.map(customerService.insert(InsertCustomerParameterMapper.map(requestBody)));
    }
}
