package com.example.carrental.dto;

import javax.validation.constraints.NotNull;

public class UpdateCarRequestDto extends CreateCarRequestDto {

    @NotNull
    private long id;

    public UpdateCarRequestDto() {
    }

    public UpdateCarRequestDto(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
