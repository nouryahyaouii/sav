package com.billcomconsulting.sav.services.interfaces;


import com.billcomconsulting.sav.entities.Swapp;

import java.util.List;

public interface IServiceSwapp {
    public Swapp addSwapp(Swapp swapp) ;
    public List<Swapp> getAllSwapp() ;
    public  boolean deleteSwapp(Long idSwapp) ;
    public Swapp updateSwapp (Long idSwapp) ;
}
