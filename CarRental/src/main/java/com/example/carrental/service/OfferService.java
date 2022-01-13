package com.example.carrental.service;

import com.example.carrental.exception.OfferInvalidFilterException;
import com.example.carrental.exception.OfferNotFoundException;
import com.example.carrental.model.Offer;
import com.example.carrental.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    @Autowired
    private OfferRepository repository;

    public Offer getOfferById(long id) {
        Optional<Offer> offer = repository.getById(id);
        if (offer.isPresent()) {
            return offer.get();
        }
        throw new OfferNotFoundException();
    }

    public List<Offer> get(long priceLowLimit, long priceHighLimit, int rentalDays) {
        if (priceLowLimit < 0) {
            throw new OfferInvalidFilterException("priceLowLimit");
        }
        if (priceHighLimit < 0) {
            throw new OfferInvalidFilterException("priceHighLimit");
        }
        if (rentalDays < 0) {
            throw new OfferInvalidFilterException("rentalDays");
        }
        if (rentalDays != 0) {
            return repository.findAllByPricePerDayBetweenAndMinimumRentalDaysLessThanEqual(priceLowLimit, priceHighLimit, rentalDays);
        }
        return repository.findAllByPricePerDayBetween(priceLowLimit, priceHighLimit);
    }

    public Offer create(Offer offer) {
        return repository.save(offer);
    }
}
