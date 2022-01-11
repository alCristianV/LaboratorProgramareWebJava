package com.example.carrental.repository;

import com.example.carrental.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    Client findByCnp(String cnp);
}
