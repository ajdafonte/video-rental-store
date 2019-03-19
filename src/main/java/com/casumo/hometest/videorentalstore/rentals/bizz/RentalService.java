package com.casumo.hometest.videorentalstore.rentals.bizz;

import java.util.List;

import com.casumo.hometest.videorentalstore.rentals.domain.Rental;


/**
 * RentalService interface.
 */
public interface RentalService
{
    /**
     * @return
     */
    List<Rental> findAll();

    /**
     * @param parameter
     * @return
     */
    Rental insert(final InsertRentalParameter parameter);

    /**
     * @param parameter
     * @return
     */
    Rental patch(final PatchRentalParameter parameter);
}
