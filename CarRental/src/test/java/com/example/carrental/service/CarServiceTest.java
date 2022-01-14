package com.example.carrental.service;

import com.example.carrental.exception.CarAlreadyExistsException;
import com.example.carrental.exception.CarNotFoundException;
import com.example.carrental.model.Car;
import com.example.carrental.model.CarType;
import com.example.carrental.model.EngineType;
import com.example.carrental.repository.CarRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Year;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    private static Optional<Car> car;
    @InjectMocks
    private CarService carService;
    @Mock
    private CarRepository carRepository;

    @BeforeAll
    public static void setup() {
        car = Optional.of(new Car(1L, "B23TST", "Brand", "Model", Year.of(2010), 2000, 120, EngineType.PETROL, CarType.COMPACT));
    }

    @Test
    @DisplayName("Test create() Fail Car With same PlateNumber")
    public void testCreateFailPlateNumberCheck() {
        // arrange
        when(carRepository.findCarByPlateNumber(car.get().getPlateNumber())).thenReturn(car);

        // act & assert
        assertThrows(CarAlreadyExistsException.class,
                () -> carService.create(car.get()));

    }

    @Test
    @DisplayName("Test create() Success")
    public void testCreateSuccess() {
        // arrange
        when(carRepository.findCarByPlateNumber(car.get().getPlateNumber())).thenReturn(Optional.empty());

        when(carRepository.save(car.get())).thenReturn(car.get());

        // act
        Car actualCar = carService.create(car.get());

        // assert
        assertEquals(car.get(), actualCar);
        assertEquals(car.get().getPlateNumber(), actualCar.getPlateNumber());
        assertEquals(car.get().getId(), actualCar.getId());
    }

    @Test
    @DisplayName("Test update() Fail No Car Found")
    public void testUpdateFailNoCarFound() {
        // arrange
        when(carRepository.findById(car.get().getId())).thenReturn(Optional.empty());

        // act & assert
        assertThrows(CarNotFoundException.class,
                () -> carService.update(car.get()));

    }

    @Test
    @DisplayName("Test update() Success")
    public void testUpdateSuccess() {
        // arrange

        Car existingCar = new Car();
        existingCar.setId(car.get().getId());
        when(carRepository.findById(car.get().getId())).thenReturn(Optional.of(existingCar));
        when(carRepository.save(car.get())).thenReturn(car.get());

        // act
        Car actualCar = carService.update(car.get());

        //assert
        assertEquals(car.get(), actualCar);
        assertEquals(car.get().getPlateNumber(), actualCar.getPlateNumber());
        assertEquals(car.get().getId(), actualCar.getId());

    }

    @Test
    @DisplayName("Test Get All Cars By Brand And Type Success")
    public void testGetAllCarsByBrandAndType() {
        // arrange
        when(carRepository.findCarsByBrandAndType(car.get().getBrand(), car.get().getType())).thenReturn(List.of(car.get()));

        // act
        List<Car> cars = carService.get(car.get().getBrand(), car.get().getType());

        // assert
        assertEquals(List.of(car.get()), cars);
    }

    @Test
    @DisplayName("Test Get All Cars By Brand Success")
    public void testGetAllCarsByBrand() {
        // arrange
        when(carRepository.findCarsByBrand(car.get().getBrand())).thenReturn(List.of(car.get()));

        // act
        List<Car> cars = carService.get(car.get().getBrand(), null);

        // assert
        assertEquals(List.of(car.get()), cars);
    }

    @Test
    @DisplayName("Test Get All Cars By Type Success")
    public void testGetAllCarsByType() {
        // arrange
        when(carRepository.findCarsByType(car.get().getType())).thenReturn(List.of(car.get()));

        // act
        List<Car> cars = carService.get(null, car.get().getType());

        // assert
        assertEquals(List.of(car.get()), cars);
    }

    @Test
    @DisplayName("Test Get All Cars")
    public void testGetAllCars() {
        // arrange
        when(carRepository.findAll()).thenReturn(List.of(car.get()));

        // act
        List<Car> cars = carService.get(null, null);

        // assert
        assertEquals(List.of(car.get()), cars);
    }

    @Test
    @DisplayName("Test getCarById() Fail Car Not Present")
    public void testGetCarByIdCarNotPresent() {
        // arrange
        when(carRepository.findCarById(car.get().getId())).thenReturn(Optional.empty());

        // act & assert
        assertThrows(CarNotFoundException.class,
                () -> carService.getCarById(car.get().getId()));
    }

    @Test
    @DisplayName("Test getCarById() Success")
    public void testGetCarByIdSuccess() {
        // arrange

        when(carRepository.findCarById(car.get().getId())).thenReturn(car);

        // act
        Car actualCar = carService.getCarById(car.get().getId());

        // assert
        assertEquals(car.get(), actualCar);
        assertEquals(car.get().getPlateNumber(), actualCar.getPlateNumber());
        assertEquals(car.get().getId(), actualCar.getId());
    }


}
