package com.casumo.hometest.videorentalstore.rentals.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casumo.hometest.videorentalstore.rentals.domain.Rental;


/**
 * RentalRepository interface.
 */
public interface RentalRepository extends JpaRepository<Rental, Long>
{
}
