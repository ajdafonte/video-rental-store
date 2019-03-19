package com.casumo.hometest.videorentalstore.rentals.rest.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.casumo.hometest.videorentalstore.common.infra.MappingTool;
import com.casumo.hometest.videorentalstore.customers.CustomerTestHelper;
import com.casumo.hometest.videorentalstore.rentals.RentalTestHelper;
import com.casumo.hometest.videorentalstore.rentals.domain.Rental;
import com.casumo.hometest.videorentalstore.rentals.domain.RentalItem;
import com.casumo.hometest.videorentalstore.rentals.rest.RentalItemRest;
import com.casumo.hometest.videorentalstore.rentals.rest.RentalRest;


/**
 * RentalRestMapperTest class - RentalRestMapper test class.
 */
class RentalRestMapperTest
{
    // map - ok
    @Test
    void givenValidRental_whenMapToRest_thenReturnRentalRestObject()
    {
        // given
        final Rental mockRental = RentalTestHelper.generateRental(RentalTestHelper.MOCK_ID1,
            CustomerTestHelper.MOCK_CUSTOMER1,
            RentalTestHelper.MOCK_START_DATETIME1,
            Arrays.asList(RentalTestHelper.getMockRentalItem1(), RentalTestHelper.getMockRentalItem2()));
        final List<RentalItem> mockRentalItems = mockRental.getRentalItems();
        final List<RentalItemRest> expectedRentalItems = mockRentalItems.stream()
            .map(RentalItemRestMapper::map)
            .collect(Collectors.toList());

        // when
        final RentalRest result = RentalRestMapper.map(mockRental);

        // then
        assertNotNull(result);
        assertThat(result.getId(), is(mockRental.getId()));
        assertThat(result.getStartDateTime(), is(MappingTool.offsetDateTimeOrNull(mockRental.getStartdatetime())));
        assertThat(result.getTotalPrice(), is(calculateTotalPrice(mockRentalItems)));
        assertThat(result.getTotalSurcharge(), is(calculateTotalSubcharge(mockRentalItems)));
        assertThat(result.getRentalItems(), is(expectedRentalItems));
    }

    // map - null
    @Test
    void givenNullRental_whenMapToRest_thenReturnNullValue()
    {
        // given + when + then
        assertNull(RentalRestMapper.map(null));
    }

    private static BigDecimal calculateTotalPrice(final List<RentalItem> items)
    {
        return items.stream().map(RentalItem::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.valueOf(0));
    }

    private static BigDecimal calculateTotalSubcharge(final List<RentalItem> items)
    {
        return items.stream().map(RentalItem::getSurcharge).reduce(BigDecimal::add).orElse(BigDecimal.valueOf(0));
    }

}
