package com.suraev.medical_card_service.domain.entity;

import com.suraev.medical_card_service.domain.entity.enums.Sex;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Patients")
@Getter
@Setter
@Schema(description = "Сущность пациента")


public class Patient {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор пациента",example = "1")
    private Long id;

    @Column(name = "first_name")
    @Schema(description = "Имя пациента",example = "Иван")
    private String firstName;

    @Column(name="last_name")
    @Schema(description = "Фамилия пациента",example = "Сидоров")
    private String lastName;

    @Column(name ="middle_name")
    @Schema(description = "Отчество пациента",example = "Петрович")
    private String middleName;

    @Enumerated(EnumType.STRING)
    @Column(name="sex")
    @Schema(description = "Пол пациента",example = "М")
    private Sex sex;

    @Column(name="birthday")
    @Schema(description = "Дата рождения",example = "21-10-2003")
    private LocalDate birthday;

    @Column(name="number_policy")
    @Schema(description = "Номер полиса",example = "1234567812345678")
    private Long numberOfPolicy;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Schema(description = "Список заболеваний пациента")
    private List<Disease> diseaseList;

    public void addDisease(Disease disease) {
        if(diseaseList==null) {
            diseaseList = new ArrayList<>();
        }
        diseaseList.add(disease);
    }
}
