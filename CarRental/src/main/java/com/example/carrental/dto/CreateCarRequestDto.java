package com.example.carrental.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Year;

public class CreateCarRequestDto {

    @NotBlank
    private String plateNumber;

    @Size(max = 50)
    @NotBlank
    private String brand;

    @Size(max = 100)
    @NotBlank
    private String model;

    @NotBlank
    private Year fabricationYear;

    @NotBlank
    @Min(100)
    @Max(10000)
    private int engineCapacity;

    @NotBlank
    @Min(10)
    @Max(1000)
    private int horsePower;

    @NotBlank
    @Size(max = 20)
    private String engineType;

    @NotBlank
    @Size(max = 20)
    private String type;

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
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

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
