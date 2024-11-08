package com.suraev.medical_card_service.dto;

import com.suraev.medical_card_service.domain.entity.enums.Sex;

public class PatientUpdateDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Sex sex;
}