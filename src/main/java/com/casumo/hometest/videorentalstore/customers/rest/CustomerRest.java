package com.casumo.hometest.videorentalstore.customers.rest;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * CustomerRest class.
 */
@ApiModel(
    value = "Customer",
    description = "A Customer on the Video Rental Store system."
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerRest
{
    @ApiModelProperty(value = "The ID of the customer")
    private long id;

    @ApiModelProperty(value = "The username of the customer")
    private String username;

    @ApiModelProperty(value = "The email of the customer")
    private String email;

    @ApiModelProperty(value = "The current bonus points of the customer")
    private long bonuspoints;

    public CustomerRest()
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
        final CustomerRest that = (CustomerRest) o;
        return id == that.id &&
            bonuspoints == that.bonuspoints &&
            Objects.equals(username, that.username) &&
            Objects.equals(email, that.email);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, username, email, bonuspoints);
    }

    @Override
    public String toString()
    {
        return "CustomerRest{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", bonuspoints=" + bonuspoints +
            '}';
    }
}
