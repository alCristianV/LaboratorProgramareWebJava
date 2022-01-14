package com.example.carrental.service;

import com.example.carrental.exception.ClientAlreadyExistsException;
import com.example.carrental.exception.ClientNotFoundException;
import com.example.carrental.model.Client;
import com.example.carrental.repository.ClientRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    private static Optional<Client> client;
    @InjectMocks
    private ClientService clientService;
    @Mock
    private ClientRepository clientRepository;

    @BeforeAll
    public static void setup() {
        client = Optional.of(new Client(1L, "1234567890123", "client@email.com", "Mihai", "Ion", "Brasov"));

    }

    @Test
    @DisplayName("Test getAllClients() Success")
    public void testGetAllClients() {
        // arrange

        when(clientRepository.findAll()).thenReturn(List.of(client.get()));

        // act
        List<Client> clients = clientService.getAllClients();

        // assert
        assertEquals(List.of(client.get()), clients);
    }

    @Test
    @DisplayName("Test getClientByEmai() Fail Client Not Present")
    public void testGetClientByEmailClientNotPresent() {
        // arrange

        when(clientRepository.findByEmail(client.get().getEmail())).thenReturn(Optional.empty());

        // act & assert
        assertThrows(ClientNotFoundException.class,
                () -> clientService.getClientByEmail(client.get().getEmail()));
    }

    @Test
    @DisplayName("Test getClientByEmai() Success")
    public void testGetClientByEmailSuccess() {
        // arrange

        when(clientRepository.findByEmail(client.get().getEmail())).thenReturn(client);

        // act
        Client actualClient = clientService.getClientByEmail(client.get().getEmail());

        // assert
        assertEquals(client.get(), actualClient);
        assertEquals(client.get().getEmail(), actualClient.getEmail());
        assertEquals(client.get().getId(), actualClient.getId());
        assertEquals(client.get().getCnp(), actualClient.getCnp());
        assertEquals(client.get().getFirstName(), actualClient.getFirstName());
        assertEquals(client.get().getLastName(), actualClient.getLastName());
        assertEquals(client.get().getCity(), actualClient.getCity());
    }


    @Test
    @DisplayName("Test create() Fail Client With same Email")
    public void testCreateFailEmailCheck() {
        // arrange
        when(clientRepository.findByEmail(client.get().getEmail())).thenReturn(client);

        // act & assert
        assertThrows(ClientAlreadyExistsException.class,
                () -> clientService.create(client.get()));

    }

    @Test
    @DisplayName("Test create() Fail Client With same Cnp")
    public void testCreateFailCnpCheck() {
        // arrange
        when(clientRepository.findByCnp(client.get().getCnp())).thenReturn(client);

        // act & assert
        assertThrows(ClientAlreadyExistsException.class,
                () -> clientService.create(client.get()));

    }

    @Test
    @DisplayName("Test create() Success")
    public void testCreateSuccess() {
        // arrange
        when(clientRepository.findByCnp(client.get().getCnp())).thenReturn(Optional.empty());
        when(clientRepository.findByEmail(client.get().getEmail())).thenReturn(Optional.empty());
        when(clientRepository.save(client.get())).thenReturn(client.get());

        // act
        Client actualClient = clientService.create(client.get());

        // assert
        assertEquals(client.get(), actualClient);
        assertEquals(client.get().getEmail(), actualClient.getEmail());
        assertEquals(client.get().getId(), actualClient.getId());
        assertEquals(client.get().getCnp(), actualClient.getCnp());
        assertEquals(client.get().getFirstName(), actualClient.getFirstName());
        assertEquals(client.get().getLastName(), actualClient.getLastName());
        assertEquals(client.get().getCity(), actualClient.getCity());
    }

    @Test
    @DisplayName("Test update() Fail No Client Found")
    public void testUpdateFailNoClientFound() {
        // arrange
        when(clientRepository.findById(client.get().getId())).thenReturn(Optional.empty());

        // act & assert
        assertThrows(ClientNotFoundException.class,
                () -> clientService.update(client.get()));

    }

    @Test
    @DisplayName("Test update() Success")
    public void testUpdateSuccess() {
        // arrange

        Client existingClient = new Client();
        existingClient.setId(client.get().getId());
        when(clientRepository.findById(client.get().getId())).thenReturn(Optional.of(existingClient));
        when(clientRepository.save(client.get())).thenReturn(client.get());

        // act
        Client actualClient = clientService.update(client.get());

        //assert
        assertEquals(client.get(), actualClient);
        assertEquals(client.get().getEmail(), actualClient.getEmail());
        assertEquals(client.get().getId(), actualClient.getId());
        assertEquals(client.get().getCnp(), actualClient.getCnp());
        assertEquals(client.get().getFirstName(), actualClient.getFirstName());
        assertEquals(client.get().getLastName(), actualClient.getLastName());
        assertEquals(client.get().getCity(), actualClient.getCity());

    }


}
