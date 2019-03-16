package com.casumo.hometest.videorentalstore.films.rest;

import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * InsertFilmRequestBody class.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(
    value = "InsertFilmRequestBody",
    description = "Request body parameter to insert a new film."
)
public class InsertFilmRequestBody
{
    @ApiModelProperty(value = "The name of the film.", required = true)
    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    @ApiModelProperty(value = "The type ID of the film.", required = true)
    @NotNull
    private Long filmTypeId;

    public InsertFilmRequestBody()
    {
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public void setFilmTypeId(final Long filmTypeId)
    {
        this.filmTypeId = filmTypeId;
    }

    public String getName()
    {
        return name;
    }

    public Long getFilmTypeId()
    {
        return filmTypeId;
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
        final InsertFilmRequestBody that = (InsertFilmRequestBody) o;
        return Objects.equals(name, that.name) &&
            Objects.equals(filmTypeId, that.filmTypeId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, filmTypeId);
    }

    @Override
    public String toString()
    {
        return "InsertFilmRequestBody{" +
            "name='" + name + '\'' +
            ", filmTypeId=" + filmTypeId +
            '}';
    }
}
