package com.billcomconsulting.sav.services.implementation;


import com.billcomconsulting.sav.entities.Swapp;
import com.billcomconsulting.sav.repositories.SwappRepository;
import com.billcomconsulting.sav.services.interfaces.IServiceSwapp;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class SwappService implements IServiceSwapp {

    SwappRepository swappRepository;

    @Override
    public Swapp addSwapp(Swapp swapp) {


        log.info("Swapp was successfully added !");
        return swappRepository.save(swapp);
    }

    @Override
    public List<Swapp> getAllSwapp() {
        return swappRepository.findAll();
    }


    @Override
    public boolean deleteSwapp(Long idSwapp) {
        Swapp existingSwapp = swappRepository.findById(idSwapp).orElse(null);
        if (existingSwapp != null) {
            swappRepository.delete(existingSwapp);
            log.info("Swapp deleted");
            return true;
        }
        log.info(" this Swapp is not existing");
        return false;
    }


    @Override
    public Swapp updateSwapp(Long idSwapp) {
        Swapp swapp = swappRepository.findById(idSwapp).orElse(null);
        if (swapp != null) {
            swappRepository.save(swapp);
            log.info("Swapp was successfully updated !");
        }
        log.info("Swapp not found !");
        return swapp;

    }


}
