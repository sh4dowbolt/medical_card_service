package com.suraev.medical_card_service.controller;

import com.suraev.medical_card_service.dto.DiseaseCreateDTO;
import com.suraev.medical_card_service.dto.DiseaseDTO;
import com.suraev.medical_card_service.dto.DiseaseUpdateDTO;
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

    @GetMapping(produces = "application/json")
    public List<DiseaseDTO> getAllDiseases(@PathVariable("patient_id") Long patientId) {
        return diseaseService.getAllDiseases(patientId);
    }
    @GetMapping(value = "/{disease_id}", produces = "application/json")
    public ResponseEntity<DiseaseDTO>getDiseaseById(@PathVariable("patient_id") Long patientId, @PathVariable("disease_id") Long diseaseId) {
        return diseaseService.getDiseaseByID(patientId,diseaseId);
    }
    @PostMapping(produces = "application/json")
    //TODO: опять же, что делать URISyntaxException в сигнатуре метода
    public ResponseEntity<DiseaseDTO> createDisease(@PathVariable("patient_id") Long patientId, @Valid @RequestBody DiseaseCreateDTO diseaseDTO) throws URISyntaxException {
        return diseaseService.createDisease(patientId, diseaseDTO);
    }
    @PutMapping(produces = "application/json")
    public ResponseEntity<DiseaseDTO> updateDisease(@PathVariable("patient_id") Long patientId, @Valid @RequestBody DiseaseUpdateDTO diseaseDTO) {
        return diseaseService.updateDisease(patientId, diseaseDTO);
    }
    @DeleteMapping(value = "/{disease_id}")
    public ResponseEntity<Void> deleteDisease(@PathVariable("patient_id") Long patientId, @PathVariable("disease_id") Long diseaseId) {
        return diseaseService.deleteDisease(patientId,diseaseId);
    }
}
