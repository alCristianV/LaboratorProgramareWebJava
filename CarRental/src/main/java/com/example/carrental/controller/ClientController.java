package com.example.carrental.controller;

import com.example.carrental.dto.CreateClientRequestDto;
import com.example.carrental.dto.UpdateClientRequestDto;
import com.example.carrental.exception.InvalidUpdateRequestException;
import com.example.carrental.mapper.ClientMapper;
import com.example.carrental.model.Client;
import com.example.carrental.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clients")
@Validated
public class ClientController {

    @Autowired
    private ClientService service;
    @Autowired
    private ClientMapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(service.getAllClients());
    }

    @GetMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getClient(@PathVariable("email") String email) {
        return ResponseEntity.ok(service.getClientByEmail(email));
    }

    @PostMapping
    public ResponseEntity<Client> create(
            @Valid
            @RequestBody CreateClientRequestDto request) {
        Client client = service.create(mapper.createClientRequestDtoToClient(request));
        return ResponseEntity.created(URI.create("/clients/" + client.getEmail()))
                .body(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(
            @PathVariable long id,
            @Valid
            @RequestBody UpdateClientRequestDto request) {
        if (id != request.getId()) {
            throw new InvalidUpdateRequestException();
        }
        return ResponseEntity.ok(service.update(mapper.updateClientRequestDtoToClient(request)));
    }


}
