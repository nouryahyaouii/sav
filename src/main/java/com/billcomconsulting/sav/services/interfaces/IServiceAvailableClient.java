package com.billcomconsulting.sav.services.interfaces;

import com.billcomconsulting.sav.entities.AvailableClient;

import java.util.List;

public interface IServiceAvailableClient {

    public AvailableClient addAvailableClient(AvailableClient availableclient);
    public List<AvailableClient> getAllAvailableClient() ;
    public  boolean deleteAvailableClient(Long idAvailableClient) ;
    public AvailableClient updateAvailableClient (Long idAvailableClient) ;

}
