package com.suraev.medical_card_service.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CodeDisease {
    private String id;
    private String titleDisease;
}
