package com.example.carrental.controller;

import com.example.carrental.dto.CreateRentalRequestDto;
import com.example.carrental.model.Client;
import com.example.carrental.model.Offer;
import com.example.carrental.model.Rental;
import com.example.carrental.service.ClientService;
import com.example.carrental.service.OfferService;
import com.example.carrental.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rentals")
@Validated
public class RentalController {

    @Autowired
    private RentalService service;

    @Autowired
    private ClientService clientService;

    @Autowired
    private OfferService offerService;

    @GetMapping("/{active}")
    public List<Rental> getActiveRentals(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return service.getAllActiveRentals(date);
    }

    @GetMapping
    public List<Rental> getAllRentals() {
        return service.getAllRentals();
    }

    @PostMapping
    public ResponseEntity<Rental> create(
            @Valid
            @RequestBody CreateRentalRequestDto request) {
        Client client = clientService.getClientByEmail(request.getClientEmail());
        Offer offer = offerService.getOfferById(request.getOfferId());

        Rental rental = new Rental(request.getStartDate(), request.getEndDate(), client, offer, request.isExtraInsurance());
        rental = service.create(rental);
        return ResponseEntity.created(URI.create("/rentals/" + rental.getId()))
                .body(rental);

    }
}
