package com.billcomconsulting.sav.controllers;


import com.billcomconsulting.sav.entities.Swapp;
import com.billcomconsulting.sav.exceptions.ResourceNotFoundException;
import com.billcomconsulting.sav.repositories.SwappRepository;
import com.billcomconsulting.sav.services.interfaces.IServiceSwapp;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user", produces = "application/json", consumes = "application/json")
@AllArgsConstructor
public class SwappController {
    private SwappRepository swappRepository;
    IServiceSwapp iServiceSwapp;


    @GetMapping(value = "/getallSwapps", consumes = MediaType.ALL_VALUE, produces = "application/json")
    public List<Swapp> getAllSwapp() {
        return swappRepository.findAll();
    }


    @PostMapping("/addSwapp")
    @ResponseBody
    public ResponseEntity<?> createSwapp(@RequestBody Swapp swapp) throws IOException {

        Swapp savedswapp = iServiceSwapp.addSwapp(swapp);
        return ResponseEntity.ok(savedswapp);
    }


    @PutMapping(value = "/UPDATE_Swapp/{id}", consumes = MediaType.ALL_VALUE, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Swapp> updateSwapp(@PathVariable long id, @RequestParam(required = false) String imei, @RequestParam(required = false) String brand, @RequestParam(required = false) String model, @RequestParam(required = false) String price, @RequestParam(required = false) String status) throws Exception {


        String sswapp = "{\"imei\": \"" + imei + "\",   \"brand\": \"" + brand + "\",   \"model\": \"" + model + "\",   \"price\": \"" + price + "\",   \"status\": \"" + status + "\" }";

        Swapp ogswapp = swappRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Swapp not found for this id :: " + id));

        System.out.println(ogswapp);

        ObjectMapper objectMapper = new ObjectMapper();
        Swapp swapp = objectMapper.readValue(sswapp, Swapp.class);


        if (imei != null) {
            ogswapp.setImei(swapp.getImei());
        }

        if (brand != null) {
            ogswapp.setBrand(swapp.getBrand());
        }

        if (model != null) {
            ogswapp.setModel(swapp.getModel());
        }


        if (price != null) {
            ogswapp.setPrice(swapp.getPrice());
        }

        if (status != null) {
            ogswapp.setStatus(swapp.getStatus());
        }


        iServiceSwapp.addSwapp(ogswapp);

        return ResponseEntity.ok(ogswapp);
    }


    @DeleteMapping(value = "/deleteSwapp/{idSwapp}", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public boolean deleteSwapp(@PathVariable Long idSwapp) {
        return iServiceSwapp.deleteSwapp(idSwapp);
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
