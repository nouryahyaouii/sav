package com.billcomconsulting.sav.controllers;


import com.billcomconsulting.sav.entities.InterventionStatus;
import com.billcomconsulting.sav.exceptions.ResourceNotFoundException;
import com.billcomconsulting.sav.repositories.InterventionStatusRepository;
import com.billcomconsulting.sav.services.interfaces.IServiceInterventionStatus;
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
public class InterventionStatusController {

    private InterventionStatusRepository interventionStatusRepository;
    IServiceInterventionStatus iServiceInterventionStatus;


    @GetMapping(value = "/getallInterventionStatuss", consumes = MediaType.ALL_VALUE, produces = "application/json")
    public List<InterventionStatus> getAllInterventionStatus() {
        return interventionStatusRepository.findAll();
    }


    @PostMapping("/addInterventionStatus")
    @ResponseBody
    public ResponseEntity<?> createInterventionStatus(@RequestBody InterventionStatus interventionStatus) throws IOException {

        InterventionStatus savedinterventionStatus = iServiceInterventionStatus.addInterventionStatus(interventionStatus);
        return ResponseEntity.ok(savedinterventionStatus);
    }


    @PutMapping(value = "/UPDATE_InterventionStatus/{id}", consumes = MediaType.ALL_VALUE, produces = "application/json")
    @ResponseBody
    public ResponseEntity<InterventionStatus> updateInterventionStatus(@PathVariable long id, @RequestParam(required = false) String status, @RequestParam(required = false) String local, @RequestParam(required = false) String amount, @RequestParam(required = false) String marque, @RequestParam(required = false) String modele, @RequestParam(required = false) String observation, @RequestParam(required = false) String newIMEI, @RequestParam(required = false) String pdfLink) throws Exception {


        String iinterventionStatus = "{\"status\": \"" + status + "\",   \"local\": \"" + local + "\",   \"amount\": \"" + amount + "\",   \"marque\": \"" + marque + "\",   \"modele\": \"" + modele + "\",   \"observation\": \"" + observation + "\" ,   \"newIMEI\": \"" + newIMEI + "\" ,   \"pdfLink\": \"" + pdfLink + "\" }";

        InterventionStatus oginterventionStatus = interventionStatusRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("InterventionStatus not found for this id :: " + id));

        System.out.println(oginterventionStatus);

        ObjectMapper objectMapper = new ObjectMapper();
        InterventionStatus interventionStatus = objectMapper.readValue(iinterventionStatus, InterventionStatus.class);


        if (status != null) {
            oginterventionStatus.setStatus(interventionStatus.getStatus());
        }

        if (local != null) {
            oginterventionStatus.setLocal(interventionStatus.getLocal());
        }

        if (amount != null) {
            oginterventionStatus.setAmount(interventionStatus.getAmount());
        }


        if (marque != null) {
            oginterventionStatus.setMarque(interventionStatus.getMarque());
        }

        if (modele != null) {
            oginterventionStatus.setModele(interventionStatus.getModele());
        }

        if (observation != null) {
            oginterventionStatus.setObservation(interventionStatus.getObservation());
        }

        if (newIMEI != null) {
            oginterventionStatus.setNewIMEI(interventionStatus.getNewIMEI());
        }

        if (pdfLink != null) {
            oginterventionStatus.setPdfLink(interventionStatus.getPdfLink());
        }


        iServiceInterventionStatus.addInterventionStatus(oginterventionStatus);

        return ResponseEntity.ok(oginterventionStatus);
    }


    @DeleteMapping(value = "/deleteInterventionStatus/{idInterventionStatus}", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public boolean deleteInterventionStatus(@PathVariable Long idInterventionStatus) {
        return iServiceInterventionStatus.deleteInterventionStatus(idInterventionStatus);
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
