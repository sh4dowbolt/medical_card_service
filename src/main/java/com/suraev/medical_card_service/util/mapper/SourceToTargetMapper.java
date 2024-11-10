package com.suraev.medical_card_service.util.mapper;

import com.suraev.medical_card_service.domain.entity.Patient;
import com.suraev.medical_card_service.dto.PatientDTO;
import org.mapstruct.Mapper;

@Mapper
public interface SourceToTargetMapper  {
    PatientDTO toTargetEntity(Patient source);
    Patient toSourceEntity(PatientDTO target);
}
