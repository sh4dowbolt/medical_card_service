package com.suraev.medical_card_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.suraev.medical_card_service.domain.entity.enums.Sex;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "DTO пациента для ответа")
public class PatientDTO {
        @Schema(description = "Идентификатор пациента",example = "1")
        private Long id;

        @Schema(description = "Имя пациента",example = "Иван")
        private String firstName;

        @Schema(description = "Фамилия пациента",example = "Сидоров")
        private String lastName;

        @Schema(description = "Отчество пациента",example = "Петрович")
        private String middleName;

        @Schema(description = "Пол пациента",example = "М")
        private Sex sex;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        @Schema(description = "Дата рождения",example = "21-10-2003")
        private LocalDate birthday;

        @Schema(description = "Номер полиса",example = "1234567812345678")
        private String numberOfPolicy;
}

