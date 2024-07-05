package com.billcomconsulting.sav.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import java.util.Set;

@Entity
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cin;
    @Size(max = 20)
    private String firstName;
    @Size(max = 20)
    private String lastName;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @Size(max = 8)
    @Size(min = 8)
    @NumberFormat
    private String phoneNumber1;
    @NotBlank
    @Size(max = 8)
    @Size(min = 8)
    @NumberFormat
    private String phoneNumber2;


    @OneToMany(cascade = CascadeType.ALL, mappedBy="client")
    private Set<Intervention> Interventions;


}
