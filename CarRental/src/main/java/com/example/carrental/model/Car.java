package com.example.carrental.model;

import javax.persistence.*;
import java.time.Year;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String brand;

    @Column
    private String model;

    @Column
    private Year fabricationYear;

    @Column
    private String plateNumber;

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
