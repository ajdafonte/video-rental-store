package com.casumo.hometest.videorentalstore.rentals.rest.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.casumo.hometest.videorentalstore.common.infra.MappingTool;
import com.casumo.hometest.videorentalstore.rentals.domain.Rental;
import com.casumo.hometest.videorentalstore.rentals.domain.RentalItem;
import com.casumo.hometest.videorentalstore.rentals.rest.RentalRest;


/**
 * RentalRestMapper class.
 */
public class RentalRestMapper
{
    public static RentalRest map(final Rental rental)
    {
        if (rental != null)
        {
            final RentalRest rentalItemRest = new RentalRest();
            rentalItemRest.setId(rental.getId());
            rentalItemRest.setStartDateTime(MappingTool.offsetDateTimeOrNull(rental.getStartdatetime()));

            final List<RentalItem> rentalItems = rental.getRentalItems();

            final BigDecimal totalPrice = rentalItems.stream()
                .map(RentalItem::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.valueOf(0));

            final BigDecimal totalSubcharge = rentalItems.stream()
                .map(RentalItem::getSurcharge)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.valueOf(0));

            rentalItemRest.setTotalPrice(totalPrice);
            rentalItemRest.setTotalSurcharge(totalSubcharge);
            rentalItemRest.setRentalItems(
                rentalItems.stream()
                    .map(RentalItemRestMapper::map).collect(Collectors.toList()));
            return rentalItemRest;
        }
        return null;
    }

}
