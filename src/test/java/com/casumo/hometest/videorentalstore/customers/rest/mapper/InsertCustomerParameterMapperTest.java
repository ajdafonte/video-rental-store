package com.casumo.hometest.videorentalstore.customers.rest.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.customers.CustomerTestHelper;
import com.casumo.hometest.videorentalstore.customers.bizz.InsertCustomerParameter;
import com.casumo.hometest.videorentalstore.customers.rest.InsertCustomerRequestBody;


/**
 * InsertCustomerParameterMapperTest class - InsertCustomerParameterMapper test class.
 */
class InsertCustomerParameterMapperTest
{
    // map - ok
    @Test
    void givenValidInsertCustomerRequestBody_whenMapToParameter_thenReturnInsertCustomerParameter()
    {
        // given
        final InsertCustomerRequestBody mockRequestBody = CustomerTestHelper.MOCK_INSERT_REQ_BODY1;

        // when
        final InsertCustomerParameter result = InsertCustomerParameterMapper.map(mockRequestBody);

        // then
        assertNotNull(result);
        assertThat(result.getUsername(), is(mockRequestBody.getUsername()));
        assertThat(result.getEmail(), is(mockRequestBody.getEmail()));
    }

    // map - null
    @Test
    void givenNullInsertCustomerParameter_whenMapToRest_thenReturnNullValue()
    {
        assertNull(InsertCustomerParameterMapper.map(null));
    }

}
