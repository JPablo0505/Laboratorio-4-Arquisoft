package com.banco.demo.controller;

import com.banco.demo.entity.Client;
import com.banco.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @QueryMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @QueryMapping
    public Client getClientById(@Argument Long id) {
        return clientService.getClientById(id);
    }

    @MutationMapping
    public Client createClient(@Argument String name) {
        return clientService.createClient(name);
    }
}