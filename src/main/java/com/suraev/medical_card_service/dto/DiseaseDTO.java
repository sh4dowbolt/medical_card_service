package com.suraev.medical_card_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "DTO заболевания для Response")
public class DiseaseDTO {

    @Schema(description = "Идентификатор заболевания", example = "1")
    private Long id;
    @Schema(description = "Код заболевания",example = "A01.0")
    private String numberOfDisease;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Schema(description = "Дата начала заболевания",example = "01-01-2024", type = "string")
    private LocalDate startDisease;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Schema(description = "Дата окончания заболевания",example = "01-01-2024", type = "string")
    private LocalDate endDisease;

    @Schema(description = "Назначение",example = "Примите анальгин")
    private String prescription;
}
