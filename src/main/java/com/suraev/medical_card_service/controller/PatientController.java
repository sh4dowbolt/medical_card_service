package com.suraev.medical_card_service.controller;

import com.suraev.medical_card_service.dto.PatientCreateDTO;
import com.suraev.medical_card_service.dto.PatientDTO;
import com.suraev.medical_card_service.dto.PatientUpdateDTO;
import com.suraev.medical_card_service.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping(value = "/{patient_id}", produces = "application/json")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable("patient_id") Long patientId) {
        return patientService.getPatientById(patientId);
    }
    @PutMapping(produces = "application/json")
    public ResponseEntity<PatientDTO> updatePatient(@Valid @RequestBody PatientUpdateDTO patientDTO) {
        return patientService.updatePatient(patientDTO);
    }
    @DeleteMapping(value ="/{patient_id}", produces = "application/json")
    public ResponseEntity<Void> deletePatient(@PathVariable("patient_id") Long patientId) {
        return patientService.deletePatientById(patientId);
    }
    @PostMapping
    //TODO: URISyntaxException
    public ResponseEntity<PatientDTO> createPatient(@Valid @RequestBody PatientCreateDTO patientDTO) throws URISyntaxException {
        return patientService.createPatient(patientDTO);
    }


}
