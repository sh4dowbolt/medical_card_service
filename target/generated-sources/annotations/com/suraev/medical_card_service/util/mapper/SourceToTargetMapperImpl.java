package com.suraev.medical_card_service.util.mapper;

import com.suraev.medical_card_service.domain.entity.Disease;
import com.suraev.medical_card_service.domain.entity.Patient;
import com.suraev.medical_card_service.dto.DiseaseDTO;
import com.suraev.medical_card_service.dto.PatientDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-15T20:13:36+0400",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
public class SourceToTargetMapperImpl implements SourceToTargetMapper {

    @Override
    public PatientDTO toTargetEntity(Patient source) {
        if ( source == null ) {
            return null;
        }

        PatientDTO patientDTO = new PatientDTO();

        patientDTO.setId( source.getId() );
        patientDTO.setFirstName( source.getFirstName() );
        patientDTO.setLastName( source.getLastName() );
        patientDTO.setMiddleName( source.getMiddleName() );
        patientDTO.setSex( source.getSex() );
        patientDTO.setBirthday( source.getBirthday() );
        if ( source.getNumberOfPolicy() != null ) {
            patientDTO.setNumberOfPolicy( String.valueOf( source.getNumberOfPolicy() ) );
        }

        return patientDTO;
    }

    @Override
    public Patient toSourceEntity(PatientDTO target, PatientMapper mapper) {
        if ( target == null && mapper == null ) {
            return null;
        }

        Patient patient = new Patient();

        if ( target != null ) {
            patient.setId( target.getId() );
            patient.setFirstName( target.getFirstName() );
            patient.setLastName( target.getLastName() );
            patient.setMiddleName( target.getMiddleName() );
            patient.setSex( target.getSex() );
            patient.setBirthday( target.getBirthday() );
            if ( target.getNumberOfPolicy() != null ) {
                patient.setNumberOfPolicy( Long.parseLong( target.getNumberOfPolicy() ) );
            }
        }

        return patient;
    }

    @Override
    public DiseaseDTO toTargetEntity(Disease source) {
        if ( source == null ) {
            return null;
        }

        DiseaseDTO diseaseDTO = new DiseaseDTO();

        diseaseDTO.setId( source.getId() );
        diseaseDTO.setNumberOfDisease( source.getNumberOfDisease() );
        diseaseDTO.setStartDisease( source.getStartDisease() );
        diseaseDTO.setEndDisease( source.getEndDisease() );
        diseaseDTO.setPrescription( source.getPrescription() );

        return diseaseDTO;
    }

    @Override
    public Disease toSourceEntity(DiseaseDTO target, DiseaseMapper mapper) {
        if ( target == null && mapper == null ) {
            return null;
        }

        Disease disease = new Disease();

        if ( target != null ) {
            disease.setId( target.getId() );
            disease.setNumberOfDisease( target.getNumberOfDisease() );
            disease.setStartDisease( target.getStartDisease() );
            disease.setEndDisease( target.getEndDisease() );
            disease.setPrescription( target.getPrescription() );
        }

        return disease;
    }
}
