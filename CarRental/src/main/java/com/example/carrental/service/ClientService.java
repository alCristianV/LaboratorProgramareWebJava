package com.example.carrental.service;

import com.example.carrental.model.Client;
import com.example.carrental.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public Collection<Client> getAllClients(){
        Collection<Client> clients = (Collection<Client>) repository.findAll();
        return clients;
    }

    public Client getClientById(long id){
        Optional<Client> client = repository.findById(id);
        if(client.isPresent()){
            return client.get();
        }
        return null;
    }

    public Client getClientByCnp(String cnp){
        Client client = repository.findByCnp(cnp);
        return client;
    }

    public void saveClient(Client client){
        repository.save(client);
    }

    public void deleteClient(Client client){
        repository.delete(client);
    }
}
