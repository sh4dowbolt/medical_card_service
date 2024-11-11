package com.suraev.medical_card_service.service;

import com.suraev.medical_card_service.dto.DiseaseCreateDTO;
import com.suraev.medical_card_service.dto.DiseaseDTO;
import com.suraev.medical_card_service.dto.DiseaseUpdateDTO;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.List;

public interface DiseaseService {
    List<DiseaseDTO> getAllDiseases(Long patient_id);
    ResponseEntity<DiseaseDTO> getDiseaseByID(Long patient_id, Long disease_id);
    ResponseEntity<DiseaseDTO> createDisease(Long patient_id, DiseaseCreateDTO disease) throws URISyntaxException;
    ResponseEntity<DiseaseDTO> updateDisease(Long patient_id, DiseaseUpdateDTO disease);
    ResponseEntity<Void> deleteDisease(Long patient_id, Long disease_id);
 }
