package com.suraev.medical_card_service.util.mapper;

import com.suraev.medical_card_service.domain.entity.Disease;
import com.suraev.medical_card_service.dto.DiseaseCreateDTO;
import com.suraev.medical_card_service.dto.DiseaseDTO;
import com.suraev.medical_card_service.dto.DiseaseUpdateDTO;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {JsonNullableMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiseaseMapper{

    Disease mapToEntity(DiseaseCreateDTO dto);
    DiseaseDTO mapToDTO(Disease disease);
    void update(DiseaseUpdateDTO dto, @MappingTarget Disease disease);


}

