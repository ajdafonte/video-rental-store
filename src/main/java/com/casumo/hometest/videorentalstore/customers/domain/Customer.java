package com.casumo.hometest.videorentalstore.customers.domain;

import java.util.Objects;


/**
 * Customer class.
 */
public class Customer
{
    private long id;
    private String username;
    private String email;
    private long bonuspoints;

    public long getId()
    {
        return id;
    }

    public void setId(final long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(final String username)
    {
        this.username = username;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(final String email)
    {
        this.email = email;
    }

    public long getBonuspoints()
    {
        return bonuspoints;
    }

    public void setBonuspoints(final long bonuspoints)
    {
        this.bonuspoints = bonuspoints;
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
        final Customer customer = (Customer) o;
        return id == customer.id &&
            bonuspoints == customer.bonuspoints &&
            Objects.equals(username, customer.username) &&
            Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, username, email, bonuspoints);
    }

    @Override
    public String toString()
    {
        return "Customer{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", bonuspoints=" + bonuspoints +
            '}';
    }
}
