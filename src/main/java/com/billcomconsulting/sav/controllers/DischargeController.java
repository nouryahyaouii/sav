package com.billcomconsulting.sav.controllers;

import com.billcomconsulting.sav.entities.Discharge;
import com.billcomconsulting.sav.exceptions.ResourceNotFoundException;
import com.billcomconsulting.sav.repositories.DischargeRepository;
import com.billcomconsulting.sav.services.interfaces.IServiceDischarge;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(value = "/api/discharge", produces = "application/json", consumes = "application/json")
@AllArgsConstructor
public class DischargeController {

    private DischargeRepository dischargeRepository;
    IServiceDischarge iServiceDischarge;


    @GetMapping(value = "/getallDischarges", consumes = MediaType.ALL_VALUE, produces = "application/json")
    public List<Discharge> getAllDischarge() {
        return dischargeRepository.findAll();
    }


    @PostMapping("/addDischarge")
    @ResponseBody
    public ResponseEntity<?> createDischarge(@RequestBody Discharge discharge) throws IOException {

        Discharge saveddischarge = iServiceDischarge.addDischarge(discharge);
        return ResponseEntity.ok(saveddischarge);
    }


    @PutMapping(value = "/UPDATE_Discharge/{id}", consumes = MediaType.ALL_VALUE, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Discharge> updateDischarge(@PathVariable long id, @RequestParam(required = false) String destination) throws Exception {


        String ddischarge = "{\"destination\": \"" + destination + "\"  }";

        Discharge ogdischarge = dischargeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Discharge not found for this id :: " + id));

        System.out.println(ogdischarge);

        ObjectMapper objectMapper = new ObjectMapper();
        Discharge discharge = objectMapper.readValue(ddischarge, Discharge.class);


        if (destination != null) {
            ogdischarge.setDestination(discharge.getDestination());
        }

        iServiceDischarge.addDischarge(ogdischarge);

        return ResponseEntity.ok(ogdischarge);
    }


    @DeleteMapping(value = "/deleteDischarge/{idDischarge}", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public boolean deleteDischarge(@PathVariable Long idDischarge) {
        return iServiceDischarge.deleteDischarge(idDischarge);
    }


    @GetMapping(value = "/getDischargeby/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Discharge> getDischargeById(@PathVariable(value = "id") Long dischargeId) throws ResourceNotFoundException {
        Discharge discharge = dischargeRepository.findById(dischargeId).orElseThrow(() -> new ResourceNotFoundException("sischarge not found for this id :: " + dischargeId));
        System.out.println(discharge.toString());
        return ResponseEntity.ok().body(discharge);
    }


}
