package com.billcomconsulting.sav.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class SwappStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
    @Size(max = 20)
    private String status;
    @Size(max = 30)
    private String comment;

    @JsonIgnore
    private LocalDate createdAt;

    @ManyToOne
    Intervention intervention;

}
