package com.suraev.medical_card_service.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@Table(name = "codeDiseases")
public class CodeDisease {
    @Id
    @Column(name="id")
    private String id;
    @Column(name = "title_disease")
    private String titleDisease;
}
