package com.billcomconsulting.sav.services.interfaces;


import com.billcomconsulting.sav.entities.Intervention;

import java.util.List;
import java.util.Optional;

public interface IServiceIntervention {

    public Intervention addIntervention(Intervention intervention);
    public List<Intervention> getAllIntervention() ;
    public  boolean deleteIntervention(Long idIntervention) ;
    public Intervention updateIntervention (Long idIntervention) ;
    List<Intervention> getInterventionsWithInterneWorkflow();
    List<Intervention> getInterventionsWithExterneWorkflow();
    List<Intervention> getInterventionsByClientCin(Long clientCin);
    public String getRepairType(Intervention intervention);
    public Intervention getInterventionById(Long id);
    public List<Intervention> findByDischargeIsNotNull();
    public Optional<Intervention> getInterventionByDischargeId(Long dischargeId);

}
