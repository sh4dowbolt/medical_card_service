package com.suraev.medical_card_service.service;

import com.suraev.medical_card_service.dto.DiseaseCreateDTO;
import com.suraev.medical_card_service.dto.DiseaseDTO;
import com.suraev.medical_card_service.dto.DiseaseUpdateDTO;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.List;

public interface DiseaseService {
    List<DiseaseDTO> getAllDiseases(Long patientId);
    ResponseEntity<DiseaseDTO> getDiseaseByID(Long patientId, Long diseaseId);
    ResponseEntity<DiseaseDTO> createDisease(Long patientId, DiseaseCreateDTO diseaseDTO) throws URISyntaxException;
    ResponseEntity<DiseaseDTO> updateDisease(Long patientId, DiseaseUpdateDTO diseaseDTO);
    ResponseEntity<Void> deleteDisease(Long patientId, Long diseaseId);
 }
