package com.suraev.medical_card_service.dto;

import com.suraev.medical_card_service.domain.entity.enums.Sex;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public class PatientCreateDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String middleName;
    @NotBlank
    private Sex sex;
    @PastOrPresent
    private LocalDate birthday;
    @Length(min = 16, max = 16)
    private Long numberOfPolicy;
}
