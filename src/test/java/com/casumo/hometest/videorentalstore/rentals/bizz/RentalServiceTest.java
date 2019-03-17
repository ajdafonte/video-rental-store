package com.casumo.hometest.videorentalstore.rentals.bizz;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import com.casumo.hometest.videorentalstore.rentals.RentalTestHelper;
import com.casumo.hometest.videorentalstore.rentals.domain.Rental;
import com.casumo.hometest.videorentalstore.rentals.repo.RentalRepository;


/**
 * RentalServiceTest class - RentalService test class.
 */
@ExtendWith(MockitoExtension.class)
class RentalServiceTest
{
    @Mock
    private RentalRepository rentalRepository;

    private RentalService rentalService;

    @BeforeEach
    void setUp()
    {
        this.rentalService = new RentalService(rentalRepository);
    }

    // getRentals - with data
    @Test
    void givenExistentRentals_whenFindAll_thenReturnAllRentals()
    {
        // given
        final List<Rental> expected = Arrays.asList(RentalTestHelper.MOCK_RENTAL1, RentalTestHelper.MOCK_RENTAL2);
        when(rentalRepository.findAll()).thenReturn(expected);

        // when
        final List<Rental> result = rentalService.findAll();

        // then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertThat(result.size(), is(expected.size()));
        assertThat(result, containsInAnyOrder(RentalTestHelper.MOCK_RENTAL1, RentalTestHelper.MOCK_RENTAL2));
        verify(rentalRepository, times(1)).findAll();
        verifyNoMoreInteractions(rentalRepository);
    }

    // getRentals - without data
    @Test
    void givenNoRentals_whenFindAll_thenReturnEmptyCollection()
    {
        // given
        when(rentalRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        final List<Rental> result = rentalService.findAll();

        // then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(rentalRepository, times(1)).findAll();
        verifyNoMoreInteractions(rentalRepository);
    }

    // getRentals - forbidden ??
}
