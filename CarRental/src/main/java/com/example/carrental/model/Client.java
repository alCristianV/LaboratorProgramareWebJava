package com.example.carrental.model;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String cnp;

    @Column(unique = true)
    private String email;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String city;

    public Client() {

    }

    public Client(String cnp, String email, String firstName, String lastName, String city) {
        this.cnp = cnp;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }

    public Client(long id, String cnp, String email, String firstName, String lastName, String city) {
        this.id = id;
        this.cnp = cnp;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
