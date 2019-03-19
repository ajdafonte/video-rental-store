package com.casumo.hometest.videorentalstore.customers.rest.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.customers.CustomerTestHelper;
import com.casumo.hometest.videorentalstore.customers.domain.Customer;
import com.casumo.hometest.videorentalstore.customers.rest.CustomerRest;


/**
 * CustomerRestMapperTest class - CustomerRestMapper test class.
 */
class CustomerRestMapperTest
{
    // map - ok
    @Test
    void givenValidCustomer_whenMapToRest_thenReturnCustomerRestObject()
    {
        // given
        final Customer mockCustomer = CustomerTestHelper.MOCK_CUSTOMER2;

        // when
        final CustomerRest result = CustomerRestMapper.map(mockCustomer);

        // then
        assertNotNull(result);
        assertThat(result.getId(), is(mockCustomer.getId()));
        assertThat(result.getUsername(), is(mockCustomer.getUsername()));
        assertThat(result.getEmail(), is(mockCustomer.getEmail()));
        assertThat(result.getBonuspoints(), is(mockCustomer.getBonuspoints()));
    }

    // map - null
    @Test
    void givenNullCustomer_whenMapToRest_thenReturnNullValue()
    {
        // given + when + then
        assertNull(CustomerRestMapper.map(null));
    }
}
