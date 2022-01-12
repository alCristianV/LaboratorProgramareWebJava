package com.example.carrental.repository;

import com.example.carrental.model.Car;
import com.example.carrental.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> getAllByCar(Car car);

    List<Offer> findAllByPricePerDayBetween(long priceLowLimit, long priceHighLimit);

    List<Offer> findAllByMinimumRentalDaysLessThanEqual(long daysNumber);
}
