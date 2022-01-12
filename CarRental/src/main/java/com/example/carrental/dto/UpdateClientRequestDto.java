package com.example.carrental.dto;

import javax.validation.constraints.NotNull;

public class UpdateClientRequestDto extends CreateClientRequestDto {

    @NotNull
    private long id;

    public UpdateClientRequestDto() {
    }

    public UpdateClientRequestDto(String cnp, String email, String firstName, String lastName, String city, long id) {
        super(cnp, email, firstName, lastName, city);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
