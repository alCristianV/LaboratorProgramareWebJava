package com.example.carrental.model;

import javax.persistence.*;

@Entity
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Car car;

    @Column
    private long pricePerDay;

    @Column
    private int minimumRentalDays;

    @Column
    private long extraInsuranceCost;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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
