package com.billcomconsulting.sav.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imei;
    private String guarante;
    private LocalDate guarantee;
    @Size(max = 20)
    private String nbRetourSav;
    @Size(max = 20)
    private String status;
    @Size(max = 20)
    private String batteryImei;
    @Size(max = 20)
    private String brand;
    @Size(max = 20)
    private String model;
    @Size(max = 20)
    private String terminal;

    @Temporal(value=TemporalType.DATE)
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private LocalDate purchaseDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="device")
    @JsonIgnore
    private Set<Intervention> Interventions;

}
