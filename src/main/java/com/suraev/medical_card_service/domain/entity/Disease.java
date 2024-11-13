package com.suraev.medical_card_service.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name="diseases")
@Getter
@Setter
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
    @Column(name = "prescription", length = 1024)
    private String prescription;

    @ManyToOne()
    @JoinColumn(name="patient_id", referencedColumnName = "id")
    private Patient patient;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Disease disease = (Disease) object;
        return Objects.equals(id, disease.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
