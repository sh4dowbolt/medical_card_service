package com.suraev.medical_card_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @NotBlank(message = "First name must be not empty")
    private String firstName;
    @NotBlank(message = "Last name must be not empty")
    private String lastName;
    private String middleName;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @NotNull(message = "Birth date must be not empty")
    @PastOrPresent(message = "Birth date must be in the past or current")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birthday;
    @NotNull(message = "Number of policy must be not empty")
    @Length(message = "Number of policy must contain 16 digits", min = 16, max = 16)
    private String numberOfPolicy;
}
