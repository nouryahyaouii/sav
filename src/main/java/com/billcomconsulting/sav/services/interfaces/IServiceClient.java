package com.billcomconsulting.sav.services.interfaces;


import com.billcomconsulting.sav.entities.Client;

import java.util.List;

public interface IServiceClient {

    public Client addClient(Client client) ;
    public List<Client> getAllClient() ;
    public  boolean deleteClient(Long idClient) ;
    public Client updateClient (Long idClient) ;

}
