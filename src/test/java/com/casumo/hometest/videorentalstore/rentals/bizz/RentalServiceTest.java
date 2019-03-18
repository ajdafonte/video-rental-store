package com.casumo.hometest.videorentalstore.rentals.bizz;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.casumo.hometest.videorentalstore.common.error.VideoRentalStoreApiError;
import com.casumo.hometest.videorentalstore.common.error.VideoRentalStoreApiException;
import com.casumo.hometest.videorentalstore.customers.CustomerTestHelper;
import com.casumo.hometest.videorentalstore.customers.domain.Customer;
import com.casumo.hometest.videorentalstore.customers.repo.CustomerRepository;
import com.casumo.hometest.videorentalstore.films.FilmTestHelper;
import com.casumo.hometest.videorentalstore.films.domain.Film;
import com.casumo.hometest.videorentalstore.films.repo.FilmRepository;
import com.casumo.hometest.videorentalstore.rentals.RentalTestHelper;
import com.casumo.hometest.videorentalstore.rentals.domain.Rental;
import com.casumo.hometest.videorentalstore.rentals.domain.RentalItem;
import com.casumo.hometest.videorentalstore.rentals.repo.RentalRepository;


/**
 * RentalServiceTest class - RentalService test class.
 */
@ExtendWith(MockitoExtension.class)
class RentalServiceTest
{
    @Mock
    private RentalRepository rentalRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private FilmRepository filmRepository;

    private RentalService rentalService;

    @BeforeEach
    void setUp()
    {
        this.rentalService = new RentalService(rentalRepository, customerRepository, filmRepository);
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

    // insertRental - ok
    @Test
    void givenValidParameter_whenInsertRental_thenReturnRentalInserted()
    {
        // given
        final Customer mockCustomer = CustomerTestHelper.MOCK_CUSTOMER1;
        final Film mockFilm1 = RentalTestHelper.MOCK_NEW_RENTAL_ITEM1.getFilm();
        final Film mockFilm2 = RentalTestHelper.MOCK_NEW_RENTAL_ITEM2.getFilm();
        final Film mockFilm3 = RentalTestHelper.MOCK_NEW_RENTAL_ITEM3.getFilm();
        final InsertRentalItemParameter rentalItemParameter1 =
            RentalTestHelper.generateInsertRentalItemParameter(mockFilm1.getId(), RentalTestHelper.MOCK_NEW_RENTAL_ITEM1.getDaysrented());
        final InsertRentalItemParameter rentalItemParameter2 =
            RentalTestHelper.generateInsertRentalItemParameter(mockFilm2.getId(), RentalTestHelper.MOCK_NEW_RENTAL_ITEM2.getDaysrented());
        final InsertRentalItemParameter rentalItemParameter3 =
            RentalTestHelper.generateInsertRentalItemParameter(mockFilm3.getId(), RentalTestHelper.MOCK_NEW_RENTAL_ITEM3.getDaysrented());
        final InsertRentalParameter mockParameter = RentalTestHelper.generateInsertRentalParameter(mockCustomer.getId(),
            Arrays.asList(rentalItemParameter1, rentalItemParameter2, rentalItemParameter3));

        final Rental expectedRental = RentalTestHelper.MOCK_NEW_RENTAL1; // see RentalTestHelper for details
        final long expectedBonusPoints = mockCustomer.getBonuspoints() + 4; // 2 for new release + 2 for old film & regular
        final BigDecimal expectedPrice1 = BigDecimal.valueOf(90);
        final BigDecimal expectedPrice2 = BigDecimal.valueOf(30);
        final BigDecimal expectedPrice3 = BigDecimal.valueOf(80);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(mockCustomer));
        when(rentalRepository.save(any(Rental.class))).thenReturn(expectedRental);
        when(customerRepository.save(any(Customer.class))).thenReturn(expectedRental.getCustomer());
        doReturn(Optional.of(mockFilm1)).when(filmRepository).findById(mockFilm1.getId());
        doReturn(Optional.of(mockFilm2)).when(filmRepository).findById(mockFilm2.getId());
        doReturn(Optional.of(mockFilm3)).when(filmRepository).findById(mockFilm3.getId());

        // when
        final Rental result = rentalService.insert(mockParameter);

        // then
        assertNotNull(result);
        assertThat(result, is(expectedRental));
        assertThat(result.getId(), is(expectedRental.getId()));
        assertThat(result.getCustomer(), is(expectedRental.getCustomer()));
        assertThat(result.getCustomer().getBonuspoints(), is(expectedBonusPoints));
        assertThat(result.getRentalItems(), is(expectedRental.getRentalItems()));
        assertThat(result.getRentalItems(), containsInAnyOrder(
            hasProperty("price", is(expectedPrice1)),
            hasProperty("price", is(expectedPrice2)),
            hasProperty("price", is(expectedPrice3))
        ));

        verify(customerRepository, times(1)).findById(mockParameter.getCustomerId());
        verify(rentalRepository, times(1)).save(any(Rental.class));
        verifyNoMoreInteractions(rentalRepository);
        verify(customerRepository, times(1)).save(any(Customer.class));
        verifyNoMoreInteractions(customerRepository);
        verify(filmRepository, times(3)).findById(anyLong());
        verifyNoMoreInteractions(filmRepository);
    }

    // insertRental - nok (customer not found)
    @Test
    void givenParameterWithInvalidCustomer_whenInsertRental_thenThrowSpecificException()
    {
        // things to check
        // given
        final long mockCustomerId = CustomerTestHelper.MOCK_UNKNOWN_ID;
        final Film mockFilm1 = RentalTestHelper.MOCK_NEW_RENTAL_ITEM1.getFilm();
        final InsertRentalItemParameter rentalItemParameter1 =
            RentalTestHelper.generateInsertRentalItemParameter(mockFilm1.getId(), RentalTestHelper.MOCK_NEW_RENTAL_ITEM1.getDaysrented());
        final List<InsertRentalItemParameter> mockItemsToRent = Collections.singletonList(rentalItemParameter1);
        final InsertRentalParameter mockParameter = RentalTestHelper.generateInsertRentalParameter(mockCustomerId, mockItemsToRent);

        when(customerRepository.findById(mockCustomerId)).thenReturn(Optional.empty());

        // when + then
        final VideoRentalStoreApiException ex = assertThrows(VideoRentalStoreApiException.class, () -> rentalService.insert(mockParameter));
        assertThat(ex.getError(), is(VideoRentalStoreApiError.UNKNOWN_RESOURCE));
        verify(customerRepository, times(1)).findById(mockCustomerId);
        verifyNoMoreInteractions(customerRepository);
        verifyZeroInteractions(filmRepository);
        verifyZeroInteractions(rentalRepository);
    }

    // insertRental - nok (film to rent not found)
    @Test
    void givenParameterWithInvalidFilm_whenInsertRental_thenThrowSpecificException()
    {
        // given
        final Customer mockCustomer = CustomerTestHelper.MOCK_CUSTOMER1;
        final long unknownFilmId = FilmTestHelper.MOCK_UNKNOWN_ID;
        final Film mockFilm1 = RentalTestHelper.MOCK_NEW_RENTAL_ITEM1.getFilm();
        final InsertRentalItemParameter rentalItemParameter1 =
            RentalTestHelper.generateInsertRentalItemParameter(mockFilm1.getId(), RentalTestHelper.MOCK_NEW_RENTAL_ITEM1.getDaysrented());
        final InsertRentalItemParameter rentalItemParameter2 =
            RentalTestHelper.generateInsertRentalItemParameter(unknownFilmId,
                RentalTestHelper.MOCK_NEW_RENTAL_ITEM2.getDaysrented());
        final List<InsertRentalItemParameter> mockItemsToRent = Arrays.asList(rentalItemParameter1, rentalItemParameter2);
        final InsertRentalParameter mockParameter = RentalTestHelper.generateInsertRentalParameter(mockCustomer.getId(), mockItemsToRent);

        when(customerRepository.findById(mockCustomer.getId())).thenReturn(Optional.of(mockCustomer));
        doReturn(Optional.of(mockFilm1)).when(filmRepository).findById(mockFilm1.getId());
        doReturn(Optional.empty()).when(filmRepository).findById(unknownFilmId);

        // when + then
        final VideoRentalStoreApiException ex = assertThrows(VideoRentalStoreApiException.class, () -> rentalService.insert(mockParameter));
        assertThat(ex.getError(), is(VideoRentalStoreApiError.UNKNOWN_RESOURCE));
        verify(customerRepository, times(1)).findById(mockCustomer.getId());
        verifyNoMoreInteractions(customerRepository);
        verify(filmRepository, times(2)).findById(anyLong());
        verifyNoMoreInteractions(filmRepository);
        verifyZeroInteractions(rentalRepository);
    }

    // patchRental - ok
    @Test
    void givenValidParameter_whenPatchRental_thenReturnRentalPatched()
    {
        // given
        final Rental currentRental = RentalTestHelper.MOCK_NEW_RENTAL1;
        final RentalItem mockRentalItem1 = RentalTestHelper.MOCK_PATCHED_RENTAL_ITEM1;
        final RentalItem mockRentalItem3 = RentalTestHelper.MOCK_PATCHED_RENTAL_ITEM3;
        final List<Long> rentalItemsToReturn = Arrays.asList(mockRentalItem1.getId(), mockRentalItem3.getId());
        final PatchRentalParameter mockParameter = RentalTestHelper.generatePatchRentalParameter(currentRental.getId(), rentalItemsToReturn);

        final Rental expectedRental = RentalTestHelper.MOCK_PATCHED_RENTAL1; // see RentalTestHelper for details
        final BigDecimal expectedSubcharge = BigDecimal.valueOf(40); // new release delivered 1 day later

        when(rentalRepository.findById(currentRental.getId())).thenReturn(Optional.of(currentRental));
        when(rentalRepository.save(any(Rental.class))).thenReturn(expectedRental);

        // when
        final Rental result = rentalService.patch(mockParameter);

        // then
        assertNotNull(result);
        assertThat(result, is(expectedRental));
        assertThat(result.getId(), is(expectedRental.getId()));
        assertThat(result.getCustomer(), is(expectedRental.getCustomer()));
        assertThat(result.getRentalItems(), is(expectedRental.getRentalItems()));
        assertThat(result.getRentalItems(), containsInAnyOrder(
            hasProperty("subcharge", is(expectedSubcharge)),
            hasProperty("subcharge", is(BigDecimal.valueOf(0))),
            hasProperty("subcharge", is(BigDecimal.valueOf(0)))
        ));

        verify(rentalRepository, times(1)).save(any(Rental.class));
        verify(rentalRepository, times(1)).findById(currentRental.getId());
        verifyNoMoreInteractions(rentalRepository);
    }

    // patchRental - rental id not found
    @Test
    void givenValidParameterWithUnknownRentalId_whenPatchRental_thenThrowSpecificException()
    {
        // given
        final long unknownRentalId = RentalTestHelper.MOCK_UNKNOWN_ID;
        final RentalItem mockRentalItem1 = RentalTestHelper.MOCK_PATCHED_RENTAL_ITEM1;
        final RentalItem mockRentalItem3 = RentalTestHelper.MOCK_PATCHED_RENTAL_ITEM3;
        final List<Long> rentalItemsToReturn = Arrays.asList(mockRentalItem1.getId(), mockRentalItem3.getId());
        final PatchRentalParameter mockParameter = RentalTestHelper.generatePatchRentalParameter(unknownRentalId, rentalItemsToReturn);

        when(rentalRepository.findById(unknownRentalId)).thenReturn(Optional.empty());

        // when + then
        final VideoRentalStoreApiException ex = assertThrows(VideoRentalStoreApiException.class, () -> rentalService.patch(mockParameter));
        assertThat(ex.getError(), is(VideoRentalStoreApiError.UNKNOWN_RESOURCE));
        verify(rentalRepository, times(1)).findById(unknownRentalId);
        verifyNoMoreInteractions(rentalRepository);
    }

    // patchRental - rental item id to return not found in rental
    @Test
    void givenValidParameterWithUnknownRentalItemId_whenPatchRental_thenThrowSpecificException()
    {
        // given
        final RentalItem currentRentalItem1 =
            RentalTestHelper.generateRentalItem(RentalTestHelper.MOCK_ID1,
                FilmTestHelper.MOCK_REGULAR_FILM,
                RentalTestHelper.MOCK_DAYS_RENTED1,
                BigDecimal.valueOf(90),
                BigDecimal.valueOf(0),
                RentalTestHelper.MOCK_START_DATETIME1,
                null);
        final RentalItem currentRentalItem2 =
            RentalTestHelper.generateRentalItem(RentalTestHelper.MOCK_ID2,
                FilmTestHelper.MOCK_OLD_FILM,
                RentalTestHelper.MOCK_DAYS_RENTED2,
                BigDecimal.valueOf(30),
                BigDecimal.valueOf(0),
                RentalTestHelper.MOCK_START_DATETIME1,
                null);
        final Rental currentRental = RentalTestHelper.generateRental(RentalTestHelper.MOCK_ID1,
            CustomerTestHelper.MOCK_CUSTOMER1,
            RentalTestHelper.MOCK_START_DATETIME1,
            Arrays.asList(currentRentalItem1, currentRentalItem2));

        final RentalItem mockRentalItem1 = RentalTestHelper.MOCK_PATCHED_RENTAL_ITEM1;
        final List<Long> rentalItemsToReturn = Arrays.asList(mockRentalItem1.getId(), RentalTestHelper.MOCK_UNKNOWN_ID);
        final PatchRentalParameter mockParameter = RentalTestHelper.generatePatchRentalParameter(currentRental.getId(), rentalItemsToReturn);

        when(rentalRepository.findById(currentRental.getId())).thenReturn(Optional.of(currentRental));

        // when + then
        final VideoRentalStoreApiException ex = assertThrows(VideoRentalStoreApiException.class, () -> rentalService.patch(mockParameter));
        assertThat(ex.getError(), is(VideoRentalStoreApiError.UNKNOWN_RESOURCE));
        verify(rentalRepository, times(1)).findById(currentRental.getId());
        verifyNoMoreInteractions(rentalRepository);
    }

    // patchRental - rental item was already returned
    @Test
    void givenValidParameterWithRentalItemAlreadyReturned_whenPatchRental_thenThrowSpecificException()
    {
        // given
        final Rental currentRental = RentalTestHelper.MOCK_RENTAL_WITH_ITEM_RETURNED;
        final RentalItem mockRentalItem1 = RentalTestHelper.MOCK_NEW_RENTAL_ITEM1;
        final RentalItem mockRentalItem2 = RentalTestHelper.MOCK_RETURNED_RENTAL_ITEM2;
        final List<Long> rentalItemsToReturn = Arrays.asList(mockRentalItem1.getId(), mockRentalItem2.getId());
        final PatchRentalParameter mockParameter = RentalTestHelper.generatePatchRentalParameter(currentRental.getId(), rentalItemsToReturn);

        when(rentalRepository.findById(currentRental.getId())).thenReturn(Optional.of(currentRental));

        // when + then
        final VideoRentalStoreApiException ex = assertThrows(VideoRentalStoreApiException.class, () -> rentalService.patch(mockParameter));
        assertThat(ex.getError(), is(VideoRentalStoreApiError.INVALID_REQUEST));
        verify(rentalRepository, times(1)).findById(currentRental.getId());
        verifyNoMoreInteractions(rentalRepository);
    }
}
