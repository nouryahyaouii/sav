package com.billcomconsulting.sav.services.implementation;


import com.billcomconsulting.sav.entities.SwappStatus;
import com.billcomconsulting.sav.repositories.SwappStatusRepository;
import com.billcomconsulting.sav.services.interfaces.IServiceSwappStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j

public class SwappStatusService implements IServiceSwappStatus {

    SwappStatusRepository swappStatusRepository ;

    @Override
    public SwappStatus addSwappStatus(SwappStatus swappStatus) {


        log.info("SwappStatus was successfully added !");
        return swappStatusRepository.save(swappStatus);
    }

    @Override
    public List<SwappStatus> getAllSwappStatus() {return swappStatusRepository.findAll();}




    @Override
    public boolean deleteSwappStatus(Long idSwappStatus) {
        SwappStatus existingSwappStatus = swappStatusRepository.findById(idSwappStatus).orElse(null);
        if(existingSwappStatus!=null) {
            swappStatusRepository.delete(existingSwappStatus);
            log.info("SwappStatus deleted");
            return true ;
        }
        log.info(" this SwappStatus is not existing");
        return false;
    }


    @Override
    public SwappStatus updateSwappStatus(Long idSwappStatus) {
        SwappStatus swappStatus = swappStatusRepository.findById(idSwappStatus).orElse(null);
        if (swappStatus != null) {
            swappStatusRepository.save(swappStatus);
            log.info("SwappStatus was successfully updated !");
        }
        log.info("SwappStatus not found !");
        return  swappStatus;

    }




}
