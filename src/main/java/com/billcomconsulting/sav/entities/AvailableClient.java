package com.billcomconsulting.sav.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Getter
@Setter
public class AvailableClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
    @Size(max = 20)
    private String shop;
    @Size(max = 20)
    private String comment;

    @ManyToOne
    Intervention intervention;

}
