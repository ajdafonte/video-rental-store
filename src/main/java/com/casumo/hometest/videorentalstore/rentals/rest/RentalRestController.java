package com.casumo.hometest.videorentalstore.rentals.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.casumo.hometest.videorentalstore.common.VideoRentalStoreApiConstants;
import com.casumo.hometest.videorentalstore.rentals.bizz.RentalServiceImpl;
import com.casumo.hometest.videorentalstore.rentals.rest.mapper.InsertRentalParameterMapper;
import com.casumo.hometest.videorentalstore.rentals.rest.mapper.PatchRentalParameterMapper;
import com.casumo.hometest.videorentalstore.rentals.rest.mapper.RentalRestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * RentalRestController class.
 */
@RestController
@RequestMapping(value = VideoRentalStoreApiConstants.RENTALS_RESOURCE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(
    tags = "Rentals",
    value = "Resources for accessing and processing rentals on the video rental system."
)
public class RentalRestController
{
    private static final Logger LOG = LoggerFactory.getLogger(RentalRestController.class);

    private final RentalServiceImpl rentalService;

    @Autowired
    public RentalRestController(final RentalServiceImpl rentalService)
    {
        this.rentalService = rentalService;
    }

    @GetMapping
    @ApiOperation(value = "Retrieve all the available rentals.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Returns a collection with all the available rentals.")})
    List<RentalRest> getRentals()
    {
        LOG.info(">> Request received in order to retrieve all the available rentals.");
        return rentalService.findAll()
            .stream()
            .map(RentalRestMapper::map)
            .collect(Collectors.toList());
    }

    @PostMapping
    @ApiOperation(value = "Insert a new rental in the system.")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "A new rental was created with success."),
        @ApiResponse(code = 400, message = "Bad Request."),
        @ApiResponse(code = 404, message = "Resource not found.")
    })
    @ResponseStatus(HttpStatus.CREATED)
    RentalRest insertRental(@RequestBody
                            @ApiParam(value = "Request body parameter to insert a new rental.", required = true)
                            @Valid final InsertRentalRequestBody requestBody)
    {
        LOG.info(">> Request received to insert a new rental in the system.");
        return RentalRestMapper.map(rentalService.insert(InsertRentalParameterMapper.map(requestBody)));
    }

    @PatchMapping(value = VideoRentalStoreApiConstants.RENTALS_ID_PATH_PARAM)
    @ApiOperation(value = "Patch a certain rental in the system. Currently, is only possible to return films from renting.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "The patch of the rental was performed successfully. All rental items were returned from rental."),
        @ApiResponse(code = 400, message = "Bad Request."),
        @ApiResponse(code = 404, message = "Resource not found."),
        @ApiResponse(code = 409, message = "Conflict.")
    })
    @ResponseStatus(HttpStatus.OK)
    RentalRest patchRental(@PathVariable
                           @ApiParam(value = "The ID of the rental.", required = true) final long id,
                           @RequestBody
                           @ApiParam(value = "Request body parameter to perform a patch operation in a rental object.", required = true)
                           @Valid final PatchRentalRequestBody requestBody)
    {
        LOG.info(">> Request received to patch a rental with ID {} in the system.", id);
        return RentalRestMapper.map(rentalService.patch(PatchRentalParameterMapper.map(id, requestBody)));
    }
}
