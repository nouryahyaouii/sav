package com.billcomconsulting.sav.services.implementation;


import com.billcomconsulting.sav.entities.InterventionStatus;
import com.billcomconsulting.sav.repositories.InterventionStatusRepository;
import com.billcomconsulting.sav.services.interfaces.IServiceInterventionStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class InterventionStatusService implements IServiceInterventionStatus {

    InterventionStatusRepository interventionStatusRepository ;

    @Override
    public InterventionStatus addInterventionStatus(InterventionStatus interventionStatus) {


        log.info("InterventionStatus was successfully added !");
        return interventionStatusRepository.save(interventionStatus);
    }

    @Override
    public List<InterventionStatus> getAllInterventionStatus() {return interventionStatusRepository.findAll();}




    @Override
    public boolean deleteInterventionStatus(Long idInterventionStatus) {
        InterventionStatus existingInterventionStatus = interventionStatusRepository.findById(idInterventionStatus).orElse(null);
        if(existingInterventionStatus!=null) {
            interventionStatusRepository.delete(existingInterventionStatus);
            log.info("InterventionStatus deleted");
            return true ;
        }
        log.info(" this InterventionStatus is not existing");
        return false;
    }


    @Override
    public InterventionStatus updateInterventionStatus(Long idInterventionStatus) {
        InterventionStatus interventionStatus = interventionStatusRepository.findById(idInterventionStatus).orElse(null);
        if (interventionStatus != null) {
            interventionStatusRepository.save(interventionStatus);
            log.info("InterventionStatus was successfully updated !");
        }
        log.info("InterventionStatus not found !");
        return  interventionStatus;

    }




}
