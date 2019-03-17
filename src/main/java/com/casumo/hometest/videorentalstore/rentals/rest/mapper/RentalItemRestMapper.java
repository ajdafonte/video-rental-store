package com.casumo.hometest.videorentalstore.rentals.rest.mapper;

import com.casumo.hometest.videorentalstore.common.infra.MappingTool;
import com.casumo.hometest.videorentalstore.films.rest.mapper.FilmRestMapper;
import com.casumo.hometest.videorentalstore.rentals.domain.RentalItem;
import com.casumo.hometest.videorentalstore.rentals.rest.RentalItemRest;


/**
 * RentalItemRestMapper class.
 */
public class RentalItemRestMapper
{
    public static RentalItemRest map(final RentalItem rentalItem)
    {
        if (rentalItem != null)
        {
            final RentalItemRest rentalItemRest = new RentalItemRest();
            rentalItemRest.setId(rentalItem.getId());
            rentalItemRest.setFilm(FilmRestMapper.map(rentalItem.getFilm()));
            rentalItemRest.setDaysRented(rentalItem.getDaysrented());
            rentalItemRest.setPrice(rentalItem.getPrice());
            rentalItemRest.setSubcharge(rentalItem.getSubcharge());
            rentalItemRest.setStartDateTime(MappingTool.offsetDateTimeOrNull(rentalItem.getStartdatetime()));
            rentalItemRest.setEndDateTime(MappingTool.offsetDateTimeOrNull(rentalItem.getEnddatetime()));
            return rentalItemRest;
        }
        return null;
    }
}
