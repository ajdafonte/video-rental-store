package com.casumo.hometest.videorentalstore.rentals.bizz;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
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

    public Rental patch(final PatchRentalParameter parameter)
    {
        final long rentalId = parameter.getRentalId();
        final List<Long> itemsToPatch = parameter.getRentalItemsToReturn();

        // find rental to patch
        final Rental rental =
            rentalRepository.findById(rentalId).orElseThrow(() -> new VideoRentalStoreApiException(VideoRentalStoreApiError.UNKNOWN_RESOURCE,
                "Rental was not found."));

        // handle items to patch
        final List<RentalItem> rentalItems = rental.getRentalItems();
        for (final Long itemToPatch : itemsToPatch)
        {
            // check if all rentalItems exists in the rental
            final RentalItem rentalItem = rentalItems.stream()
                .filter(item -> item.getId() == itemToPatch)
                .findFirst().orElseThrow(() -> new VideoRentalStoreApiException(VideoRentalStoreApiError.UNKNOWN_RESOURCE,
                    "Rental Item was not found."));

            // check if rental item was already returned
            if (rentalItem.getEnddatetime() != null)
            {
                throw new VideoRentalStoreApiException(VideoRentalStoreApiError.INVALID_REQUEST,
                    "Rental Item was already returned.");
            }

            // calculate subcharge
            final OffsetDateTime startRentalTime = MappingTool.offsetDateTimeOrNull(rentalItem.getStartdatetime());
            final OffsetDateTime limitDeliverRentalTime = startRentalTime.plus(rentalItem.getDaysrented(), ChronoUnit.DAYS);
            final OffsetDateTime currentTime = OffsetDateTime.now();
            final long numDays = ChronoUnit.DAYS.between(limitDeliverRentalTime, currentTime);
            if (numDays > 0)
            {
                rentalItem.setSubcharge(calculateRentalSubcharge(rentalItem.getFilm(), Long.valueOf(numDays).intValue()));
            }

            // set enddatetime for rental
            rentalItem.setEnddatetime(MappingTool.millisOrNull(currentTime));
        }
        return rentalRepository.save(rental);
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

    private int calculateNumExtraDays()
    {
        // TODO
        return 0;
    }

    private BigDecimal calculateRentalSubcharge(final Film film, final int extraNumDays)
    {
        final RentalCalculator rentalCalculator = RentalCalculatorFactory.getRentalCalculator(film.getType());
        return rentalCalculator.rentalSubcharge(film, extraNumDays);
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
