package com.suraev.medical_card_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Schema(description = "DTO для обновления заболевания")
public class DiseaseUpdateDTO {

    @NotNull
    @Schema(description = "Идентификатор заболевания",example = "1")
    private Long id;

    @Schema(description = "Код заболевания",example = "A01.0", type = "string", nullable = true)
    private JsonNullable<String> numberOfDisease;

    @PastOrPresent(message = "Birth date must be in the past or current")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Schema(description = "Дата начала заболевания",example = "01-01-2024", type="localDate", nullable = true)
    private JsonNullable<LocalDate> startDisease;

    @PastOrPresent(message = "Birth date must be in the past or current")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Schema(description = "Дата окончания заболевания",example = "01-01-2024", type="string", nullable = true)
    private JsonNullable<LocalDate> endDisease;

    @LengthNullableStringProperty(message = "Prescription must contain 1024 symbols", max = 1024)
    @Schema(description = "Назначение",example = "Примите анальгин", type="string", nullable = true)
    private JsonNullable<String> prescription;

}
