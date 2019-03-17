package com.casumo.hometest.videorentalstore.rentals.bizz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casumo.hometest.videorentalstore.rentals.domain.Rental;
import com.casumo.hometest.videorentalstore.rentals.repo.RentalRepository;


/**
 * RentalService class.
 */
@Service
public class RentalService
{
    private final RentalRepository rentalRepository;

    @Autowired
    public RentalService(final RentalRepository rentalRepository)
    {
        this.rentalRepository = rentalRepository;
    }

    public List<Rental> findAll()
    {
        return rentalRepository.findAll();
    }

    public Rental insert(final InsertRentalParameter map)
    {
        return null;
    }
}
