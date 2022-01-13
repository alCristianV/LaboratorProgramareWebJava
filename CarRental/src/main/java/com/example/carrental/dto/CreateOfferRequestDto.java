package com.example.carrental.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CreateOfferRequestDto {

    @NotNull
    private long carId;

    @Min(0)
    @Max(10000)
    @NotNull
    private long pricePerDay;

    @Min(0)
    @NotNull
    private int minimumRentalDays;

    @Min(0)
    @NotNull
    private long extraInsuranceCost;

    public CreateOfferRequestDto() {
    }

    public CreateOfferRequestDto(long carId, long pricePerDay, int minimumRentalDays, long extraInsuranceCost) {
        this.carId = carId;
        this.pricePerDay = pricePerDay;
        this.minimumRentalDays = minimumRentalDays;
        this.extraInsuranceCost = extraInsuranceCost;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public long getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(long pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public int getMinimumRentalDays() {
        return minimumRentalDays;
    }

    public void setMinimumRentalDays(int minimumRentalDays) {
        this.minimumRentalDays = minimumRentalDays;
    }

    public long getExtraInsuranceCost() {
        return extraInsuranceCost;
    }

    public void setExtraInsuranceCost(long extraInsuranceCost) {
        this.extraInsuranceCost = extraInsuranceCost;
    }
}
