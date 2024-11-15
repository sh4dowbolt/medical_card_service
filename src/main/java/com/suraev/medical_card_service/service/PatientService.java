package com.suraev.medical_card_service.service;

import com.suraev.medical_card_service.dto.PatientCreateDTO;
import com.suraev.medical_card_service.dto.PatientDTO;
import com.suraev.medical_card_service.dto.PatientUpdateDTO;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;

public interface PatientService {
    ResponseEntity<PatientDTO> createPatient(PatientCreateDTO patientDTO);
    ResponseEntity<PatientDTO> updatePatient(PatientUpdateDTO patientDTO);
    ResponseEntity<PatientDTO> getPatientById(Long id);
    ResponseEntity<Void> deletePatientById(Long id);
}
