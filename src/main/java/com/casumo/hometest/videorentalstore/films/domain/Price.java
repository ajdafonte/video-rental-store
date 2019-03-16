package com.casumo.hometest.videorentalstore.films.domain;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Price class.
 */
@Entity
@Table(
    name = "price"
)
public class Price
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private BigDecimal value;

    public Price()
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

    public BigDecimal getValue()
    {
        return value;
    }

    public void setValue(final BigDecimal value)
    {
        this.value = value;
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
        final Price price = (Price) o;
        return id == price.id &&
            Objects.equals(name, price.name) &&
            Objects.equals(value, price.value);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, value);
    }

    @Override
    public String toString()
    {
        return "Price{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", value=" + value +
            '}';
    }
}
