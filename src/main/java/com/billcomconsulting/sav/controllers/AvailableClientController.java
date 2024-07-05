package com.billcomconsulting.sav.controllers;


import com.billcomconsulting.sav.entities.AvailableClient;
import com.billcomconsulting.sav.exceptions.ResourceNotFoundException;
import com.billcomconsulting.sav.repositories.AvailableClientRepository;
import com.billcomconsulting.sav.services.interfaces.IServiceAvailableClient;
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
public class AvailableClientController {

    AvailableClientRepository availableclientRepository;
    IServiceAvailableClient iServiceAvailableClient;


    @GetMapping(value = "/getallAvailableClients", consumes = MediaType.ALL_VALUE, produces = "application/json")
    public List<AvailableClient> getAllAvailableClient() {
        return availableclientRepository.findAll();
    }


    @PostMapping("/addAvailableClient")
    @ResponseBody
    public ResponseEntity<?> createAvailableClient(@RequestBody AvailableClient availableclient) throws IOException {

        AvailableClient savedavailableclient = iServiceAvailableClient.addAvailableClient(availableclient);
        return ResponseEntity.ok(savedavailableclient);
    }


    @PutMapping(value = "/UPDATE_AvailableClient/{id}", consumes = MediaType.ALL_VALUE, produces = "application/json")
    @ResponseBody
    public ResponseEntity<AvailableClient> updateAvailableClient(@PathVariable long id, @RequestParam(required = false) String shop, @RequestParam(required = false) String comment

    ) throws Exception {


        String aavailableclient = "{\"shop\": \"" + shop + "\",   \"comment\": \"" + comment + "\" }";

        AvailableClient ogavailableclient = availableclientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + id));

        System.out.println(ogavailableclient);

        ObjectMapper objectMapper = new ObjectMapper();
        AvailableClient availableclient = objectMapper.readValue(aavailableclient, AvailableClient.class);


        if (shop != null) {
            ogavailableclient.setShop(availableclient.getShop());
        }

        if (comment != null) {
            ogavailableclient.setComment(availableclient.getComment());
        }


        iServiceAvailableClient.addAvailableClient(ogavailableclient);

        return ResponseEntity.ok(ogavailableclient);
    }


    @DeleteMapping(value = "/deleteAvailableClient/{idAvailableClient}", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public boolean deleteAvailableClient(@PathVariable Long idAvailableClient) {
        return iServiceAvailableClient.deleteAvailableClient(idAvailableClient);
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
