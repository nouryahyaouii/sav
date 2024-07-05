package com.billcomconsulting.sav.services.interfaces;


import com.billcomconsulting.sav.entities.Discharge;

import java.util.List;

public interface IServiceDischarge {

    public Discharge addDischarge(Discharge discharge);
    public List<Discharge> getAllDischarge() ;
    public  boolean deleteDischarge(Long idDischarge) ;
    public Discharge updateDischarge (Long idDischarge) ;

}
