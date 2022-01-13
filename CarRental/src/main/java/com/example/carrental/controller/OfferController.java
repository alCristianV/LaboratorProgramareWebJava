package com.example.carrental.controller;

import com.example.carrental.dto.CreateOfferRequestDto;
import com.example.carrental.model.Car;
import com.example.carrental.model.Offer;
import com.example.carrental.service.CarService;
import com.example.carrental.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/offers")
@Validated
public class OfferController {

    @Autowired
    private OfferService service;

    @Autowired
    private CarService carService;

    @GetMapping
    public List<Offer> get(@RequestParam(defaultValue = "0", required = false) long priceLowLimit,
                           @RequestParam(defaultValue = "10000", required = false) long priceHighLimit,
                           @RequestParam(defaultValue = "0", required = false) int rentalDays) {
        return service.get(priceLowLimit, priceHighLimit, rentalDays);
    }

    @PostMapping
    public ResponseEntity<Offer> create(
            @Valid
            @RequestBody CreateOfferRequestDto request) {
        Car car = carService.getCarById(request.getCarId());
        Offer offer = new Offer(car, request.getPricePerDay(), request.getMinimumRentalDays(), request.getExtraInsuranceCost());
        service.create(offer);
        return ResponseEntity.created(URI.create("/offers/" + offer.getId()))
                .body(offer);

    }
}
