package com.casumo.hometest.videorentalstore.rentals.domain;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.casumo.hometest.videorentalstore.films.domain.Film;


/**
 * RentalItem class.
 */
@Entity
@Table(
    name = "rentalitem"
)
public class RentalItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int daysrented;

    private BigDecimal price;

    private BigDecimal subcharge;

    private Long startdatetime;

    private Long enddatetime;

    @OneToOne()
    @JoinColumn(name = "filmid", referencedColumnName = "id")
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rentalid")
    private Rental rental;

    public long getId()
    {
        return id;
    }

    public void setId(final long id)
    {
        this.id = id;
    }

    public int getDaysrented()
    {
        return daysrented;
    }

    public void setDaysrented(final int daysrented)
    {
        this.daysrented = daysrented;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(final BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getSubcharge()
    {
        return subcharge;
    }

    public void setSubcharge(final BigDecimal subcharge)
    {
        this.subcharge = subcharge;
    }

    public Long getStartdatetime()
    {
        return startdatetime;
    }

    public void setStartdatetime(final Long startdatetime)
    {
        this.startdatetime = startdatetime;
    }

    public Long getEnddatetime()
    {
        return enddatetime;
    }

    public void setEnddatetime(final Long enddatetime)
    {
        this.enddatetime = enddatetime;
    }

    public Film getFilm()
    {
        return film;
    }

    public void setFilm(final Film film)
    {
        this.film = film;
    }

    public Rental getRental()
    {
        return rental;
    }

    public void setRental(final Rental rental)
    {
        this.rental = rental;
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
        final RentalItem that = (RentalItem) o;
        return id == that.id &&
            daysrented == that.daysrented &&
            Objects.equals(price, that.price) &&
            Objects.equals(subcharge, that.subcharge) &&
            Objects.equals(startdatetime, that.startdatetime) &&
            Objects.equals(enddatetime, that.enddatetime) &&
            Objects.equals(film, that.film) &&
            Objects.equals(rental, that.rental);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, daysrented, price, subcharge, startdatetime, enddatetime, film, rental);
    }

    @Override
    public String toString()
    {
        return "RentalItem{" +
            "id=" + id +
            ", daysrented=" + daysrented +
            ", price=" + price +
            ", subcharge=" + subcharge +
            ", startdatetime=" + startdatetime +
            ", enddatetime=" + enddatetime +
            ", film=" + film +
            '}';
    }
}
