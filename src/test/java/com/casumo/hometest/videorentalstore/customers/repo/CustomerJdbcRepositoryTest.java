package com.casumo.hometest.videorentalstore.customers.repo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.casumo.hometest.videorentalstore.common.error.VideoRentalStoreApiException;
import com.casumo.hometest.videorentalstore.customers.CustomerTestHelper;
import com.casumo.hometest.videorentalstore.customers.domain.Customer;


/**
 * CustomerJdbcRepositoryTest class - Test CustomerJdbcRepository class.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CustomerJdbcRepositoryTestConfig.class)
@ActiveProfiles("repotest")
class CustomerJdbcRepositoryTest
{
    private static int INIT_NUM_CUSTOMERS = 2;

    private final CustomerRepository customerRepository;

    @Autowired
    CustomerJdbcRepositoryTest(final CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    // find all
    @Test
    void givenExistentCustomers_whenFindAll_thenReturnAllCustomers()
    {
        // when
        final List<Customer> result = customerRepository.findAll();

        // then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertThat(result.size(), is(INIT_NUM_CUSTOMERS));
        assertThat(result, Matchers.hasItem(CustomerTestHelper.MOCK_CUSTOMER1));
        assertThat(result, Matchers.hasItem(CustomerTestHelper.MOCK_CUSTOMER2));
    }

    // findById - ok
    @Test
    void givenExistentCustomerId_whenFindById_thenReturnExistentCustomer()
    {
        // given
        final Customer existentCustomer = CustomerTestHelper.MOCK_CUSTOMER1;

        // when
        final Customer result = customerRepository.findBy(CustomerTestHelper.MOCK_ID1);

        // then
        assertNotNull(result);
        assertEquals(existentCustomer, result);
        assertThat(result.getId(), is(existentCustomer.getId()));
        assertThat(result.getUsername(), is(existentCustomer.getUsername()));
        assertThat(result.getEmail(), is(existentCustomer.getEmail()));
        assertThat(result.getBonuspoints(), is(existentCustomer.getBonuspoints()));
    }

    // findById - nok (id not found)
    @Test
    void givenNotExistentCustomerId_whenFindById_thenThrowSpecificException()
    {
        // when + then
        assertThrows(VideoRentalStoreApiException.class, () -> customerRepository.findBy(CustomerTestHelper.MOCK_UNKNOWN_ID));
    }

    // save - ok
    @Test
    void givenNewCustomer_whenSaveCustomer_thenReturnNewCustomerId()
    {
        // when
        final Customer mockCustomer =
            CustomerTestHelper.generateCustomer(CustomerTestHelper.MOCK_NEW_CUSTOMER_USERNAME, CustomerTestHelper.MOCK_NEW_CUSTOMER_EMAIL);
        final long result = customerRepository.save(mockCustomer);

        // then
        assertThat(result, notNullValue());
        final List<Customer> allCustomers = customerRepository.findAll();
        assertThat(allCustomers.size(), is(++INIT_NUM_CUSTOMERS));
    }

    // save - duplicated
    @Test
    void givenExistentCustomer_whenSaveCustomer_thenThrowSpecificException()
    {
        // when + then
        assertThrows(VideoRentalStoreApiException.class, () -> customerRepository.save(CustomerTestHelper.MOCK_CUSTOMER1));
    }
}
