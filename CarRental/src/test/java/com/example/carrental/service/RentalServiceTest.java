package com.example.carrental.service;

import com.example.carrental.exception.OfferMinimumDaysException;
import com.example.carrental.exception.OfferNotAvailableException;
import com.example.carrental.model.*;
import com.example.carrental.repository.RentalRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RentalServiceTest {

    private static Optional<Rental> rental;
    private static Offer offer;
    private static Client client;
    @InjectMocks
    private RentalService rentalService;
    @Mock
    private RentalRepository rentalRepository;

    @BeforeAll
    public static void setup() throws ParseException {
        Car car = new Car(1L, "B23TST", "Brand", "Model", Year.of(2010), 2000, 120, EngineType.PETROL, CarType.COMPACT);
        offer = new Offer(1L, car, 100, 5, 250);

        client = new Client(1L, "1234567890123", "client@email.com", "Mihai", "Ion", "Brasov");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        rental = Optional.of(new Rental(1L, formatter.parse("2022-01-01"), formatter.parse("2022-01-10"), client, offer, false));
    }

    @Test
    @DisplayName("Test getAllActiveRentals() Date Null Success")
    public void testGetAllActiveRentalsDateNull() {
        // arrange

        when(rentalRepository.getAllActiveRentals(any(Date.class))).thenReturn(List.of(rental.get()));

        // act
        List<Rental> rentals = rentalService.getAllActiveRentals(null);

        // assert
        assertEquals(List.of(rental.get()), rentals);
    }

    @Test
    @DisplayName("Test getAllRentals() Success")
    public void testGetAllRentals() {
        // arrange

        when(rentalRepository.findAll()).thenReturn(List.of(rental.get()));

        // act
        List<Rental> rentals = rentalService.getAllRentals();

        // assert
        assertEquals(List.of(rental.get()), rentals);
        assertEquals(1, rentals.size());
    }

    @Test
    @DisplayName("Test create() Offer Not Available Fail")
    public void testGetAllRentalsOfferNotAvailableFail() {
        // arrange

        when(rentalRepository.getAllActiveOffersIds(any(Date.class), any(Date.class))).thenReturn(List.of(rental.get().getId()));

        // act & assert
        assertThrows(OfferNotAvailableException.class,
                () -> rentalService.create(rental.get()));

    }

    @Test
    @DisplayName("Test create() Minimum Days Exception Fail")
    public void testGetAllRentalsMinimumDaysExceptionFail() {
        // arrange
        when(rentalRepository.getAllActiveOffersIds(any(Date.class), any(Date.class))).thenReturn(List.of(2L));

        offer.setMinimumRentalDays(11);

        // act & assert
        assertThrows(OfferMinimumDaysException.class,
                () -> rentalService.create(rental.get()));

        offer.setMinimumRentalDays(5);
    }

    @Test
    @DisplayName("Test create() without Extra Insurance Success")
    public void testGetAllRentalsSuccess() {
        // arrange
        when(rentalRepository.getAllActiveOffersIds(any(Date.class), any(Date.class))).thenReturn(List.of(2L));
        rental.get().setTotalPrice(900);
        when(rentalRepository.save(rental.get())).thenReturn(rental.get());

        rental.get().setTotalPrice(0);
        // act & assert
        Rental rent = rentalService.create(rental.get());

        assertEquals(900, rent.getTotalPrice());
        assertEquals(rental.get().getId(), rent.getId());
        assertEquals(rental.get().getOffer().getId(), rent.getOffer().getId());

    }

    @Test
    @DisplayName("Test create() with Extra Insurance Success")
    public void testGetAllRentalsExtraInsuranceSuccess() {
        // arrange
        when(rentalRepository.getAllActiveOffersIds(any(Date.class), any(Date.class))).thenReturn(List.of(2L));
        rental.get().setTotalPrice(900);
        rental.get().setExtraInsurance(true);
        when(rentalRepository.save(rental.get())).thenReturn(rental.get());

        rental.get().setTotalPrice(0);
        // act & assert
        Rental rent = rentalService.create(rental.get());

        assertEquals(1150, rent.getTotalPrice());
        assertEquals(rental.get().getId(), rent.getId());
        assertEquals(rental.get().getOffer().getId(), rent.getOffer().getId());

    }


}
