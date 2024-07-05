package com.billcomconsulting.sav.services.interfaces;


import com.billcomconsulting.sav.entities.InterventionStatus;

import java.util.List;

public interface IServiceInterventionStatus {

    public InterventionStatus addInterventionStatus(InterventionStatus interventionStatus);
    public List<InterventionStatus> getAllInterventionStatus();
    public  boolean deleteInterventionStatus(Long idInterventionStatus);
    public InterventionStatus updateInterventionStatus (Long idInterventionStatus) ;

}
