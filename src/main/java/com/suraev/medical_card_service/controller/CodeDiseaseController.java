package com.suraev.medical_card_service.controller;


import com.suraev.medical_card_service.domain.entity.CodeDisease;
import com.suraev.medical_card_service.service.CodeDiseaseService;
import com.suraev.medical_card_service.service.CodeDiseaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CodeDiseaseController {

    private final CodeDiseaseService codeDiseaseService;

    @GetMapping(value= "/dictionary/mkb10", produces = "application/json")
    public List<CodeDisease> getCodeDiseaseList() {
        return codeDiseaseService.getAllCodeDiseases();
    }
}
