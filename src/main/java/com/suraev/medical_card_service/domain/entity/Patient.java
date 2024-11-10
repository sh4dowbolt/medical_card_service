package com.suraev.medical_card_service.domain.entity;

import com.suraev.medical_card_service.domain.entity.enums.Sex;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Patients")
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
    @Column(name="sex")
    private Sex sex;
    @Column(name="birthday")
    private LocalDate birthday;
    @Column(name="number_policy")
    private Long numberOfPolicy;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Disease> diseaseList;

}
