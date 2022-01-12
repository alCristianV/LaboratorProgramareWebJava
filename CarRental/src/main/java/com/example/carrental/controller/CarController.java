package com.example.carrental.controller;

import com.example.carrental.dto.CreateCarRequestDto;
import com.example.carrental.dto.UpdateCarRequestDto;
import com.example.carrental.exception.InvalidUpdateRequestException;
import com.example.carrental.mapper.CarMapper;
import com.example.carrental.model.Car;
import com.example.carrental.model.CarType;
import com.example.carrental.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cars")
@Validated
public class CarController {

    @Autowired
    private CarService service;

    @Autowired
    private CarMapper mapper;

    @PostMapping
    public ResponseEntity<Car> create(
            @Valid
            @RequestBody CreateCarRequestDto request) {
        Car car = service.create(mapper.createCarRequestDtoToCar(request));
        return ResponseEntity.created(URI.create("/cars/" + car.getId()))
                .body(car);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> update(
            @PathVariable long id,
            @Valid
            @RequestBody UpdateCarRequestDto request) {
        if (id != request.getId()) {
            throw new InvalidUpdateRequestException();
        }
        return ResponseEntity.ok(service.update(mapper.updateClientRequestDtoToClient(request)));
    }


    @GetMapping
    public List<Car> get(@RequestParam(required = false) String brand,
                         @RequestParam(required = false) CarType carType) {
        return service.get(brand, carType);
    }


}
