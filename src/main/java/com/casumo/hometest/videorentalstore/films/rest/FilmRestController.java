package com.casumo.hometest.videorentalstore.films.rest;

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
import com.casumo.hometest.videorentalstore.films.bizz.FilmService;
import com.casumo.hometest.videorentalstore.films.rest.mapper.FilmRestMapper;
import com.casumo.hometest.videorentalstore.films.rest.mapper.InsertFilmParameterMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * FilmController class.
 */
@RestController
@RequestMapping(value = VideoRentalStoreApiConstants.FILMS_RESOURCE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(
    tags = "Films",
    value = "Resources for accessing and processing films on the video rental system."
)
public class FilmRestController
{
    private static final Logger LOG = LoggerFactory.getLogger(FilmRestController.class);

    private final FilmService filmService;

    @Autowired
    public FilmRestController(final FilmService filmService)
    {
        this.filmService = filmService;
    }

    @GetMapping
    @ApiOperation(value = "Retrieve all the available films.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Returns a collection with all the available films.")})
    List<FilmRest> getFilms()
    {
        LOG.info(">> Request received in order to retrieve all the available films.");
        return filmService.findAll()
            .stream()
            .map(FilmRestMapper::map)
            .collect(Collectors.toList());
    }

    @GetMapping(value = VideoRentalStoreApiConstants.FILMS_ID_PATH_PARAM)
    @ApiOperation(value = "Retrieve a film specified by the ID.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns a collection with all the available films."),
        @ApiResponse(code = 400, message = "Bad Request."),
        @ApiResponse(code = 404, message = "The film specified by the ID was not found.")})
    FilmRest getFilm(@PathVariable
                     @ApiParam(value = "The ID of the film.", required = true) final long id)
    {
        LOG.info(">> Request received in order to retrieve a film with ID {}", id);
        return FilmRestMapper.map(filmService.findBy(id));
    }

    @PostMapping
    @ApiOperation(value = "Insert a new film in the system.")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "A new film was created with success."),
        @ApiResponse(code = 400, message = "Bad Request."),
        @ApiResponse(code = 409, message = "Conflict."),
    })
    @ResponseStatus(HttpStatus.CREATED)
    FilmRest insertFilm(@RequestBody
                        @ApiParam(value = "Request body parameter to insert a new film.", required = true)
                        @Valid final InsertFilmRequestBody requestBody)
    {
        LOG.info(">> Request received to insert a new film in the system.");
        return FilmRestMapper.map(filmService.insert(InsertFilmParameterMapper.map(requestBody)));
    }
}
