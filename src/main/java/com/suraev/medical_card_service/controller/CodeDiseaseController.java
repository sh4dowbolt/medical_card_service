package com.suraev.medical_card_service.controller;


import com.suraev.medical_card_service.domain.entity.CodeDisease;
import com.suraev.medical_card_service.repository.CodeDiseaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dictionary")
@RequiredArgsConstructor
public class CodeDiseaseController {

    private final CodeDiseaseRepository codeDiseaseRepository;

    @GetMapping(name= "/mkb10",produces = "application/json")
    public List<CodeDisease> getCodeDiseaseList() {
        return codeDiseaseRepository.findAll();
    }


}
