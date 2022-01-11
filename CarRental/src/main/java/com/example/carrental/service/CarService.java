package com.example.carrental.service;

import com.example.carrental.model.Car;
import com.example.carrental.model.CarType;
import com.example.carrental.model.EngineType;
import com.example.carrental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository repository;

    public Collection<Car> getAllCars(){
        Collection<Car> cars = (Collection<Car>) repository.findAll();
        return cars;
    }

    public Collection<Car> getCarsByBrand(String brand){
        Collection<Car> cars = (Collection<Car>) repository.getCarsByBrand(brand);
        return cars;
    }

    public Collection<Car> getCarsByBrandAndModel(String brand, String model){
        Collection<Car> cars = (Collection<Car>) repository.getCarsByBrandAndModel(brand, model);
        return cars;
    }

    public Collection<Car> getCarsByEngineType(EngineType engineType){
        Collection<Car> cars = (Collection<Car>) repository.getCarsByEngineType(engineType);
        return cars;
    }

    public Collection<Car> getCarsByType(CarType type){
        Collection<Car> cars = (Collection<Car>) repository.getCarsByType(type);
        return cars;
    }

    public Car getCarById(long id){
        Optional<Car> car = repository.findById(id);
        if(car.isPresent()){
            return car.get();
        }
        return null;
    }

    public Car getCartByPlateNumber(String plateNumber){
        Car car = repository.findCarByPlateNumber(plateNumber);
        return car;
    }

    public void saveCar(Car car){
        repository.save(car);
    }

    public void deleteCar(Car car){
        repository.delete(car);
    }
}
