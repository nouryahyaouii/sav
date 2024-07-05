package com.billcomconsulting.sav.controllers;


import com.billcomconsulting.sav.entities.Intervention;
import com.billcomconsulting.sav.exceptions.ResourceNotFoundException;
import com.billcomconsulting.sav.repositories.InterventionRepository;
import com.billcomconsulting.sav.services.interfaces.IServiceIntervention;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/intervention", produces = "application/json", consumes = "application/json")
@AllArgsConstructor
public class InterventionController {

    private InterventionRepository interventionRepository;
    IServiceIntervention iServiceIntervention;


    @GetMapping(value = "/getallInterventions", consumes = MediaType.ALL_VALUE, produces = "application/json")
    public List<Intervention> getAllIntervention() {
        return interventionRepository.findAll();
    }


    @PostMapping("/addIntervention")
    @ResponseBody
    public ResponseEntity<?> createIntervention(@RequestBody Intervention intervention) throws IOException {

        Intervention savedintervention = iServiceIntervention.addIntervention(intervention);
        return ResponseEntity.ok(savedintervention);
    }

    /*
        @PutMapping(value="/update/{idIntervention}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
        @ResponseBody
        public Intervention updateIntervention(@PathVariable Long idIntervention )  {return  iServiceIntervention.updateIntervention(idIntervention );}
    */ List<Intervention> interventions = new ArrayList<>();


    @PutMapping(value = "/UPDATE_Intervention/{id}", consumes = MediaType.ALL_VALUE, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Intervention> updateIntervention(@PathVariable long id, @RequestParam(required = false) String imei, @RequestParam(required = false) String panneType, @RequestParam(required = false) String accessories, @RequestParam(required = false) String description, @RequestParam(required = false) String workflow, @RequestParam(required = false) String etatTerminal, @RequestParam(required = false) String terminalDePret, @RequestParam(required = false) String status) throws Exception {


        String iintervention = "{\"imei\": \"" + imei + "\",   \"panneType\": \"" + panneType + "\",   \"accessories\": \"" + accessories + "\",   \"description\": \"" + description + "\",   \"workflow\": \"" + workflow + "\",   \"status\": \"" + status + "\",   \"etatTerminal\": \"" + etatTerminal + "\",   \"terminalDePret\": \"" + terminalDePret + "\" }";

        Intervention ogintervention = interventionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Intervention not found for this id :: " + id));

        System.out.println(ogintervention);

        ObjectMapper objectMapper = new ObjectMapper();
        Intervention intervention = objectMapper.readValue(iintervention, Intervention.class);


        if (imei != null) {
            ogintervention.setImei(intervention.getImei());
        }

        if (panneType != null) {
            ogintervention.setPanneType(intervention.getPanneType());
        }

        if (accessories != null) {
            ogintervention.setAccessories(intervention.getAccessories());
        }


        if (description != null) {
            ogintervention.setDescription(intervention.getDescription());
        }

        if (workflow != null) {
            ogintervention.setWorkflow(intervention.getWorkflow());
        }

        if (status != null) {
            ogintervention.setStatus(intervention.getStatus());
        }
        if (etatTerminal != null) {
            ogintervention.setEtatTerminal(intervention.getEtatTerminal());
        }
        if (terminalDePret != null) {
            ogintervention.setTerminalDePret(intervention.getTerminalDePret());
        }


        iServiceIntervention.addIntervention(ogintervention);

        return ResponseEntity.ok(ogintervention);
    }


    @DeleteMapping(value = "/deleteIntervention/{idIntervention}", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public boolean deleteIntervention(@PathVariable Long idIntervention) {
        return iServiceIntervention.deleteIntervention(idIntervention);
    }


    @GetMapping(value = "/interne", consumes = MediaType.ALL_VALUE, produces = "application/json")
    public List<Intervention> getInterventionsWithInterneWorkflow() {
        interventions = iServiceIntervention.getInterventionsWithInterneWorkflow();
        System.out.println("intervention" + interventions);
        return interventions;
    }

    @GetMapping(value = "/externe", consumes = MediaType.ALL_VALUE, produces = "application/json")
    public List<Intervention> getInterventionsWithExterneWorkflow() {
        interventions = iServiceIntervention.getInterventionsWithExterneWorkflow();
        System.out.println("intervention" + interventions);
        return interventions;
    }

    @GetMapping(value = "/getInterventionbyImei/{imei}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Intervention> getInterventionByImei(@PathVariable(value = "imei") String imei) throws ResourceNotFoundException {
        Intervention intervention = interventionRepository.findByImei(imei).orElseThrow(() -> new ResourceNotFoundException("Intervention not found for this IMEI :: " + imei));
        return ResponseEntity.ok().body(intervention);
    }

    @GetMapping(value = "/by-client-cin/{clientCin}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<List<Intervention>> getInterventionsByClientCin(@PathVariable("clientCin") BigInteger clientCin) {
        // Convert clientCin from BigInteger to Long
        Long clientCinLong = clientCin.longValue();

        List<Intervention> interventions = iServiceIntervention.getInterventionsByClientCin(clientCinLong);
        if (interventions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(interventions, HttpStatus.OK);
    }


    @GetMapping(value = "/getInterventionby/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Intervention> getInterventionById(@PathVariable(value = "id") Long interventionId) throws ResourceNotFoundException {
        Intervention intervention = interventionRepository.findById(interventionId).orElseThrow(() -> new ResourceNotFoundException("intervention not found for this id :: " + interventionId));
        System.out.println(intervention.toString());
        return ResponseEntity.ok().body(intervention);
    }

    @GetMapping(value = "/getRepairType/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<String> getRepairType(@PathVariable("id") Long id) {
        if (id == null || id <= 0) {
            return new ResponseEntity<>("Invalid id", HttpStatus.BAD_REQUEST);
        }
        Intervention intervention = iServiceIntervention.getInterventionById(id);
        if (intervention == null) {
            return new ResponseEntity<>("Intervention not found", HttpStatus.NOT_FOUND);
        }
        String repairType = iServiceIntervention.getRepairType(intervention);
        return new ResponseEntity<>(repairType, HttpStatus.OK);
    }

    @GetMapping(value = "/getInterventionWithDecharge", consumes = MediaType.ALL_VALUE)
    public List<Intervention> getAllInterventionsWithDischarge() {
        return iServiceIntervention.findByDischargeIsNotNull();
    }

    @GetMapping(value = "/getInterventionByDechargeId/{dischargeId}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Intervention> getInterventionByDischargeId(@PathVariable Long dischargeId) {
        Optional<Intervention> intervention = iServiceIntervention.getInterventionByDischargeId(dischargeId);
        return intervention.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
/*
    @GetMapping(value="/getExterneInterventions", produces = "application/json")
    public List<Intervention> getExterneInterventions(@RequestParam(value = "workflow", defaultValue = "externe") String workflow) {
        return interventionRepository.findAllExterneInterventions();
    }
*/

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
