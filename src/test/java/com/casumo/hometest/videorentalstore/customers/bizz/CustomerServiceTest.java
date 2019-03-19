package com.casumo.hometest.videorentalstore.customers.bizz;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.casumo.hometest.videorentalstore.common.error.VideoRentalStoreApiException;
import com.casumo.hometest.videorentalstore.customers.CustomerTestHelper;
import com.casumo.hometest.videorentalstore.customers.domain.Customer;
import com.casumo.hometest.videorentalstore.customers.repo.CustomerRepository;


/**
 * CustomerServiceTest class - Test CustomerService class.
 */
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest
{
    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    @BeforeEach
    void setUp()
    {
        this.customerService = new CustomerServiceImpl(customerRepository);
    }

    // getCustomers - with data
    @Test
    void givenExistentCustomers_whenFindAll_thenReturnAllCustomers()
    {
        // given
        final List<Customer> expected = Arrays.asList(CustomerTestHelper.MOCK_CUSTOMER1, CustomerTestHelper.MOCK_CUSTOMER2);
        when(customerRepository.findAll()).thenReturn(expected);

        // when
        final List<Customer> result = customerService.findAll();

        // then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertThat(result.size(), is(expected.size()));
        assertThat(result, containsInAnyOrder(CustomerTestHelper.MOCK_CUSTOMER1, CustomerTestHelper.MOCK_CUSTOMER2));
        verify(customerRepository, times(1)).findAll();
        verifyNoMoreInteractions(customerRepository);
    }

    // getCustomers - without data
    @Test
    void givenNoCustomers_whenFindAll_thenReturnEmptyCollection()
    {
        // given
        when(customerRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        final List<Customer> result = customerService.findAll();

        // then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(customerRepository, times(1)).findAll();
        verifyNoMoreInteractions(customerRepository);
    }

    // getCustomers - forbidden ??

    // getCustomerById - ok
    @Test
    void givenCustomersAndExistentId_whenFindById_thenReturnSpecificCustomer()
    {
        // given
        final Customer expected = CustomerTestHelper.MOCK_CUSTOMER1;
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(expected));

        // when
        final Customer result = customerService.findBy(CustomerTestHelper.MOCK_ID1);

        // then
        assertNotNull(result);
        assertEquals(expected, result);
        assertThat(result.getId(), is(expected.getId()));
        assertThat(result.getUsername(), is(expected.getUsername()));
        assertThat(result.getEmail(), is(expected.getEmail()));
        assertThat(result.getBonuspoints(), is(expected.getBonuspoints()));
        verify(customerRepository, times(1)).findById(CustomerTestHelper.MOCK_ID1);
        verifyNoMoreInteractions(customerRepository);
    }

    // getCustomerById - id not existent
    @Test
    void givenCustomersAndNonexistentId_whenFindById_thenThrowSpecificException()
    {
        // given
        when(customerRepository.findById(anyLong())).thenThrow(VideoRentalStoreApiException.class);

        // when + then
        assertThrows(VideoRentalStoreApiException.class, () -> customerService.findBy(CustomerTestHelper.MOCK_UNKNOWN_ID));
        verify(customerRepository, times(1)).findById(CustomerTestHelper.MOCK_UNKNOWN_ID);
        verifyNoMoreInteractions(customerRepository);
    }

    // getCustomerById - forbidden ??

    // insertCustomer - ok
    @Test
    void givenValidParameter_whenInsertCustomer_thenReturnCustomerInserted()
    {
        // given
        final Customer expected = CustomerTestHelper.generateCustomer(CustomerTestHelper.MOCK_USERNAME1, CustomerTestHelper.MOCK_EMAIL1, 0);
        final InsertCustomerParameter mockParameter = CustomerTestHelper.generateInsertCustomerParameter(expected.getUsername(), expected.getEmail());
        when(customerRepository.save(any(Customer.class))).thenReturn(expected);

        // when
        final Customer result = customerService.insert(mockParameter);

        // then
        assertNotNull(result);
        assertThat(result, is(expected));
        assertThat(result.getId(), is(expected.getId()));
        assertThat(result.getUsername(), is(expected.getUsername()));
        assertThat(result.getEmail(), is(expected.getEmail()));
        assertThat(result.getBonuspoints(), is(expected.getBonuspoints()));
        verify(customerRepository, times(1)).save(expected);
        verifyNoMoreInteractions(customerRepository);
    }

    // insertCustomer - already exist in db
    @Test
    void givenParameterWithExistentCustomerName_whenInsertCustomer_thenThrowSpecificException()
    {
        // given
        final Customer mockCustomer = CustomerTestHelper.generateCustomer(CustomerTestHelper.MOCK_USERNAME2, CustomerTestHelper.MOCK_EMAIL2, 0);
        final InsertCustomerParameter mockParameter =
            CustomerTestHelper.generateInsertCustomerParameter(mockCustomer.getUsername(), mockCustomer.getEmail());
        when(customerRepository.save(any(Customer.class))).thenThrow(VideoRentalStoreApiException.class);

        // when + then
        assertThrows(VideoRentalStoreApiException.class, () -> customerService.insert(mockParameter));
        verify(customerRepository, times(1)).save(mockCustomer);
        verify(customerRepository, times(0)).findById(anyLong());
        verifyNoMoreInteractions(customerRepository);
    }
}
