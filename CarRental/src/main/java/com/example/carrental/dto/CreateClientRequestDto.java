package com.example.carrental.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateClientRequestDto {

    @NotBlank
    @Size(max = 13, min = 13)
    private String cnp;

    @NotBlank
    @Size(max = 100)
    @Email
    private String email;

    @NotBlank
    @Size(max = 100)
    private String firstName;

    @NotBlank
    @Size(max = 100)
    private String lastName;

    @NotBlank
    @Size(max = 100)
    private String city;

    public CreateClientRequestDto() {
    }

    public CreateClientRequestDto(String cnp, String email, String firstName, String lastName, String city) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
