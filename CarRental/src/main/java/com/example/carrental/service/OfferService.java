package com.example.carrental.service;

import com.example.carrental.model.Car;
import com.example.carrental.model.Offer;
import com.example.carrental.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class OfferService {

    @Autowired
    private OfferRepository repository;

    public Collection<Offer> getAllOffers(){
        Collection<Offer> offers = (Collection<Offer>) repository.findAll();
        return offers;
    }

    public Offer getOfferById(long id){
        Optional<Offer> offer = repository.findById(id);
        if(offer.isPresent()){
            return offer.get();
        }
        return null;
    }

    public Collection<Offer> getOffersByCar(Car car){
        Collection<Offer> offers = (Collection<Offer>) repository.getAllByCar(car);
        return offers;
    }

    public Collection<Offer> getOffersBetweenPrice(long priceLowLimit,long priceHighLimit){
        Collection<Offer> offers = (Collection<Offer>) repository.findAllByPricePerDayBetween(priceLowLimit, priceHighLimit);
        return offers;
    }

    public Collection<Offer> getOffersByRentalDays(int rentalDays){
        Collection<Offer> offers = (Collection<Offer>) repository.findAllByMinimumRentalDaysLessThanEqual(rentalDays);
        return offers;
    }

    public void saveOffer(Offer offer){
        repository.save(offer);
    }

    public void deleteOffer(Offer offer){
        repository.delete(offer);
    }
}
