package com.example.carrental.repository;

import com.example.carrental.model.Car;
import com.example.carrental.model.Offer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Long> {

    Collection<Offer> getAllByCar(Car car);

    Collection<Offer> findAllByPricePerDayBetween(long priceLowLimit,long priceHighLimit);

    Collection<Offer> findAllByMinimumRentalDaysLessThanEqual(long daysNumber);
}
