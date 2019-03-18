package com.casumo.hometest.videorentalstore.customers.repo;

import static com.casumo.hometest.videorentalstore.customers.CustomerTestHelper.MOCK_NEW_CUSTOMER_BONUSPOINTS;
import static com.casumo.hometest.videorentalstore.customers.CustomerTestHelper.MOCK_NEW_CUSTOMER_EMAIL;
import static com.casumo.hometest.videorentalstore.customers.CustomerTestHelper.MOCK_NEW_CUSTOMER_USERNAME;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;

import com.casumo.hometest.videorentalstore.customers.CustomerTestHelper;
import com.casumo.hometest.videorentalstore.customers.domain.Customer;


/**
 * CustomerRepositoryIntegrationTest class - Integration test for CustomerRepository.
 */
@DataJpaTest()
class CustomerRepositoryIntegrationTest
{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager entityManager;

    // find all
    @Test
    void givenExistentCustomers_whenFindAll_thenReturnAllCustomers()
    {
        // given
        final Customer customer1 = CustomerTestHelper.generateCustomer(CustomerTestHelper.MOCK_USERNAME1,
            CustomerTestHelper.MOCK_EMAIL1,
            CustomerTestHelper.MOCK_BONUSPOINTS1);
        final Customer customer2 = CustomerTestHelper.generateCustomer(CustomerTestHelper.MOCK_USERNAME2,
            CustomerTestHelper.MOCK_EMAIL2,
            CustomerTestHelper.MOCK_BONUSPOINTS2);
        final List<Customer> mockCustomers = Arrays.asList(customer1, customer2);
        mockCustomers.forEach(customer -> entityManager.persistAndFlush(customer));

        // when
        final List<Customer> result = customerRepository.findAll();

        // then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertThat(result.size(), is(mockCustomers.size()));
        assertThat(result, hasItem(customer1));
        assertThat(result, hasItem(customer2));
    }

    // find all - empty collection
    @Test
    void givenEmptyCustomers_whenFindAll_thenReturnEmptyCollection()
    {
        // given
        // here empty records in db

        // when
        final List<Customer> result = customerRepository.findAll();

        // then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    // findById - ok
    @Test
    void givenExistentCustomer_whenFindById_thenReturnExistentCustomer()
    {
        // given
        final Customer mockCustomer = CustomerTestHelper.generateCustomer(
            CustomerTestHelper.MOCK_USERNAME1,
            CustomerTestHelper.MOCK_EMAIL1,
            MOCK_NEW_CUSTOMER_BONUSPOINTS);
        entityManager.persistAndFlush(mockCustomer);
        final Long id = (Long) entityManager.getId(mockCustomer);

        // when
        final Optional<Customer> result = customerRepository.findById(id);

        // then
        assertNotNull(result);
        final Customer customer = result.get();
        assertThat(customer.getId(), is(id));
        assertThat(customer.getUsername(), is(mockCustomer.getUsername()));
        assertThat(customer.getEmail(), is(mockCustomer.getEmail()));
        assertThat(customer.getBonuspoints(), is(mockCustomer.getBonuspoints()));
    }

    // findById - nok (id not found)
    @Test
    void givenNonexistentCustomerId_whenFindById_thenThrowSpecificException()
    {
        // given
        final Customer mockCustomer = CustomerTestHelper.generateCustomer(
            CustomerTestHelper.MOCK_USERNAME1,
            CustomerTestHelper.MOCK_EMAIL1,
            MOCK_NEW_CUSTOMER_BONUSPOINTS);
        entityManager.persistAndFlush(mockCustomer);

        // when
        final Optional<Customer> result = customerRepository.findById(CustomerTestHelper.MOCK_UNKNOWN_ID);

        // then
        assertFalse(result.isPresent());
    }

    // save - ok
    @Test
    void givenNewCustomer_whenSaveCustomer_thenReturnNewCustomerId()
    {
        // given
        final Customer customer1 = CustomerTestHelper.generateCustomer(CustomerTestHelper.MOCK_USERNAME1,
            CustomerTestHelper.MOCK_EMAIL1,
            CustomerTestHelper.MOCK_BONUSPOINTS1);
        final Customer customer2 = CustomerTestHelper.generateCustomer(CustomerTestHelper.MOCK_USERNAME2,
            CustomerTestHelper.MOCK_EMAIL2,
            CustomerTestHelper.MOCK_BONUSPOINTS2);
        final List<Customer> mockCustomers = Arrays.asList(customer1, customer2);
        mockCustomers.forEach(customer -> entityManager.persistAndFlush(customer));

        // when
        final Customer mockCustomer =
            CustomerTestHelper.generateCustomer(MOCK_NEW_CUSTOMER_USERNAME, MOCK_NEW_CUSTOMER_EMAIL, MOCK_NEW_CUSTOMER_BONUSPOINTS);
        final Customer result = customerRepository.save(mockCustomer);

        // then
        assertNotNull(result);
        assertThat(mockCustomer.getUsername(), is(result.getUsername()));
        assertThat(mockCustomer.getEmail(), is(result.getEmail()));
        assertThat(mockCustomer.getBonuspoints(), is(result.getBonuspoints()));
        final List<Customer> allCustomers = customerRepository.findAll();
        final int expectedNumCustomers = mockCustomers.size() + 1;
        assertThat(allCustomers.size(), is(expectedNumCustomers));
    }

    // save - duplicated (unique constraint)
    @Test
    void givenExistentCustomer_whenSaveCustomer_thenThrowSpecificException()
    {
        // given
        final Customer existentCustomer = CustomerTestHelper.generateCustomer(CustomerTestHelper.MOCK_USERNAME1,
            CustomerTestHelper.MOCK_EMAIL1,
            CustomerTestHelper.MOCK_BONUSPOINTS1);
        entityManager.persistAndFlush(existentCustomer);

        final Customer duplicatedCustomer = CustomerTestHelper.generateCustomer(CustomerTestHelper.MOCK_USERNAME1,
            CustomerTestHelper.MOCK_EMAIL1,
            CustomerTestHelper.MOCK_BONUSPOINTS1);

        // when + then
        assertThrows(DataIntegrityViolationException.class, () -> customerRepository.save(duplicatedCustomer));
    }

    // save - not null username

    @Test
    void givenCustomerWithNullUsername_whenSaveCustomer_thenThrowSpecificException()
    {
        // given
        final Customer existentCustomer =
            CustomerTestHelper.generateCustomer(null, CustomerTestHelper.MOCK_EMAIL1, 0);

        // when + then
        assertThrows(DataIntegrityViolationException.class, () -> customerRepository.save(existentCustomer));
    }
}
