package com.casumo.hometest.videorentalstore.films.rest;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * FilmRest class.
 */
@ApiModel(value = "Film",
    description = "A Film on the Video Rental Store system.")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilmRest
{
    @ApiModelProperty(value = "The ID of the film.")
    private Long id;

    @ApiModelProperty(value = "The name of the film.")
    private String name;

    @ApiModelProperty(value = "The type of the film.")
    private FilmTypeRest type;

    public FilmRest()
    {
    }

    public Long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public FilmTypeRest getType()
    {
        return type;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public void setType(final FilmTypeRest type)
    {
        this.type = type;
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
        final FilmRest filmRest = (FilmRest) o;
        return Objects.equals(id, filmRest.id) &&
            Objects.equals(name, filmRest.name) &&
            type == filmRest.type;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, type);
    }

    @Override
    public String toString()
    {
        return "FilmRest{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", type=" + type +
            '}';
    }
}
