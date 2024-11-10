package com.suraev.medical_card_service.service;

import com.suraev.medical_card_service.domain.entity.Patient;
import com.suraev.medical_card_service.dto.PatientCreateDTO;
import com.suraev.medical_card_service.dto.PatientDTO;
import com.suraev.medical_card_service.dto.PatientUpdateDTO;
import com.suraev.medical_card_service.repository.PatientRepository;
import com.suraev.medical_card_service.util.HeaderUtil;
import com.suraev.medical_card_service.util.ResponseUtil;
import com.suraev.medical_card_service.util.mapper.PatientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    @Value("${spring.application.name}")
    private String applicationName;
    private static final String ENTITY_NAME="Patient";
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public ResponseEntity<PatientDTO> createPatient(PatientCreateDTO patientDTO) throws URISyntaxException {
        var patient = patientMapper.map(patientDTO);
        var result = patientRepository.save(patient);
        var patientResponse = patientMapper.map(result);

        HttpHeaders headers = HeaderUtil.createEntityCreationAlert(applicationName, ENTITY_NAME, String.valueOf(patientResponse.getId()));
        // TODO: протестить на что влияет "/api/patient"
        return ResponseEntity.created(new URI("/api/patient"+patientResponse.getId())).headers(headers).body(patientResponse);
    }

    @Override
    public ResponseEntity<PatientDTO> updatePatient(PatientUpdateDTO patientDTO) {
        if(patientDTO.getId() == null) {
            throw new IllegalArgumentException("An existing patient should have an id");
        }
        Patient existedPatient = patientRepository.findById(patientDTO.getId().get())
                .orElseThrow(() -> new IllegalArgumentException("Such patient with this id doesn't exist"));

        patientMapper.update(patientDTO,existedPatient);
        var result = patientRepository.save(existedPatient);
        var patientResponse = patientMapper.map(result);
        HttpHeaders headers = HeaderUtil.createEntityUpdateAlert(applicationName, ENTITY_NAME, String.valueOf(patientResponse.getId()));

        return ResponseEntity.ok().headers(headers).body(patientResponse);
    }
    @Override
    public ResponseEntity<PatientDTO> getPatientById(Long id) {
        var result = patientRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(result, patientMapper);
    }

    @Override
    public ResponseEntity<Void> deletePatientById(String id) {
        HttpHeaders headers = HeaderUtil.createEntityDeletionAlert(applicationName, ENTITY_NAME, id);
        return ResponseEntity.noContent().headers(headers).build();
    }
}
