package com.billcomconsulting.sav.services.implementation;


import com.billcomconsulting.sav.entities.Discharge;
import com.billcomconsulting.sav.repositories.DischargeRepository;
import com.billcomconsulting.sav.services.interfaces.IServiceDischarge;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class DischargeService implements IServiceDischarge {

    DischargeRepository dischargeRepository ;

    @Override
    public Discharge addDischarge(Discharge discharge) {


        log.info("Discharge was successfully added !");
        return dischargeRepository.save(discharge);
    }

    @Override
    public List<Discharge> getAllDischarge() {return dischargeRepository.findAll();}




    @Override
    public boolean deleteDischarge(Long idDischarge) {
        Discharge existingDischarge = dischargeRepository.findById(idDischarge).orElse(null);
        if(existingDischarge!=null) {
            dischargeRepository.delete(existingDischarge);
            log.info("Discharge deleted");
            return true ;
        }
        log.info(" this Discharge is not existing");
        return false;
    }


    @Override
    public Discharge updateDischarge(Long idDischarge) {
        Discharge discharge = dischargeRepository.findById(idDischarge).orElse(null);
        if (discharge != null) {
            dischargeRepository.save(discharge);
            log.info("Discharge was successfully updated !");
        }
        log.info("Discharge not found !");
        return  discharge;

    }




}
