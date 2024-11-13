package com.suraev.medical_card_service.service;

import com.suraev.medical_card_service.domain.entity.CodeDisease;
import com.suraev.medical_card_service.domain.entity.Patient;
import com.suraev.medical_card_service.dto.DiseaseCreateDTO;
import com.suraev.medical_card_service.dto.DiseaseDTO;
import com.suraev.medical_card_service.dto.DiseaseUpdateDTO;
import com.suraev.medical_card_service.exception.BadRequestAlertException;
import com.suraev.medical_card_service.repository.CodeDiseaseRepository;
import com.suraev.medical_card_service.repository.PatientRepository;
import com.suraev.medical_card_service.util.HeaderUtil;
import com.suraev.medical_card_service.util.ResponseUtil;
import com.suraev.medical_card_service.util.mapper.DiseaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Status;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DiseaseServiceImpl implements DiseaseService{
    @Value("${spring.application.name}")
    private String applicationName;
    private static final String ENTITY_NAME="Disease";
    private final CodeDiseaseRepository codeDiseaseRepository;
    private final PatientRepository patientRepository;
    private final DiseaseMapper diseaseMapper;

    @Override
    @Transactional(readOnly = true)
    public List<DiseaseDTO> getAllDiseases(Long patientId) {
        final var patient = getPatientFromDbIfExisted(patientId);
        return patient.getDiseaseList().stream().map(diseaseMapper::mapToDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<DiseaseDTO> getDiseaseByID(Long patientId, Long diseaseId) {
        final var patient = getPatientFromDbIfExisted(patientId);
        final var result = patient.getDiseaseList().stream().map(diseaseMapper::mapToDTO)
                .filter(diseaseDTO -> Objects.equals(diseaseDTO.getId(), diseaseId)).findFirst();
        return ResponseUtil.wrapOrNotFound(result);
    }
    @Override
    @Transactional
    public ResponseEntity<DiseaseDTO> createDisease(Long patientId, DiseaseCreateDTO diseaseDTO) throws URISyntaxException {
        final var patient = getPatientFromDbIfExisted(patientId);
        final var existedCodeDisease = getCodeDiseaseFromDBIfExisted(diseaseDTO.getNumberOfDisease());

        final var disease = diseaseMapper.mapToEntity(diseaseDTO);
        disease.setPatient(patient);
        patient.addDisease(disease);

        final var result = patientRepository.save(patient);

        final var diseaseFromDB = result.getDiseaseList().get(result.getDiseaseList().size()-1);
        final var diseaseDTOForResponse = diseaseMapper.mapToDTO(diseaseFromDB);

        HttpHeaders headers = HeaderUtil.createEntityCreationAlert(applicationName, ENTITY_NAME, String.valueOf(diseaseDTOForResponse.getId()));

        return ResponseEntity.created(new URI("api/v1/patient/"+diseaseDTOForResponse.getId()+"/disease")).headers(headers).body(diseaseDTOForResponse);
    }

    @Override
    @Transactional
    public ResponseEntity<DiseaseDTO> updateDisease(Long patientId, DiseaseUpdateDTO diseaseDTO) {
        final var patient = getPatientFromDbIfExisted(patientId);
        if(diseaseDTO.getNumberOfDisease() != null) {
            getCodeDiseaseFromDBIfExisted(diseaseDTO.getNumberOfDisease().get());
        }
        final var disease = patient.getDiseaseList().stream().filter(diseaseEntity -> diseaseEntity.getId().equals(diseaseDTO.getId()))
                .findFirst().orElseThrow(() -> new BadRequestAlertException("A disease with this id doesn't exist",Status.BAD_REQUEST,ENTITY_NAME,"no_exist_disease"));

        diseaseMapper.update(diseaseDTO,disease);

        final var result = patientRepository.save(patient);

        final var diseaseFromDB = result.getDiseaseList().stream().filter(x -> x.getId().equals(diseaseDTO.getId())).findFirst();
        final var diseaseDTOForResponse = diseaseFromDB.map(diseaseMapper::mapToDTO).get();

        HttpHeaders headers = HeaderUtil.createEntityUpdateAlert(applicationName, ENTITY_NAME, String.valueOf(diseaseDTO.getId()));

        return ResponseEntity.ok().headers(headers).body(diseaseDTOForResponse);

    }
    @Override
    @Transactional
    public ResponseEntity<Void> deleteDisease(Long patientId, Long diseaseId) {
        var patient = getPatientFromDbIfExisted(patientId);
        patient.getDiseaseList().removeIf(x -> x.getId().equals(diseaseId));

        patientRepository.save(patient);
        HttpHeaders headers = HeaderUtil.createEntityDeletionAlert(applicationName, ENTITY_NAME, String.valueOf(diseaseId));

        return ResponseEntity.noContent().headers(headers).build();
    }
    //TODO: надо ли приватные методы сервиса делать транкзационными, они же делают запрос к бд ?
    private Patient getPatientFromDbIfExisted(Long patient_id) {
        return patientRepository.findById(patient_id).orElseThrow(
                () -> new BadRequestAlertException("An existing patient must have an id", Status.BAD_REQUEST, ENTITY_NAME, "noexistid"));
    }
    private CodeDisease getCodeDiseaseFromDBIfExisted(String diseaseId) {
        return codeDiseaseRepository.findById(diseaseId).orElseThrow(
                () -> new BadRequestAlertException("Should be existed code disease from GET /dictionary/mkb10", Status.BAD_REQUEST, ENTITY_NAME, "no_exist_code_disease"));
    }
}
