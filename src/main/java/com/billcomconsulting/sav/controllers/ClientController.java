package com.billcomconsulting.sav.controllers;


import com.billcomconsulting.sav.entities.Client;
import com.billcomconsulting.sav.exceptions.ResourceNotFoundException;
import com.billcomconsulting.sav.repositories.ClientRepository;
import com.billcomconsulting.sav.services.interfaces.IServiceClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController

@RequestMapping(value = "/api/user", produces = "application/json", consumes = "application/json")
@AllArgsConstructor
public class ClientController {

    ClientRepository clientRepository;
    IServiceClient iServiceClient;


    @GetMapping(value = "/getallClients", consumes = MediaType.ALL_VALUE, produces = "application/json")
    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }


    @PostMapping("/addClient")
    @ResponseBody
    public ResponseEntity<?> createClient(@RequestBody Client client) throws IOException {

        Client savedclient = iServiceClient.addClient(client);
        return ResponseEntity.ok(savedclient);
    }


    @PutMapping(value = "/UPDATE_Client/{id}", consumes = MediaType.ALL_VALUE, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Client> updateClient(@PathVariable long id, @RequestParam(required = false) String cin, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName, @RequestParam(required = false) String email, @RequestParam(required = false) String phoneNumber1, @RequestParam(required = false) String phoneNumber2) throws Exception {


        String cclient = "{\"cin\": \"" + cin + "\",   \"firstName\": \"" + firstName + "\",   \"lastName\": \"" + lastName + "\",   \"email\": \"" + email + "\",   \"phoneNumber1\": \"" + phoneNumber1 + "\",   \"phoneNumber2\": \"" + phoneNumber2 + "\" }";

        Client ogclient = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + id));

        System.out.println(ogclient);

        ObjectMapper objectMapper = new ObjectMapper();
        Client client = objectMapper.readValue(cclient, Client.class);


        if (cin != null) {
            ogclient.setCin(client.getCin());
        }

        if (firstName != null) {
            ogclient.setFirstName(client.getFirstName());
        }

        if (lastName != null) {
            ogclient.setLastName(client.getLastName());
        }


        if (email != null) {
            ogclient.setEmail(client.getEmail());
        }

        if (phoneNumber1 != null) {
            ogclient.setPhoneNumber1(client.getPhoneNumber1());
        }

        if (phoneNumber2 != null) {
            ogclient.setPhoneNumber2(client.getPhoneNumber2());
        }


        iServiceClient.addClient(ogclient);

        return ResponseEntity.ok(ogclient);
    }


    @DeleteMapping(value = "/deleteClient/{idClient}", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public boolean deleteClient(@PathVariable Long idClient) {
        return iServiceClient.deleteClient(idClient);
    }


/*
    @GetMapping(value="/getby/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Intervention> getInterventionById(@PathVariable(value = "id") Long interventionId)
            throws ResourceNotFoundException {
        Intervention intervention = interventionRepository.findById(interventionId)
                .orElseThrow(() -> new ResourceNotFoundException("Intervention not found for this id :: " + interventionId));
        System.out.println(intervention.toString());
        return ResponseEntity.ok().body(intervention);
    }
*/


}
