package com.suraev.medical_card_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.suraev.medical_card_service.domain.entity.enums.Sex;
import com.suraev.medical_card_service.util.validate.LengthNullableStringProperty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

import java.time.LocalDate;

@Getter
@Setter
public class PatientUpdateDTO {
    private JsonNullable<Long> id;
    private JsonNullable<String> firstName;
    private JsonNullable<String> lastName;
    private JsonNullable<String> middleName;
    private JsonNullable<Sex> sex;
    @PastOrPresent(message = "Birth date must be in the past or current")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private JsonNullable<LocalDate> birthday;
    @LengthNullableStringProperty(message = "Number of policy must contain 16 digits", min = 16, max = 16)
    private JsonNullable<String> numberOfPolicy;
}
