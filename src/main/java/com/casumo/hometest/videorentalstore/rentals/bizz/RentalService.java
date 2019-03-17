package com.casumo.hometest.videorentalstore.rentals.bizz;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casumo.hometest.videorentalstore.common.error.VideoRentalStoreApiError;
import com.casumo.hometest.videorentalstore.common.error.VideoRentalStoreApiException;
import com.casumo.hometest.videorentalstore.customers.domain.Customer;
import com.casumo.hometest.videorentalstore.customers.repo.CustomerRepository;
import com.casumo.hometest.videorentalstore.films.domain.Film;
import com.casumo.hometest.videorentalstore.films.repo.FilmRepository;
import com.casumo.hometest.videorentalstore.rentals.domain.Rental;
import com.casumo.hometest.videorentalstore.rentals.domain.RentalItem;
import com.casumo.hometest.videorentalstore.rentals.repo.RentalRepository;


/**
 * RentalService class.
 */
@Service
public class RentalService
{
    private final RentalRepository rentalRepository;

    private final CustomerRepository customerRepository;

    private final FilmRepository filmRepository;

    @Autowired
    public RentalService(final RentalRepository rentalRepository,
                         final CustomerRepository customerRepository,
                         final FilmRepository filmRepository)
    {
        this.rentalRepository = rentalRepository;
        this.customerRepository = customerRepository;
        this.filmRepository = filmRepository;
    }

    public List<Rental> findAll()
    {
        return rentalRepository.findAll();
    }

    public Rental insert(final InsertRentalParameter parameter)
    {
        // find customer
        final Customer customer = customerRepository.findById(parameter.getCustomerId())
            .orElseThrow(() -> new VideoRentalStoreApiException(VideoRentalStoreApiError.UNKNOWN_RESOURCE,
                "Customer was not found."));

        // generate and save rental
        final Rental rental = generateRentalEntity(parameter, customer);
        final Rental savedRental = rentalRepository.save(rental);

        // update costumer bonus points
        updateCustomerBonusPoints(customer, savedRental);

        return savedRental;
    }

    private void updateCustomerBonusPoints(final Customer customer, final Rental savedRental)
    {
        final long newBonusPoints = calculateBonusPoints(savedRental.getRentalItems());
        customer.setBonuspoints(customer.getBonuspoints() + newBonusPoints);
        customerRepository.save(customer);
    }

    private Rental generateRentalEntity(final InsertRentalParameter parameter, final Customer customer)
    {
        // set current instant
        final Long currentTime = OffsetDateTime.now().toEpochSecond();

        // generate and validate rental items
        final List<RentalItem> itemsToInsert =
            parameter.getItemsToRent().stream()
                .map(parameter1 -> generateRentalItem(parameter1, currentTime))
                .collect(Collectors.toList());

        // create entity
        final Rental rental = new Rental();
        rental.setCustomer(customer);
        rental.setStartdatetime(currentTime);
        rental.setRentalItems(itemsToInsert);
        return rental;
    }

    private RentalItem generateRentalItem(final InsertRentalItemParameter parameter, final Long currentTime)
    {
        final long filmId = parameter.getFilmId();
        final int numDaysToRent = parameter.getDaysToRent();

        // get and validate film
        final Film film = filmRepository.findById(filmId).orElseThrow(() -> new VideoRentalStoreApiException(
            VideoRentalStoreApiError.UNKNOWN_RESOURCE,
            "One of the items to rent are not available"));

        // create entity
        final RentalItem rentalItem = new RentalItem();
        rentalItem.setFilm(film);
        rentalItem.setDaysrented(numDaysToRent);
        rentalItem.setStartdatetime(currentTime);
        rentalItem.setPrice(calculateRentalPrice(film, numDaysToRent));
        rentalItem.setSubcharge(BigDecimal.valueOf(0));

        return rentalItem;
    }

    private BigDecimal calculateRentalPrice(final Film film, final int numDaysToRent)
    {
        final RentalCalculator rentalCalculator = RentalCalculatorFactory.getRentalCalculator(film.getType());
        return rentalCalculator.rentalPrice(film, numDaysToRent);
    }

    private int calculateBonusPoints(final List<RentalItem> rentalItems)
    {
        int total = 0;
        for (final RentalItem rentalItem : rentalItems)
        {
            final Film film = rentalItem.getFilm();
            final RentalCalculator rentalCalculator = RentalCalculatorFactory.getRentalCalculator(film.getType());
            total += rentalCalculator.rentalBonusPoints(film);
        }
        return total;
    }
}
