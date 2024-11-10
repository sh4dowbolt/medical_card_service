package com.suraev.medical_card_service.util;

import com.suraev.medical_card_service.domain.entity.Patient;
import com.suraev.medical_card_service.dto.PatientDTO;
import com.suraev.medical_card_service.util.mapper.PatientMapper;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class ResponseUtil {
    public static  ResponseEntity<PatientDTO> wrapOrNotFound(Optional<Patient> optional, PatientMapper mapper) {
        if (optional.isPresent()) {
            Patient patient = optional.get();
            PatientDTO result = mapper.map(patient);
            return ResponseEntity.ok().body(result);
        }
        return  ResponseEntity.notFound().build();
    }
}

