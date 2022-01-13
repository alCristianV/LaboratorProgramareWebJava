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
    private Offer offer;

    @Column
    private boolean extraInsurance;

    @Column
    private long totalPrice;

    public Rental() {
    }

    public Rental(Date startDate, Date endDate, Client client, Offer offer, boolean extraInsurance) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.client = client;
        this.offer = offer;
        this.extraInsurance = extraInsurance;
    }

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

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public boolean isExtraInsurance() {
        return extraInsurance;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setExtraInsurance(boolean extraInsurance) {
        this.extraInsurance = extraInsurance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
