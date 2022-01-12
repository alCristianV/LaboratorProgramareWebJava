package com.example.carrental.mapper;

import com.example.carrental.dto.CreateCarRequestDto;
import com.example.carrental.dto.UpdateCarRequestDto;
import com.example.carrental.exception.CarInvalidParameterException;
import com.example.carrental.model.Car;
import com.example.carrental.model.CarType;
import com.example.carrental.model.EngineType;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    public Car createCarRequestDtoToCar(CreateCarRequestDto request) {

        EngineType engineType = checkCarEngineType(request.getEngineType().toUpperCase());
        CarType carType = checkCarType(request.getType().toUpperCase());

        return new Car(request.getPlateNumber(), request.getBrand(), request.getModel(), request.getFabricationYear(), request.getEngineCapacity(), request.getHorsePower(), engineType, carType);
    }

    public Car updateClientRequestDtoToClient(UpdateCarRequestDto request) {
        EngineType engineType = checkCarEngineType(request.getEngineType().toUpperCase());
        CarType carType = checkCarType(request.getType().toUpperCase());

        return new Car(request.getId(), request.getPlateNumber(), request.getBrand(), request.getModel(), request.getFabricationYear(), request.getEngineCapacity(), request.getHorsePower(), engineType, carType);

    }

    private EngineType checkCarEngineType(String engine) {
        EngineType engineType;
        try {
            engineType = EngineType.valueOf(engine);
        } catch (IllegalArgumentException e) {
            throw new CarInvalidParameterException("engineType");
        }
        return engineType;
    }

    private CarType checkCarType(String type) {
        CarType carType;
        try {
            carType = CarType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new CarInvalidParameterException("carType");
        }
        return carType;
    }
}
