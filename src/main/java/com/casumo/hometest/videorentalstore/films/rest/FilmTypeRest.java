package com.casumo.hometest.videorentalstore.films.rest;

import io.swagger.annotations.ApiModel;


/**
 * Rest representation for a film type (FilmType). This enum describes the film type of a film. See {@link
 * com.casumo.hometest.videorentalstore.films.domain.FilmType}.
 */
@ApiModel(value = "FilmTypeRest",
    description = "The type of the film.")
public enum FilmTypeRest
{
    NEW_RELEASE, REGULAR, OLD
}
