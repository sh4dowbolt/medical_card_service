package com.suraev.medical_card_service.dto;

import com.suraev.medical_card_service.domain.entity.enums.Sex;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Getter
@Setter
public class PatientCreateDTO {
    @NotBlank(message = "Имя не должно быть пустым")
    private String firstName;
    @NotBlank
    private String lastName;
    private String middleName;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @PastOrPresent
    private LocalDate birthday;
    @Length(message = "Номер полиса должен состоять из 16 цифр", min = 16, max = 16)
    private String numberOfPolicy;
}
