package com.casumo.hometest.videorentalstore.films.rest;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * Rest representation for a film type (FilmType). This enum describes the film type of a film. See {@link
 * com.casumo.hometest.videorentalstore.films.domain.FilmType}.
 */
@ApiModel(value = "FilmTypeRest",
    description = "The type of the film.")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilmTypeRest
{
    @ApiModelProperty(value = "The ID of the film type.")
    private long id;

    @ApiModelProperty(value = "The name of the film type.")
    private String name;

    public FilmTypeRest()
    {
    }

    public long getId()
    {
        return id;
    }

    public void setId(final long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        final FilmTypeRest that = (FilmTypeRest) o;
        return id == that.id &&
            Objects.equals(name, that.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name);
    }

    @Override
    public String toString()
    {
        return "FilmTypeRest{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
