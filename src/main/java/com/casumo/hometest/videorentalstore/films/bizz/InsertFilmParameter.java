package com.casumo.hometest.videorentalstore.films.bizz;

import java.util.Objects;


/**
 * InsertFilmParameter class.
 */
public class InsertFilmParameter
{
    private String name;
    private Long filmTypeId;

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public Long getFilmTypeId()
    {
        return filmTypeId;
    }

    public void setFilmTypeId(final Long filmTypeId)
    {
        this.filmTypeId = filmTypeId;
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
        final InsertFilmParameter that = (InsertFilmParameter) o;
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
        return "InsertFilmParameter{" +
            "name='" + name + '\'' +
            ", filmTypeId=" + filmTypeId +
            '}';
    }
}
