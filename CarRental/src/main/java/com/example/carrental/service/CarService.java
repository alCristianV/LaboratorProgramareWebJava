package com.example.carrental.service;

import com.example.carrental.exception.CarAlreadyExistsException;
import com.example.carrental.exception.CarNotFoundException;
import com.example.carrental.model.Car;
import com.example.carrental.model.CarType;
import com.example.carrental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository repository;

    public Car create(Car car) {
        checkUniquePlateNumber(car);
        return repository.save(car);
    }

    public Car update(Car car) {
        Car existingCar = repository.findById(car.getId())
                .orElseThrow(() -> new CarNotFoundException());
        if (!car.getPlateNumber().equals(existingCar.getPlateNumber())) {
            checkUniquePlateNumber(car);
        }
        return repository.save(car);
    }

    public List<Car> get(String brand, CarType carType) {
        if (brand != null) {
            if (carType != null) {
                return repository.findCarsByBrandAndType(brand, carType);
            }
            return repository.findCarsByBrand(brand);
        }
        if (carType != null) {
            return repository.findCarsByType(carType);
        }
        return repository.findAll();
    }

    public Car getCarById(long id) {
        Optional<Car> car = repository.findCarById(id);
        if (car.isPresent()) {
            return car.get();
        }
        throw new CarNotFoundException();
    }

    private void checkUniquePlateNumber(Car car) {
        Optional<Car> existingCar = repository.findCarByPlateNumber(car.getPlateNumber());
        if (existingCar.isPresent()) {
            throw new CarAlreadyExistsException();
        }
    }

}
