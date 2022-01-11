package com.example.carrental.repository;

import com.example.carrental.model.Car;
import com.example.carrental.model.CarType;
import com.example.carrental.model.EngineType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    Collection<Car> getCarsByBrand(String brand);

    Collection<Car> getCarsByBrandAndModel(String brand, String model);

    Car findCarByPlateNumber(String plateNumber);

    Collection<Car> getCarsByEngineType(EngineType engineType);

    Collection<Car> getCarsByType(CarType type);

}
