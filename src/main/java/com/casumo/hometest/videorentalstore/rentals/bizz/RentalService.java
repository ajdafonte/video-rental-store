package com.casumo.hometest.videorentalstore.rentals.bizz;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casumo.hometest.videorentalstore.common.error.VideoRentalStoreApiError;
import com.casumo.hometest.videorentalstore.common.error.VideoRentalStoreApiException;
import com.casumo.hometest.videorentalstore.common.infra.MappingTool;
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
        final Customer customer = customerRepository.findById(parameter.getCustomerId())
            .orElseThrow(() -> new VideoRentalStoreApiException(VideoRentalStoreApiError.UNKNOWN_RESOURCE,
                "Customer was not found."));

        final Rental rental = generateRentalEntity(parameter, customer);
        final Rental savedRental = rentalRepository.save(rental);

        updateCustomerBonusPoints(customer, savedRental);

        return savedRental;
    }

    public Rental patch(final PatchRentalParameter parameter)
    {
        final long rentalId = parameter.getRentalId();
        final List<Long> itemsToPatch = parameter.getRentalItemsToReturn();

        final Rental rental =
            rentalRepository.findById(rentalId).orElseThrow(() -> new VideoRentalStoreApiException(VideoRentalStoreApiError.UNKNOWN_RESOURCE,
                "Rental was not found."));

        // handle items to patch
        final List<RentalItem> rentalItems = rental.getRentalItems();
        for (final Long itemIdToPatch : itemsToPatch)
        {
            // check if all rentalItems exists in the rental
            final RentalItem rentalItem = rentalItems.stream()
                .filter(item -> itemIdToPatch.equals(item.getId()))
                .findFirst().orElseThrow(() -> new VideoRentalStoreApiException(VideoRentalStoreApiError.UNKNOWN_RESOURCE,
                    "Rental Item with id " + itemIdToPatch + " does not exist in this Rental."));

            // check if rental item was already returned
            if (rentalItem.getEnddatetime() != null)
            {
                throw new VideoRentalStoreApiException(VideoRentalStoreApiError.RENTAL_ITEM_ALREADY_RETURNED,
                    "Rental Item: " + rentalItem.getId());
            }

            // set surcharge and end date time
            final OffsetDateTime endDateTime = OffsetDateTime.now();
            rentalItem.setSurcharge(retrieveRentalSurcharge(rentalItem, endDateTime));
            // remark: think about the capability that is possible to specify when to end the rental of the item
            rentalItem.setEnddatetime(MappingTool.millisOrNull(endDateTime));
        }

        return rentalRepository.save(rental);
    }

    private void updateCustomerBonusPoints(final Customer customer, final Rental savedRental)
    {
        final long newBonusPoints = retrieveBonusPoints(savedRental.getRentalItems());
        customer.setBonuspoints(customer.getBonuspoints() + newBonusPoints);
        customerRepository.save(customer);
    }

    private Rental generateRentalEntity(final InsertRentalParameter parameter, final Customer customer)
    {
        final OffsetDateTime currentDateTime = OffsetDateTime.now();

        // generate and validate rental items
        final List<RentalItem> itemsToInsert =
            parameter.getItemsToRent().stream()
                .map(itemToInsert -> generateRentalItemEntity(itemToInsert, currentDateTime))
                .collect(Collectors.toList());

        final Rental rental = new Rental();
        rental.setCustomer(customer);
        rental.setStartdatetime(MappingTool.millisOrNull(currentDateTime));
        rental.setRentalItems(itemsToInsert);
        return rental;
    }

    private RentalItem generateRentalItemEntity(final InsertRentalItemParameter parameter, final OffsetDateTime currentDateTime)
    {
        final long filmId = parameter.getFilmId();
        final int numDaysToRent = parameter.getDaysToRent();

        // get and validate film
        final Film film = filmRepository.findById(filmId).orElseThrow(() -> new VideoRentalStoreApiException(
            VideoRentalStoreApiError.UNKNOWN_RESOURCE,
            "Film with id " + filmId + " does not exist."));

        final RentalItem rentalItem = new RentalItem();
        rentalItem.setFilm(film);
        rentalItem.setDaysrented(numDaysToRent);
        rentalItem.setStartdatetime(MappingTool.millisOrNull(currentDateTime));
        rentalItem.setPrice(retrieveRentalPrice(film, numDaysToRent));
        rentalItem.setSurcharge(BigDecimal.valueOf(0));

        return rentalItem;
    }

    private BigDecimal retrieveRentalPrice(final Film film, final int numDaysToRent)
    {
        final RentalCalculator rentalCalculator = RentalCalculatorFactory.getRentalCalculator(film.getType());
        return rentalCalculator.calculatePrice(film, numDaysToRent);
    }

    private BigDecimal retrieveRentalSurcharge(final RentalItem rentalItem, final OffsetDateTime endDateTime)
    {
        BigDecimal surcharge = BigDecimal.valueOf(0);
        final Film film = rentalItem.getFilm();
        final RentalCalculator rentalCalculator = RentalCalculatorFactory.getRentalCalculator(film.getType());
        final long extraNumDays = rentalCalculator.calculateNumberOfExtraDays(rentalItem, endDateTime);
        if (extraNumDays > 0)
        {
            surcharge = rentalCalculator.calculateSurcharge(film, (int) extraNumDays);
        }
        return surcharge;
    }

    private int retrieveBonusPoints(final List<RentalItem> rentalItems)
    {
        int total = 0;
        for (final RentalItem rentalItem : rentalItems)
        {
            final Film film = rentalItem.getFilm();
            final RentalCalculator rentalCalculator = RentalCalculatorFactory.getRentalCalculator(film.getType());
            total += rentalCalculator.calculateBonusPoints();
        }
        return total;
    }

}
