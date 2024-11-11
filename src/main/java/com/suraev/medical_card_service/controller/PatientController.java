package com.suraev.medical_card_service.controller;

import com.suraev.medical_card_service.domain.entity.Patient;
import com.suraev.medical_card_service.dto.PatientCreateDTO;
import com.suraev.medical_card_service.dto.PatientDTO;
import com.suraev.medical_card_service.dto.PatientUpdateDTO;
import com.suraev.medical_card_service.repository.PatientRepository;
import com.suraev.medical_card_service.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping(value = "/{patient_id}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable Long patient_id) {
        return patientService.getPatientById(patient_id);
    }
    @PutMapping
    public ResponseEntity<PatientDTO> updatePatient(@RequestBody @Valid PatientUpdateDTO patient) {
        return patientService.updatePatient(patient);
    }
    @DeleteMapping(value ="/{patient_id}")
    public ResponseEntity<Void> deletePatient(@PathVariable String patient_id) {
        return patientService.deletePatientById(patient_id);
    }

    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@RequestBody @Valid PatientCreateDTO patient) throws URISyntaxException {
        return patientService.createPatient(patient);
    }


}
