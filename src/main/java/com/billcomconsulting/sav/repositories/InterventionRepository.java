package com.billcomconsulting.sav.repositories;

import com.billcomconsulting.sav.entities.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InterventionRepository extends JpaRepository<Intervention,Long> {
    Optional<Intervention> findByImei(String imei);
    List<Intervention> findByClient_Cin(Long clientCin);
    List<Intervention> findByWorkflow(String workflow);
    List<Intervention> findByDischargeIsNotNull();
    Optional<Intervention> findByDischargeId(Long dischargeId);

}
