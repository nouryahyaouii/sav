package com.billcomconsulting.sav.services.implementation;


import com.billcomconsulting.sav.entities.AvailableClient;
import com.billcomconsulting.sav.repositories.AvailableClientRepository;
import com.billcomconsulting.sav.services.interfaces.IServiceAvailableClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class AvailableClientService implements IServiceAvailableClient {


    AvailableClientRepository availableclientRepository ;

    @Override
    public AvailableClient addAvailableClient(AvailableClient availableclient) {


        log.info("AvailableClient was successfully added !");
        return availableclientRepository.save(availableclient);
    }

    @Override
    public List<AvailableClient> getAllAvailableClient() {return availableclientRepository.findAll();}




    @Override
    public boolean deleteAvailableClient(Long idAvailableClient) {
        AvailableClient existingAvailableClient = availableclientRepository.findById(idAvailableClient).orElse(null);
        if(existingAvailableClient!=null) {
            availableclientRepository.delete(existingAvailableClient);
            log.info("AvailableClient deleted");
            return true ;
        }
        log.info(" this AvailableClient is not existing");
        return false;
    }


    @Override
    public AvailableClient updateAvailableClient(Long idAvailableClient) {
        AvailableClient availableclient = availableclientRepository.findById(idAvailableClient).orElse(null);
        if (availableclient != null) {
            availableclientRepository.save(availableclient);
            log.info("AvailableClient was successfully updated !");
        }
        log.info("AvailableClient not found !");
        return  availableclient;

    }




}
