package com.suraev.medical_card_service.util.mapper;

import com.suraev.medical_card_service.domain.entity.Disease;
import com.suraev.medical_card_service.dto.DiseaseCreateDTO;
import com.suraev.medical_card_service.dto.DiseaseDTO;
import com.suraev.medical_card_service.dto.DiseaseUpdateDTO;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-15T20:13:36+0400",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class DiseaseMapperImpl implements DiseaseMapper {

    @Autowired
    private JsonNullableMapper jsonNullableMapper;

    @Override
    public Disease mapToEntity(DiseaseCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Disease disease = new Disease();

        disease.setNumberOfDisease( dto.getNumberOfDisease() );
        disease.setStartDisease( dto.getStartDisease() );
        disease.setEndDisease( dto.getEndDisease() );
        disease.setPrescription( dto.getPrescription() );

        return disease;
    }

    @Override
    public DiseaseDTO mapToDTO(Disease disease) {
        if ( disease == null ) {
            return null;
        }

        DiseaseDTO diseaseDTO = new DiseaseDTO();

        diseaseDTO.setId( disease.getId() );
        diseaseDTO.setNumberOfDisease( disease.getNumberOfDisease() );
        diseaseDTO.setStartDisease( disease.getStartDisease() );
        diseaseDTO.setEndDisease( disease.getEndDisease() );
        diseaseDTO.setPrescription( disease.getPrescription() );

        return diseaseDTO;
    }

    @Override
    public void update(DiseaseUpdateDTO dto, Disease disease) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            disease.setId( dto.getId() );
        }
        if ( dto.getNumberOfDisease() != null ) {
            disease.setNumberOfDisease( jsonNullableMapper.unwrap( dto.getNumberOfDisease() ) );
        }
        if ( dto.getStartDisease() != null ) {
            disease.setStartDisease( jsonNullableMapper.unwrap( dto.getStartDisease() ) );
        }
        if ( dto.getEndDisease() != null ) {
            disease.setEndDisease( jsonNullableMapper.unwrap( dto.getEndDisease() ) );
        }
        if ( dto.getPrescription() != null ) {
            disease.setPrescription( jsonNullableMapper.unwrap( dto.getPrescription() ) );
        }
    }
}
