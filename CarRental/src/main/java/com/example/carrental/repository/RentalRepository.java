package com.example.carrental.repository;

import com.example.carrental.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    @Query(value = "select distinct * from rental rent where :date between rent.start_date AND rent.end_date", nativeQuery = true)
    List<Rental> getAllActiveRentals(Date date);

    @Query(value = "select distinct offer_id from rental rent where :startDate between rent.start_date AND rent.end_date OR :endDate between rent.start_date AND rent.end_date", nativeQuery = true)
    List<Long> getAllActiveOffersIds(Date startDate, Date endDate);


}
