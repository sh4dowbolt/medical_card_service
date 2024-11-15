package com.suraev.medical_card_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.suraev.medical_card_service.domain.entity.enums.Sex;
import com.suraev.medical_card_service.util.validate.LengthNullableStringProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "DTO для обновления пациента")

public class PatientUpdateDTO {
    @NotNull
    @Schema(description = "Идентификатор пациента",example = "1")
    private Long id;

    @Schema(description = "Имя пациента",example = "Иван", type = "String", nullable = true)
    private JsonNullable<String> firstName;

    @Schema(description = "Фамилия пациента",example = "Сидоров", type = "String", nullable = true)
    private JsonNullable<String> lastName;


    @Schema(description = "Отчество пациента",example = "Петрович", type = "String", nullable = true)
    private JsonNullable<String> middleName;

    @Schema(description = "Пол пациента", example = "M", type = "String", nullable = true)
    private JsonNullable<Sex> sex;


    @PastOrPresent(message = "Birth date must be in the past or current")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Schema(description = "Дата рождения",example = "21-10-2003")
    private JsonNullable<LocalDate> birthday;

    @LengthNullableStringProperty(message = "Number of policy must contain 16 digits", min = 16, max = 16)
    @Schema(description = "Номер полиса",example = "1234567812345678", type = "String", nullable = true)
    private JsonNullable<String> numberOfPolicy;
}
