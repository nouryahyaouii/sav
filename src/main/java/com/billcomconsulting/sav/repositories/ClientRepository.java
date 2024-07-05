package com.billcomconsulting.sav.repositories;

import com.billcomconsulting.sav.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
