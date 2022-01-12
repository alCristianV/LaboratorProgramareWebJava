package com.example.carrental.repository;

import com.example.carrental.model.Car;
import com.example.carrental.model.CarType;
import com.example.carrental.model.EngineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Collection<Car> getCarsByBrandContains(String brand);

    Collection<Car> getCarsByBrandAndModel(String brand, String model);

    Optional<Car> findCarByPlateNumber(String plateNumber);

    List<Car> findCarsByBrandAndType(String brand, CarType carType);

    List<Car> findCarsByBrand(String brand);

    List<Car> findCarsByType(CarType type);

    Collection<Car> getCarsByEngineType(EngineType engineType);

    Collection<Car> getCarsByType(CarType type);

}
