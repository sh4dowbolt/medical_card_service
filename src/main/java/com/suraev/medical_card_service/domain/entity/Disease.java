package com.suraev.medical_card_service.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name="diseases")
@Getter
@Setter
@Schema(description = "Сущность заболевания")
public class Disease {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор заболевания",example = "1")
    private Long id;

    @Column(name = "numberOfDisease")
    @Schema(description = "Код заболевания",example = "A01.0")
    private String numberOfDisease;

    @Column(name ="start_disease")
    @Schema(description = "Дата начала заболевания",example = "01-01-2024")
    private LocalDate startDisease;

    @Column(name = "end_disease")
    @Schema(description = "Дата окончания заболевания",example = "01-01-2024")
    private LocalDate endDisease;

    @Column(name = "prescription", length = 1024)
    @Schema(description = "Назначение",example = "Примите анальгин")
    private String prescription;

    @ManyToOne()
    @JoinColumn(name="patient_id", referencedColumnName = "id")
    @Schema(description = "Идентификатор пациента",example = "1")
    private Patient patient;

}
