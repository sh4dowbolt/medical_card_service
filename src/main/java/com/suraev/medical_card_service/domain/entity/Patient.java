package com.suraev.medical_card_service.domain.entity;

import com.suraev.medical_card_service.domain.entity.enums.Sex;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Patient")
public class Patient {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name ="middle_name")
    private String middleName;
    @Column(name="sex")
    private Sex sex;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Disease> diseaseList;

}
