package com.example.carrental.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Offer offer;

    @Column
    private boolean extraInsurance;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public boolean isExtraInsurance() {
        return extraInsurance;
    }

    public void setExtraInsurance(boolean extraInsurance) {
        this.extraInsurance = extraInsurance;
    }
}
