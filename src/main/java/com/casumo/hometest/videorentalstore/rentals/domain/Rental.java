package com.casumo.hometest.videorentalstore.rentals.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.casumo.hometest.videorentalstore.customers.domain.Customer;


/**
 * Rental class.
 */
@Entity
@Table(
    name = "rental"
)
public class Rental
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Long startdatetime;

    @OneToOne()
    @JoinColumn(name = "customerid", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JoinColumn(name = "rentalid")
    private List<RentalItem> rentalItems;

    public Rental()
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

    public Long getStartdatetime()
    {
        return startdatetime;
    }

    public void setStartdatetime(final Long startdatetime)
    {
        this.startdatetime = startdatetime;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(final Customer customer)
    {
        this.customer = customer;
    }

    public List<RentalItem> getRentalItems()
    {
        return rentalItems;
    }

    public void setRentalItems(final List<RentalItem> rentalItems)
    {
        this.rentalItems = rentalItems;
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
        final Rental rental = (Rental) o;
        return id == rental.id &&
            Objects.equals(startdatetime, rental.startdatetime) &&
            Objects.equals(customer, rental.customer) &&
            Objects.equals(rentalItems, rental.rentalItems);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, startdatetime, customer, rentalItems);
    }

    @Override
    public String toString()
    {
        return "Rental{" +
            "id=" + id +
            ", startdatetime=" + startdatetime +
            ", customer=" + customer +
            ", rentalItems=" + rentalItems +
            '}';
    }
}
