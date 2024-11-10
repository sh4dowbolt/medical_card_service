package com.suraev.medical_card_service.util.mapper;

import com.suraev.medical_card_service.domain.entity.Patient;
import com.suraev.medical_card_service.dto.PatientCreateDTO;
import com.suraev.medical_card_service.dto.PatientDTO;
import com.suraev.medical_card_service.dto.PatientUpdateDTO;
import org.mapstruct.*;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {JsonNullableMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientMapper{

    Patient map(PatientCreateDTO dto);
    PatientDTO map(Patient patient);
    void update(PatientUpdateDTO dto, @MappingTarget Patient patient);


}
