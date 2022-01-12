package com.example.carrental.repository;

import com.example.carrental.model.Offer;
import com.example.carrental.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    @Query(value = "select distinct rental from rent where :date between rental.startDate AND rental.endDate", nativeQuery = true)
    Collection<Rental> getAllActiveRentals(Date date);

    @Query(value = "select distinct rental.offer from rent where :date not between rental.startDate AND rental.endDate", nativeQuery = true)
    Collection<Offer> getAllAvailableOffers(Date date);

}
