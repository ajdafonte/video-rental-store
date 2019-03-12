package com.casumo.hometest.videorentalstore.customers.rest;

import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * InsertCustomerRequestBody class.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(
    value = "InsertCustomerRequestBody",
    description = "Request body parameter to insert a new customer."
)
public class InsertCustomerRequestBody
{
    @ApiModelProperty(value = "The username of the customer.", required = true)
    @NotNull
    @Size(min = 1, max = 100)
    private String username;

    @ApiModelProperty(value = "The email of the customer.", required = true)
    @NotNull
    @Size(min = 1, max = 100)
    private String email;

    public InsertCustomerRequestBody()
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
        final InsertCustomerRequestBody that = (InsertCustomerRequestBody) o;
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
        return "InsertCustomerRequestBody{" +
            "username='" + username + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
