package com.suraev.medical_card_service.service;

import com.suraev.medical_card_service.domain.entity.Patient;
import com.suraev.medical_card_service.dto.PatientCreateDTO;
import com.suraev.medical_card_service.dto.PatientDTO;
import com.suraev.medical_card_service.dto.PatientUpdateDTO;
import com.suraev.medical_card_service.exception.BadRequestAlertException;
import com.suraev.medical_card_service.repository.PatientRepository;
import com.suraev.medical_card_service.util.HeaderUtil;
import com.suraev.medical_card_service.util.ResponseUtil;
import com.suraev.medical_card_service.util.mapper.PatientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Status;

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
    @Transactional
    public ResponseEntity<PatientDTO> createPatient(PatientCreateDTO patientDTO) {
        final var patient = patientMapper.map(patientDTO);
        final var result = patientRepository.save(patient);
        final var patientResponse = patientMapper.map(result);

        HttpHeaders headers = HeaderUtil.createEntityCreationAlert(applicationName, ENTITY_NAME, String.valueOf(patientResponse.getId()));

        try {
            return ResponseEntity.created(new URI("/api/v1/patient/"+patientResponse.getId())).headers(headers).body(patientResponse);
        } catch (URISyntaxException e) {
            throw new BadRequestAlertException("URL cant be parsed",Status.BAD_REQUEST, ENTITY_NAME,"wrong_url");
        }
    }

    @Override
    @Transactional
    public ResponseEntity<PatientDTO> updatePatient(PatientUpdateDTO patientDTO) {

        final var existedPatient = patientRepository.findById(patientDTO.getId())
                .orElseThrow(() -> new BadRequestAlertException("Such patient with this id doesn't exist",Status.BAD_REQUEST,ENTITY_NAME,"no_exist_patient)"));

        patientMapper.update(patientDTO,existedPatient);
        final var result = patientRepository.save(existedPatient);
        final var patientResponse = patientMapper.map(result);
        HttpHeaders headers = HeaderUtil.createEntityUpdateAlert(applicationName, ENTITY_NAME, String.valueOf(patientResponse.getId()));

        return ResponseEntity.ok().headers(headers).body(patientResponse);
    }
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<PatientDTO> getPatientById(Long id) {
        final var result = patientRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(result, patientMapper);
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deletePatientById(Long id) {
        patientRepository.deleteById(id);
        HttpHeaders headers = HeaderUtil.createEntityDeletionAlert(applicationName, ENTITY_NAME, String.valueOf(id));
        return ResponseEntity.noContent().headers(headers).build();
    }
}
