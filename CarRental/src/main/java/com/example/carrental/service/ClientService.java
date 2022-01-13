package com.example.carrental.service;

import com.example.carrental.exception.ClientAlreadyExistsException;
import com.example.carrental.exception.ClientNotFoundException;
import com.example.carrental.model.Client;
import com.example.carrental.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public List<Client> getAllClients() {
        return repository.findAll();
    }

    public Client getClientByEmail(String email) {
        Optional<Client> client = repository.findByEmail(email);
        if (client.isPresent()) {
            return client.get();
        }
        throw new ClientNotFoundException();
    }

    public Client create(Client client) {
        checkUniqueEmail(client);
        checkUniqueCnp(client);
        return repository.save(client);
    }

    public Client update(Client client) {
        Client existingDriver = repository.findById(client.getId())
                .orElseThrow(() -> new ClientNotFoundException());
        if (!client.getEmail().equals(existingDriver.getEmail())) {
            checkUniqueEmail(client);
        }
        if (!client.getCnp().equals(existingDriver.getCnp())) {
            checkUniqueCnp(client);
        }
        return repository.save(client);
    }

    private void checkUniqueEmail(Client client) {
        Optional<Client> existingClient = repository.findByEmail(client.getEmail());
        if (existingClient.isPresent()) {
            throw new ClientAlreadyExistsException("email");
        }
    }

    private void checkUniqueCnp(Client client) {
        Optional<Client> existingClient = repository.findByCnp(client.getCnp());
        if (existingClient.isPresent()) {
            throw new ClientAlreadyExistsException("cnp");
        }
    }
}
