package com.casumo.hometest.videorentalstore.customers.bizz;

import java.util.Objects;


/**
 * InsertCustomerParameter class.
 */
public class InsertCustomerParameter
{
    private String username;
    private String email;

    public InsertCustomerParameter()
    {
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
        final InsertCustomerParameter that = (InsertCustomerParameter) o;
        return Objects.equals(username, that.username) &&
            Objects.equals(email, that.email);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(username, email);
    }

    @Override
    public String toString()
    {
        return "InsertCustomerParameter{" +
            "username='" + username + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
