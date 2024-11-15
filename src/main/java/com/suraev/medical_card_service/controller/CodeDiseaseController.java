package com.suraev.medical_card_service.controller;


import com.suraev.medical_card_service.domain.entity.CodeDisease;
import com.suraev.medical_card_service.service.CodeDiseaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Справочник заболеваний по МКБ10", description = "Получить справочник заболеваний по МКБ10")
@RestController
@RequiredArgsConstructor

public class CodeDiseaseController {

    private final CodeDiseaseService codeDiseaseService;

    @Operation(summary = "Получить справочник заболеваний", description = "Позволяет получить справочник забоелваний по МКБ10")
    @ApiResponse(responseCode = "200", description = "Успешно получено", content = {@Content(schema = @Schema(implementation = CodeDisease.class), mediaType = "application/json")})
    @GetMapping(value= "/dictionary/mkb10", produces = "application/json")
    public List<CodeDisease> getCodeDiseaseList() {
        return codeDiseaseService.getAllCodeDiseases();
    }
}
