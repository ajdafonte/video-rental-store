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
    void givenCustomersInInventory_whenFindAll_thenReturnAllCustomers()
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
    void givenNoCustomersInInventory_whenFindAll_thenReturnEmptyCollection()
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
        when(customerRepository.findBy(anyLong())).thenReturn(expected);

        // when
        final Customer result = customerService.findBy(CustomerTestHelper.MOCK_ID1);

        // then
        assertNotNull(result);
        assertEquals(expected, result);
        assertThat(result.getId(), is(expected.getId()));
        assertThat(result.getUsername(), is(expected.getUsername()));
        assertThat(result.getEmail(), is(expected.getEmail()));
        assertThat(result.getBonuspoints(), is(expected.getBonuspoints()));
        verify(customerRepository, times(1)).findBy(CustomerTestHelper.MOCK_ID1);
        verifyNoMoreInteractions(customerRepository);
    }

    // getCustomerById - id not existent
    @Test
    void givenCustomersAndNonexistentId_whenFindById_thenThrowSpecificException()
    {
        // given
        when(customerRepository.findBy(anyLong())).thenThrow(VideoRentalStoreApiException.class);

        // when + then
        assertThrows(VideoRentalStoreApiException.class, () -> customerService.findBy(CustomerTestHelper.MOCK_UNKNOWN_ID));
        verify(customerRepository, times(1)).findBy(CustomerTestHelper.MOCK_UNKNOWN_ID);
        verifyNoMoreInteractions(customerRepository);
    }

    // getCustomerById - forbidden ??

    // insertCustomer - ok
    @Test
    void givenValidCustomer_whenInsertCustomer_thenReturnCustomerInserted()
    {
        // given
        final Customer expected = CustomerTestHelper.MOCK_CUSTOMER1;
        when(customerRepository.save(any(Customer.class))).thenReturn(expected.getId());
        when(customerRepository.findBy(anyLong())).thenReturn(expected);

        // when
        final Customer result = customerService.insert(expected);

        // then
        assertNotNull(result);
        assertThat(result, is(expected));
        assertThat(result.getId(), is(expected.getId()));
        assertThat(result.getUsername(), is(expected.getUsername()));
        assertThat(result.getEmail(), is(expected.getEmail()));
        assertThat(result.getBonuspoints(), is(expected.getBonuspoints()));
        verify(customerRepository, times(1)).save(expected);
        verify(customerRepository, times(1)).findBy(expected.getId());
        verifyNoMoreInteractions(customerRepository);
    }

    // insertCustomer - already exist in db
    @Test
    void givenExistentCustomerName_whenInsertCustomer_thenThrowSpecificException()
    {
        // given
        final Customer mockCustomer = CustomerTestHelper.MOCK_CUSTOMER2;
        when(customerRepository.save(any(Customer.class))).thenThrow(VideoRentalStoreApiException.class);

        // when + then
        assertThrows(VideoRentalStoreApiException.class, () -> customerService.insert(mockCustomer));
        verify(customerRepository, times(1)).save(mockCustomer);
        verify(customerRepository, times(0)).findBy(anyLong());
        verifyNoMoreInteractions(customerRepository);
    }
}
