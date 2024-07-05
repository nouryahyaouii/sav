package com.billcomconsulting.sav.services.implementation;


import com.billcomconsulting.sav.entities.Client;
import com.billcomconsulting.sav.repositories.ClientRepository;
import com.billcomconsulting.sav.services.interfaces.IServiceClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ClientService implements IServiceClient {

    ClientRepository clientRepository ;

    @Override
    public Client addClient(Client client) {


        log.info("Client was successfully added !");
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getAllClient() {return clientRepository.findAll();}




    @Override
    public boolean deleteClient(Long idClient) {
        Client existingClient = clientRepository.findById(idClient).orElse(null);
        if(existingClient!=null) {
            clientRepository.delete(existingClient);
            log.info("Client deleted");
            return true ;
        }
        log.info(" this Client is not existing");
        return false;
    }


    @Override
    public Client updateClient(Long idClient) {
        Client client = clientRepository.findById(idClient).orElse(null);
        if (client != null) {
            clientRepository.save(client);
            log.info("Client was successfully updated !");
        }
        log.info("Client not found !");
        return  client;

    }



}
