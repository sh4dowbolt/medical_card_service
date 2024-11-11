package com.suraev.medical_card_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.suraev.medical_card_service.util.validate.LengthNullableStringProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

import java.time.LocalDate;

@Getter
@Setter
public class DiseaseUpdateDTO {

    @NotNull
    private Long id;
    private JsonNullable<String> numberOfDisease;
    @PastOrPresent(message = "Birth date must be in the past or current")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private JsonNullable<LocalDate> startDisease;
    @PastOrPresent(message = "Birth date must be in the past or current")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private JsonNullable<LocalDate> endDisease;
    @LengthNullableStringProperty(message = "Prescription must contain 1024 symbols", max = 1024)
    private JsonNullable<String> prescription;

}
