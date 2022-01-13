package com.example.carrental.repository;

import com.example.carrental.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    Optional<Offer> getById(long id);

    List<Offer> findAllByPricePerDayBetween(long priceLowLimit, long priceHighLimit);

    List<Offer> findAllByPricePerDayBetweenAndMinimumRentalDaysLessThanEqual(long priceLowLimit, long priceHighLimit, int daysNumber);

}
