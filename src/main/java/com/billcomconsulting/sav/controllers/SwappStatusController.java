package com.billcomconsulting.sav.controllers;

import com.billcomconsulting.sav.entities.SwappStatus;
import com.billcomconsulting.sav.exceptions.ResourceNotFoundException;
import com.billcomconsulting.sav.repositories.SwappStatusRepository;
import com.billcomconsulting.sav.services.interfaces.IServiceSwappStatus;
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
public class SwappStatusController {

    private SwappStatusRepository swappStatusRepository;
    IServiceSwappStatus iServiceSwappStatus;


    @GetMapping(value = "/getallSwappStatuss", consumes = MediaType.ALL_VALUE, produces = "application/json")
    public List<SwappStatus> getAllSwappStatus() {
        return swappStatusRepository.findAll();
    }


    @PostMapping("/addSwappStatus")
    @ResponseBody
    public ResponseEntity<?> createSwappStatus(@RequestBody SwappStatus swappStatus) throws IOException {

        SwappStatus savedswappStatus = iServiceSwappStatus.addSwappStatus(swappStatus);
        return ResponseEntity.ok(savedswappStatus);
    }


    @PutMapping(value = "/UPDATE_SwappStatus/{id}", consumes = MediaType.ALL_VALUE, produces = "application/json")
    @ResponseBody
    public ResponseEntity<SwappStatus> updateSwappStatus(@PathVariable long id, @RequestParam(required = false) String status, @RequestParam(required = false) String comment

    ) throws Exception {


        String sswappStatus = "{    \"status\": \"" + status + "\",   \"comment\": \"" + comment + "\" }";

        SwappStatus ogswappStatus = swappStatusRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("SwappStatus not found for this id :: " + id));

        System.out.println(ogswappStatus);

        ObjectMapper objectMapper = new ObjectMapper();
        SwappStatus swappStatus = objectMapper.readValue(sswappStatus, SwappStatus.class);


        if (status != null) {
            ogswappStatus.setStatus(swappStatus.getStatus());
        }

        if (comment != null) {
            ogswappStatus.setComment(swappStatus.getComment());
        }


        iServiceSwappStatus.addSwappStatus(ogswappStatus);

        return ResponseEntity.ok(ogswappStatus);
    }


    @DeleteMapping(value = "/deleteSwappStatus/{idSwappStatus}", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public boolean deleteSwappStatus(@PathVariable Long idSwappStatus) {
        return iServiceSwappStatus.deleteSwappStatus(idSwappStatus);
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
