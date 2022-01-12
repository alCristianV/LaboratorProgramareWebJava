package com.example.carrental.service;

import com.example.carrental.model.Offer;
import com.example.carrental.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    @Autowired
    private OfferRepository repository;

    public Offer getOfferById(long id) {
        return new Offer();
    }

    public List<Offer> getAvailableOffers(long priceLowLimit, long priceHighLimit, int rentalDays) {
        //Collection<Offer> offers = (Collection<Offer>) repository.findAllByPricePerDayBetween(priceLowLimit, priceHighLimit);
        List<Offer> offers = repository.findAllByMinimumRentalDaysLessThanEqual(rentalDays);
        return offers;
    }


    public void saveOffer(Offer offer) {
        repository.save(offer);
    }

    public void deleteOffer(Offer offer) {
        repository.delete(offer);
    }
}
