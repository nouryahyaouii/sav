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
public class Discharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
    @Size(max = 30)
    private String destination;
    @JsonIgnore
    private LocalDateTime createdAt;


//    @ManyToOne
//    User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="discharge")
    private Set<Intervention> Interventions;


}
