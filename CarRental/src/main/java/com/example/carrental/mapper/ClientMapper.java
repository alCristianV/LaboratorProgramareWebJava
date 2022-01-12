package com.example.carrental.mapper;

import com.example.carrental.dto.CreateClientRequestDto;
import com.example.carrental.dto.UpdateClientRequestDto;
import com.example.carrental.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client createClientRequestDtoToClient(CreateClientRequestDto request) {
        return new Client(request.getCnp(), request.getEmail(), request.getFirstName(), request.getLastName(), request.getCity());
    }

    public Client updateClientRequestDtoToClient(UpdateClientRequestDto request) {
        return new Client(request.getId(), request.getCnp(), request.getEmail(), request.getFirstName(), request.getLastName(), request.getCity());
    }
}
