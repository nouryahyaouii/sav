package com.billcomconsulting.sav.services.interfaces;


import com.billcomconsulting.sav.entities.SwappStatus;

import java.util.List;

public interface IServiceSwappStatus {

    public SwappStatus addSwappStatus(SwappStatus swappStatus);
    public List<SwappStatus> getAllSwappStatus() ;
    public  boolean deleteSwappStatus(Long idSwappStatus) ;
    public SwappStatus updateSwappStatus (Long idSwappStatus) ;

}
