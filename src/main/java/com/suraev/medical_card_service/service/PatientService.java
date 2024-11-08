package com.suraev.medical_card_service.service;

import com.suraev.medical_card_service.domain.entity.Patient;
import com.suraev.medical_card_service.dto.PatientCreateDTO;
import com.suraev.medical_card_service.dto.PatientDTO;
import com.suraev.medical_card_service.dto.PatientUpdateDTO;

public interface PatientService {
    PatientDTO createPatient(PatientCreateDTO patientDTO);
    PatientDTO updatePatient(PatientUpdateDTO patientDTO);
    PatientDTO getPatientById(String id);
    void deletePatientById(String id);
}
