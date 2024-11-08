package com.suraev.medical_card_service.controller;

import com.suraev.medical_card_service.domain.entity.Patient;
import com.suraev.medical_card_service.dto.PatientCreateDTO;
import com.suraev.medical_card_service.dto.PatientDTO;
import com.suraev.medical_card_service.dto.PatientUpdateDTO;
import com.suraev.medical_card_service.repository.PatientRepository;
import com.suraev.medical_card_service.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping(value = "/{patient_id}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable String patient_id) {

    }
    @PutMapping
    public ResponseEntity<PatientDTO> updatePatient(@RequestBody PatientUpdateDTO patient) {

    }
    @DeleteMapping
    public ResponseEntity<Void> deletePatient(@PathVariable String patient_id) {

    }

    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientCreateDTO patient) {
        return patientService.createPatient(patient);
    }


}
