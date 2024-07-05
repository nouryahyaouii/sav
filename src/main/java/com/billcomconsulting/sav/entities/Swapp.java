package com.billcomconsulting.sav.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Swapp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
    @Size(max = 30)
    private String imei;
    @Size(max = 20)
    private String brand;
    @Size(max = 20)
    private String model;
    @Size(max = 20)
    private String price;
    @Size(max = 20)
    private String status;
    @ManyToOne
    Intervention intervention;

}
