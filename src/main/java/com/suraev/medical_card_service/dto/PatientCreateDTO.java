package com.suraev.medical_card_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.suraev.medical_card_service.domain.entity.enums.Sex;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "DTO для создания пациента")
public class PatientCreateDTO {

    @NotBlank(message = "First name must be not empty")
    @Schema(description = "Имя пациента",example = "Иван")
    private String firstName;

    @NotBlank(message = "Last name must be not empty")
    @Schema(description = "Фамилия пациента",example = "Сидоров")
    private String lastName;

    @Schema(description = "Отчество пациента",example = "Петрович")
    private String middleName;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Пол пациента",example = "М")
    private Sex sex;

    @NotNull(message = "Birth date must be not empty")
    @PastOrPresent(message = "Birth date must be in the past or current")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Schema(description = "Дата рождения",example = "21-10-2003")
    private LocalDate birthday;

    @NotNull(message = "Number of policy must be not empty")
    @Length(message = "Number of policy must contain 16 digits", min = 16, max = 16)
    @Schema(description = "Номер полиса",example = "1234567812345678")
    private String numberOfPolicy;
}
