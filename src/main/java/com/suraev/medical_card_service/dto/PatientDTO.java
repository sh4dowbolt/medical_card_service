package com.suraev.medical_card_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.suraev.medical_card_service.domain.entity.enums.Sex;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientDTO {
        private Long id;
        private String firstName;
        private String lastName;
        private String middleName;
        private Sex sex;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        private LocalDate birthday;
        private String numberOfPolicy;
}

