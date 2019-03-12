package com.casumo.hometest.videorentalstore.films.domain;

import java.util.Objects;


/**
 * Film class.
 */
public class Film
{
    private Long id;
    private String name;
    private FilmType type;

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public FilmType getType()
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

    public void setType(final FilmType type)
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
        final Film film = (Film) o;
        return Objects.equals(id, film.id) &&
            name.equals(film.name) &&
            type == film.type;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, type);
    }

    @Override
    public String toString()
    {
        return "Film{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", type=" + type +
            '}';
    }
}
