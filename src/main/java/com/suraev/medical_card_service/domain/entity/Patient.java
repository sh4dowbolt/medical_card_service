package com.suraev.medical_card_service.domain.entity;

import com.suraev.medical_card_service.domain.entity.enums.Sex;
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

public class Patient {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name ="middle_name")
    private String middleName;
    @Enumerated(EnumType.STRING)
    @Column(name="sex")
    private Sex sex;
    @Column(name="birthday")
    private LocalDate birthday;
    @Column(name="number_policy")
    private Long numberOfPolicy;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Disease> diseaseList;

    public void addDisease(Disease disease) {
        if(diseaseList==null) {
            diseaseList = new ArrayList<>();
        }
        diseaseList.add(disease);
    }
}
