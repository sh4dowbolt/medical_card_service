package com.suraev.medical_card_service.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="diseases")
public class Disease {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numberOfDisease")
    private String numberOfDisease;
    @Column(name ="start_disease")
    private LocalDate startDisease;
    @Column(name = "end_disease")
    private LocalDate endDisease;
    @Column(name = "prescription")
    private String prescription;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="disease_id", referencedColumnName = "id")
    private Patient patient;

}
