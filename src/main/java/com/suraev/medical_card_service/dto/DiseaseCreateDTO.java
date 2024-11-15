package com.suraev.medical_card_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
@Getter
@Setter
@Schema(description = "DTO для создания заболевания")
public class DiseaseCreateDTO {

    @Schema(description = "Код заболевания",example = "A01.0")
    private String numberOfDisease;

    @Schema(description = "Дата начала заболевания",example = "01-01-2024", type = "string")
    @PastOrPresent
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate startDisease;

    @PastOrPresent
    @Schema(description = "Дата окончания заболевания",example = "01-01-2024", type = "string")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate endDisease;

    @NotNull
    @Length(message = "Prescription must contain 1024 symbols", max = 1024)
    @Schema(description = "Назначение",example = "Примите анальгин")
    private String prescription;
}
