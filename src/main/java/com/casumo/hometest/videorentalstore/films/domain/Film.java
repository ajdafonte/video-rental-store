package com.casumo.hometest.videorentalstore.films.domain;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


/**
 * Film class.
 */
@Entity
@Table(
    name = "film",
    uniqueConstraints = @UniqueConstraint(columnNames = {"name"})
)
public class Film
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "typeid", referencedColumnName = "id")
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

    public void setId(final long id)
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
        return id == film.id &&
            Objects.equals(name, film.name) &&
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
