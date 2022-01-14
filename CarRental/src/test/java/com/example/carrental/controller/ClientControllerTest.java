package com.example.carrental.controller;

import com.example.carrental.exception.ClientNotFoundException;
import com.example.carrental.mapper.ClientMapper;
import com.example.carrental.model.Client;
import com.example.carrental.service.ClientService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    private static Client client;
    @MockBean
    private ClientService clientService;
    @MockBean
    private ClientMapper clientMapper;
    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void setup() {
        client = new Client(1L, "1234567890123", "client@email.com", "Mihai", "Ion", "Brasov");

    }

    @Test
    public void createMock() {
        assertNotNull(mockMvc);
    }

    @Test
    public void testGetClients() throws Exception {
        when(clientService.getAllClients()).thenReturn(List.of(client));

        String endpoint = "/clients";
        mockMvc.perform(get(endpoint))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetClientOk() throws Exception {
        when(clientService.getClientByEmail(client.getEmail())).thenReturn(client);

        String endpoint = "/clients/client@email.com";
        mockMvc.perform(get(endpoint))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetClientBadRequest() throws Exception {
        when(clientService.getClientByEmail(any(String.class))).thenThrow(new ClientNotFoundException());

        String endpoint = "/clients/client@email.com";
        mockMvc.perform(get(endpoint))
                .andExpect(status().isBadRequest());
    }

//    @Test
//    public void testCreateClientSuccess() throws Exception {
//        CreateClientRequestDto createClientRequestDto = new CreateClientRequestDto(client.getCnp(), client.getEmail(), client.getFirstName(), client.getLastName(), client.getCity());
//        when(clientService.create(client)).thenReturn(client);
//        when(clientMapper.createClientRequestDtoToClient(createClientRequestDto)).thenReturn(client);
//        String endpoint = "/clients";
//
//        mockMvc.perform(post(endpoint)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());
//    }

//    @Test
//    public void testCreateClientBadRequest() throws Exception {
//        when(clientService.create(client)).thenThrow(new ClientAlreadyExistsException("test"));
//
//        String endpoint = "/clients";
//        mockMvc.perform(post(endpoint))
//                .andExpect(status().isBadRequest());
//    }

//    @Test
//    public void testUpdateClientSuccess() throws Exception {
//        when(clientService.update(client.getId(),client)).thenReturn(client);
//
//        String endpoint = "/clients/1";
//        mockMvc.perform(put(endpoint))
//                .andExpect(status().isOk());
//    }

    @Test
    public void testUpdateClientInvalidUpdateRequestException() throws Exception {
        when(clientService.update(2, client)).thenReturn(client);

        String endpoint = "/clients/2";
        mockMvc.perform(put(endpoint))
                .andExpect(status().isBadRequest());
    }


}
