package com.billcomconsulting.sav.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class InterventionStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 20)
    private String status;
    @Size(max = 20)
    private String local;
    @Size(max = 20)
    private String amount;
    @Size(max = 20)
    private String marque;
    @Size(max = 20)
    private String modele;
    @Size(max = 50)
    private String observation;
    @Size(max = 20)
    private String newIMEI;
    @Size(max = 50)
    private String pdfLink;

    private LocalDate createdAt;

    @ManyToOne
    Intervention intervention;

}
