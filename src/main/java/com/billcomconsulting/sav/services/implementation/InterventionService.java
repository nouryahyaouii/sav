package com.billcomconsulting.sav.services.implementation;


import com.billcomconsulting.sav.entities.Intervention;
import com.billcomconsulting.sav.repositories.InterventionRepository;
import com.billcomconsulting.sav.services.interfaces.IServiceIntervention;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor

public class InterventionService implements IServiceIntervention {

    InterventionRepository interventionRepository ;

    @Override
    public Intervention addIntervention(Intervention intervention) {


        log.info("Intervention was successfully added !");
        return interventionRepository.save(intervention);
    }

    @Override
    public List<Intervention> getAllIntervention() {return interventionRepository.findAll();}




    @Override
    public boolean deleteIntervention(Long idIntervention) {
        Intervention existingIntervention = interventionRepository.findById(idIntervention).orElse(null);
        if(existingIntervention!=null) {
            interventionRepository.delete(existingIntervention);
            log.info("Intervention deleted");
            return true ;
        }
        log.info(" this Intervention is not existing");
        return false;
    }


    @Override
    public Intervention updateIntervention(Long idIntervention) {
        Intervention intervention = interventionRepository.findById(idIntervention).orElse(null);
        if (intervention != null) {
            interventionRepository.save(intervention);
            log.info("Intervention was successfully updated !");
        }
        log.info("Intervention not found !");
        return  intervention;

    }

    @Override
    public List<Intervention> getInterventionsWithInterneWorkflow() {
        String OverFlow="interne";
        return interventionRepository.findByWorkflow(OverFlow);
    }

    @Override
    public List<Intervention> getInterventionsWithExterneWorkflow() {
        String OverFlow="externe";
        return interventionRepository.findByWorkflow(OverFlow);
    }
    @Override
    public List<Intervention> getInterventionsByClientCin(Long clientCin) {
        return interventionRepository.findByClient_Cin(clientCin);
    }
    @Override
    public Intervention getInterventionById(Long id) {
        return interventionRepository.findById(id).orElse(null);
    }
    @Override
    public String getRepairType(Intervention intervention) {
        String workflow = intervention.getWorkflow();
        if ("externe".equalsIgnoreCase(workflow)) {
            return "En attente Envoi Réparateur externe";
        } else if ("interne".equalsIgnoreCase(workflow)) {
            return "En attente Envoi Réparateur interne";
        } else {
            return "En attente Envoi Workflow normal"; // Assuming "normal" or other cases should return a default value
        }
    }

    @Override
    public List<Intervention> findByDischargeIsNotNull() {
        return interventionRepository.findByDischargeIsNotNull();
    }
    @Override
    public Optional<Intervention> getInterventionByDischargeId(Long dischargeId) {
        return interventionRepository.findByDischargeId(dischargeId);
    }

}
