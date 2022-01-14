package com.example.carrental.service;

import com.example.carrental.exception.OfferInvalidFilterException;
import com.example.carrental.exception.OfferNotFoundException;
import com.example.carrental.model.Car;
import com.example.carrental.model.CarType;
import com.example.carrental.model.EngineType;
import com.example.carrental.model.Offer;
import com.example.carrental.repository.OfferRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Year;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OfferServiceTest {

    private static Optional<Offer> offer;
    private static Car car;
    @InjectMocks
    private OfferService offerService;
    @Mock
    private OfferRepository offerRepository;

    @BeforeAll
    public static void setup() {
        car = new Car(1L, "B23TST", "Brand", "Model", Year.of(2010), 2000, 120, EngineType.PETROL, CarType.COMPACT);
        offer = Optional.of(new Offer(1L, car, 100, 5, 250));
    }

    @Test
    @DisplayName("Test getOfferById() Fail Offer Not Present")
    public void testGetOfferByIdOfferNotPresent() {
        // arrange
        when(offerRepository.getById(offer.get().getId())).thenReturn(Optional.empty());

        // act & assert
        assertThrows(OfferNotFoundException.class,
                () -> offerService.getOfferById(offer.get().getId()));
    }

    @Test
    @DisplayName("Test getOfferById() Success")
    public void testGetOfferByIdSuccess() {
        // arrange

        when(offerRepository.getById(offer.get().getId())).thenReturn(offer);

        // act
        Offer actualOffer = offerService.getOfferById(offer.get().getId());

        // assert
        assertEquals(offer.get(), actualOffer);
        assertEquals(offer.get().getCar().getId(), actualOffer.getCar().getId());
        assertEquals(offer.get().getId(), actualOffer.getId());
    }

    @Test
    @DisplayName("Test create() Success")
    public void testCreateSuccess() {
        // arrange
        when(offerRepository.save(offer.get())).thenReturn(offer.get());

        // act
        Offer actualOffer = offerService.create(offer.get());

        // assert
        assertEquals(offer.get(), actualOffer);
        assertEquals(offer.get().getCar().getId(), actualOffer.getCar().getId());
        assertEquals(offer.get().getId(), actualOffer.getId());
    }

    @Test
    @DisplayName("Test Get All Offers priceLowLimit <0 Fail")
    public void testGetAllOffersPriceLowFail() {

        // act & assert
        OfferInvalidFilterException e = assertThrows(OfferInvalidFilterException.class,
                () -> offerService.get(-1L, 0, 0));

        assertEquals("priceLowLimit has not a valid value", e.getMessage());
    }

    @Test
    @DisplayName("Test Get All Offers priceHighLimit <0 Fail")
    public void testGetAllOffersPriceHighFail() {

        // act & assert
        OfferInvalidFilterException e = assertThrows(OfferInvalidFilterException.class,
                () -> offerService.get(0, -1, 0));

        assertEquals("priceHighLimit has not a valid value", e.getMessage());
    }

    @Test
    @DisplayName("Test Get All Offers rentalDays <0 Fail")
    public void testGetAllOffersRentalDaysFail() {

        // act & assert
        OfferInvalidFilterException e = assertThrows(OfferInvalidFilterException.class,
                () -> offerService.get(0, 0, -1));

        assertEquals("rentalDays has not a valid value", e.getMessage());
    }
}
