package com.example.carrental.service;

import com.example.carrental.exception.OfferMinimumDaysException;
import com.example.carrental.exception.OfferNotAvailableException;
import com.example.carrental.model.Offer;
import com.example.carrental.model.Rental;
import com.example.carrental.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class RentalService {

    @Autowired
    RentalRepository repository;

    public List<Rental> getAllActiveRentals(Date date) {
        if (date == null) {
            date = new Date();
        }

        List<Rental> rentals = repository.getAllActiveRentals(date);
        return rentals;
    }

    public List<Rental> getAllRentals() {
        List<Rental> rentals = repository.findAll();
        return rentals;
    }

    public Rental create(Rental rental) {

        Offer offer = rental.getOffer();
        List<Long> activeOffersIds = getAllActiveOffersIds(rental.getStartDate(), rental.getEndDate());
        Optional<Long> activeOfferId = activeOffersIds.stream().filter(o -> offer.getId() == o).findFirst();

        if (activeOfferId.isPresent()) {
            throw new OfferNotAvailableException();
        }

        int daysNumber = calculateDaysNumber(rental.getStartDate(), rental.getEndDate());

        if (offer.getMinimumRentalDays() > daysNumber) {
            throw new OfferMinimumDaysException();
        }

        if (rental.isExtraInsurance()) {
            rental.setTotalPrice(calculateTotalPrice(daysNumber, offer.getPricePerDay(), offer.getExtraInsuranceCost()));
        } else {
            rental.setTotalPrice(calculateTotalPrice(daysNumber, offer.getPricePerDay()));
        }

        return repository.save(rental);
    }

    private long calculateTotalPrice(int daysNumber, long pricePerDay, long extraInsurancePrice) {
        return (calculateTotalPrice(daysNumber, pricePerDay) + extraInsurancePrice);
    }

    private long calculateTotalPrice(int daysNumber, long pricePerDay) {
        return daysNumber * pricePerDay;
    }

    private int calculateDaysNumber(Date startDate, Date endDate) {
        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
        long daysNumber = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        return (int) daysNumber;
    }

    private List<Long> getAllActiveOffersIds(Date startdate, Date endDate) {
        List<Long> offersIds = repository.getAllActiveOffersIds(startdate, endDate);
        return offersIds;
    }


}
