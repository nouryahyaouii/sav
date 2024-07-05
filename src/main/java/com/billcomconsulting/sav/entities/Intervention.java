package com.billcomconsulting.sav.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
public class Intervention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 30)
    private String imei;
    @Size(max = 50)
    private String panneType;
    @Size(max = 50)
    private String accessories;
    @Size(max = 40)
    private String description;
    @Size(max = 40)
    private String workflow;
    @Size(max = 40)
    private String status;

    @Size(max = 40)
    private String etatTerminal;
    @Size(max = 40)
    private String terminalDePret;
    @JsonIgnore
    private LocalDateTime createdAt;

    @ManyToOne
    @JsonIgnore
    Discharge discharge;

    @ManyToOne
    @JsonIgnore
    Client client;

    @ManyToOne
    @JsonIgnore
    Device device;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="intervention")
    private Set<AvailableClient> AvailableClients;


    @OneToMany(cascade = CascadeType.ALL, mappedBy="intervention")
    private Set<Swapp> Swapps;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="intervention")
    private Set<SwappStatus> SwappStatuss;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="intervention")
    private Set<InterventionStatus> InterventionStatuss;


    public String getRepairType() {
        if ("externe".equalsIgnoreCase(workflow)) {
            return "En attente Envoi Réparateur externe";
        } else if ("interne".equalsIgnoreCase(workflow)) {
            return "En attente Envoi Réparateur interne";
        } else {
            return "En attente Envoi Workflow normal"; // Assuming "normal" or other cases should return a default value
        }
    }

    protected void Create() {
        if (etatTerminal == null) {
            etatTerminal = "en cours de diagnostic";
        }
        if (status == null) {
            status = "ouverte";
        }
    }

}

