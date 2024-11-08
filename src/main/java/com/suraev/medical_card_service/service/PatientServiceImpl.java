package com.suraev.medical_card_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suraev.medical_card_service.domain.entity.Patient;
import com.suraev.medical_card_service.dto.PatientCreateDTO;
import com.suraev.medical_card_service.dto.PatientDTO;
import com.suraev.medical_card_service.dto.PatientUpdateDTO;
import com.suraev.medical_card_service.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final ObjectMapper objectMapper;

    @Override
    public PatientDTO createPatient(PatientCreateDTO patientDTO) {
        objectMapper.map(patientDTO);
        patientRepository.save();
    }

    @Override
    public PatientDTO updatePatient(PatientUpdateDTO patientDTO) {
        return null;
    }

    @Override
    public PatientDTO getPatientById(String id) {
        return null;
    }

    @Override
    public void deletePatientById(String id) {

    }
}
