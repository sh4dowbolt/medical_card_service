package com.suraev.medical_card_service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class FieldError {
    private final String objectName;
    private final String field;
    private final String message;
}
