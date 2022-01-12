package com.example.carrental.model;

import javax.persistence.*;
import java.time.Year;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String plateNumber;

    @Column
    private String brand;

    @Column
    private String model;

    @Column
    private Year fabricationYear;

    @Column
    private int engineCapacity;

    @Column
    private int horsePower;

    @Column
    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @Column
    @Enumerated(EnumType.STRING)
    private CarType type;

    public Car(long id, String plateNumber, String brand, String model, Year fabricationYear, int engineCapacity, int horsePower, EngineType engineType, CarType type) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.model = model;
        this.fabricationYear = fabricationYear;
        this.engineCapacity = engineCapacity;
        this.horsePower = horsePower;
        this.engineType = engineType;
        this.type = type;
    }

    public Car() {
    }

    public Car(String plateNumber, String brand, String model, Year fabricationYear, int engineCapacity, int horsePower, EngineType engineType, CarType type) {
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.model = model;
        this.fabricationYear = fabricationYear;
        this.engineCapacity = engineCapacity;
        this.horsePower = horsePower;
        this.engineType = engineType;
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Year getFabricationYear() {
        return fabricationYear;
    }

    public void setFabricationYear(Year fabricationYear) {
        this.fabricationYear = fabricationYear;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public EngineType getFuelType() {
        return engineType;
    }

    public void setFuelType(EngineType engineType) {
        this.engineType = engineType;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }
}
