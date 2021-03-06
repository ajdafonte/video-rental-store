package com.casumo.hometest.videorentalstore.films.domain;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * FilmType class.
 */
@Entity
@Table(name = "filmtype")
public class FilmType
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "priceid", referencedColumnName = "id")
    private Price price;

    public FilmType()
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

    public Price getPrice()
    {
        return price;
    }

    public void setPrice(final Price price)
    {
        this.price = price;
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
        final FilmType type = (FilmType) o;
        return id == type.id &&
            Objects.equals(name, type.name) &&
            Objects.equals(price, type.price);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, price);
    }

    @Override
    public String toString()
    {
        return "FilmType{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", price=" + price +
            '}';
    }
}
