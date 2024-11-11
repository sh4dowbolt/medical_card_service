package com.suraev.medical_card_service.service;

import com.suraev.medical_card_service.domain.entity.Disease;
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
import org.zalando.problem.Status;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

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
    public List<DiseaseDTO> getAllDiseases(Long patient_id) {
        var patient = patientRepository.findById(patient_id).orElseThrow(
                () -> new BadRequestAlertException("An existing patient must have an id", Status.BAD_REQUEST, ENTITY_NAME, "noexistid"));
        return patient.getDiseaseList().stream().map(diseaseMapper::mapToDTO).toList();
    }

    @Override
    public ResponseEntity<DiseaseDTO> getDiseaseByID(Long patient_id, Long disease_id) {
        var patient = patientRepository.findById(patient_id).orElseThrow(
                () -> new BadRequestAlertException("An existing patient must have an id", Status.BAD_REQUEST, ENTITY_NAME, "noexistid"));
        Optional<DiseaseDTO> result = patient.getDiseaseList().stream().map(diseaseMapper::mapToDTO)
                .filter(diseaseDTO -> Objects.equals(diseaseDTO.getId(), disease_id)).findFirst();
        return ResponseUtil.wrapOrNotFound(result);

    }

    @Override
    public ResponseEntity<DiseaseDTO> createDisease(Long patient_id, DiseaseCreateDTO disease) throws URISyntaxException {
        var patient = patientRepository.findById(patient_id).orElseThrow(
                () -> new BadRequestAlertException("An existing patient must have an id", Status.BAD_REQUEST, ENTITY_NAME, "noexistid"));

        var existedCodeDisease = codeDiseaseRepository.findById(disease.getNumberOfDisease()).orElseThrow(
                () -> new BadRequestAlertException("Should be existed code disease from GET /dictionary/mkb10", Status.BAD_REQUEST, ENTITY_NAME, "no_exist_disease_id"));
        Disease diseaseEntity = diseaseMapper.mapToEntity(disease);
        diseaseEntity.setPatient(patient);
        patient.addDisease(diseaseEntity);

        var result = patientRepository.save(patient);

        Disease diseaseFromDB = result.getDiseaseList().get(result.getDiseaseList().size()-1);
        DiseaseDTO actualResult = diseaseMapper.mapToDTO(diseaseFromDB);

        HttpHeaders headers = HeaderUtil.createEntityCreationAlert(applicationName, ENTITY_NAME, String.valueOf(actualResult.getId()));
        return ResponseEntity.created(new URI("api/v1/patient/"+actualResult.getId()+"/disease")).headers(headers).body(actualResult);
    }

    @Override
    public ResponseEntity<DiseaseDTO> updateDisease(Long patient_id, DiseaseUpdateDTO disease) {
        var patient = patientRepository.findById(patient_id).orElseThrow(
                () -> new BadRequestAlertException("An existing patient must have an id", Status.BAD_REQUEST, ENTITY_NAME, "noexistid"));
        if(disease.getNumberOfDisease() != null) {
            final var existedCodeDisease = codeDiseaseRepository.findById(disease.getNumberOfDisease().get()).orElseThrow(
                    () -> new BadRequestAlertException("Should be existed code disease from GET /dictionary/mkb10", Status.BAD_REQUEST, ENTITY_NAME, "no_exist_disease_id"));
        }
       List<Disease> list = patient.getDiseaseList().stream().map(x -> Objects.equals(x.getId(), disease.getId()) ? diseaseMapper.update(disease,x) : x).toList();
       patient.setDiseaseList(list);

       var result = patientRepository.save(patient);

       Optional<Disease> diseaseFromDB = result.getDiseaseList().stream().filter(x -> x.getId().equals(disease.getId())).findFirst();
       DiseaseDTO actualResult = diseaseFromDB.map(diseaseMapper::mapToDTO).orElseThrow( () -> new NoSuchElementException("такого элемента не нашлось"));

       HttpHeaders headers = HeaderUtil.createEntityUpdateAlert(applicationName, ENTITY_NAME, String.valueOf(disease.getId()));

       return ResponseEntity.ok().headers(headers).body(actualResult);

    }

    @Override
    public ResponseEntity<Void> deleteDisease(Long patient_id, Long disease_id) {
        return null;
    }
}
