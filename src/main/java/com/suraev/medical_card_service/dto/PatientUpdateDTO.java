package com.suraev.medical_card_service.dto;

import com.suraev.medical_card_service.domain.entity.enums.Sex;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Getter
@Setter
public class PatientUpdateDTO {
    private JsonNullable <Long> id;
    private JsonNullable <String> firstName;
    private JsonNullable <String> lastName;
    private JsonNullable <String> middleName;
    private JsonNullable <Sex> sex;
}
