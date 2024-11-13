package com.suraev.medical_card_service.controller;

import com.suraev.medical_card_service.domain.entity.Disease;
import com.suraev.medical_card_service.dto.DiseaseCreateDTO;
import com.suraev.medical_card_service.dto.DiseaseDTO;
import com.suraev.medical_card_service.dto.DiseaseUpdateDTO;
import com.suraev.medical_card_service.service.CodeDiseaseService;
import com.suraev.medical_card_service.service.DiseaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/patient/{patient_id}/disease")
@RequiredArgsConstructor
public class DiseaseController {
    private final DiseaseService diseaseService;

    @GetMapping
    public List<DiseaseDTO> getAllDiseases(@PathVariable Long patient_id) {
        return diseaseService.getAllDiseases(patient_id);
    }
    @GetMapping(value = "/{disease_id}")
    public ResponseEntity<DiseaseDTO>getDiseaseById(@PathVariable Long patient_id, @PathVariable Long disease_id) {
        return diseaseService.getDiseaseByID(patient_id,disease_id);
    }
    @PostMapping
    public ResponseEntity<DiseaseDTO> createDisease(@PathVariable Long patient_id, @RequestBody DiseaseCreateDTO disease) throws URISyntaxException {
        return diseaseService.createDisease(patient_id, disease);
    }
    @PutMapping(produces = "application/json")
    public ResponseEntity<DiseaseDTO> updateDisease(@PathVariable("patient_id") Long patient_id, @Valid @RequestBody DiseaseUpdateDTO disease) {
        return diseaseService.updateDisease(patient_id, disease);
    }
    @DeleteMapping(value = "/{disease_id}")
    public ResponseEntity<Void> deleteDisease(@PathVariable("patient_id") Long patient_id, @PathVariable("disease_id") Long disease_id) {
        return diseaseService.deleteDisease(patient_id,disease_id);
    }
}
