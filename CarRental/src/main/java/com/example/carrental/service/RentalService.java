package com.example.carrental.service;

import com.example.carrental.model.Offer;
import com.example.carrental.model.Rental;
import com.example.carrental.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class RentalService {

    @Autowired
    RentalRepository repository;

    public Collection<Rental> getAllRentals(){
        Collection<Rental> rentals = (Collection<Rental>) repository.findAll();
        return rentals;
    }

    public Collection<Rental> getAllActiveRentals(Date date){
        Collection<Rental> rentals = (Collection<Rental>) repository.getAllActiveRentals(date);
        return rentals;
    }

    public Collection<Rental> getAllActiveRentals(){
        Collection<Rental> rentals = (Collection<Rental>) repository.getAllActiveRentals(new Date());
        return rentals;
    }

    public Collection<Offer> getAllAvailableOffers(Date date){
        Collection<Offer> offers = (Collection<Offer>) repository.getAllAvailableOffers(date);
        return offers;
    }

    public Collection<Offer> getAllAvailableOffers(){
        Collection<Offer> offers = (Collection<Offer>) repository.getAllAvailableOffers(new Date());
        return offers;
    }

    public Rental getRentalById(long id){
        Optional<Rental> rental = repository.findById(id);
        if(rental.isPresent()){
            return rental.get();
        }
        return null;
    }

    public void saveRental(Rental rental){
        repository.save(rental);
    }

    public void deleteRental(Rental rental){
        repository.delete(rental);
    }

}
