package com.suraev.medical_card_service.util.mapper;

import com.suraev.medical_card_service.domain.entity.Patient;
import com.suraev.medical_card_service.dto.PatientCreateDTO;
import com.suraev.medical_card_service.dto.PatientDTO;
import com.suraev.medical_card_service.dto.PatientUpdateDTO;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-15T20:13:33+0400",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class PatientMapperImpl implements PatientMapper {

    @Autowired
    private JsonNullableMapper jsonNullableMapper;

    @Override
    public Patient map(PatientCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Patient patient = new Patient();

        patient.setFirstName( dto.getFirstName() );
        patient.setLastName( dto.getLastName() );
        patient.setMiddleName( dto.getMiddleName() );
        patient.setSex( dto.getSex() );
        patient.setBirthday( dto.getBirthday() );
        if ( dto.getNumberOfPolicy() != null ) {
            patient.setNumberOfPolicy( Long.parseLong( dto.getNumberOfPolicy() ) );
        }

        return patient;
    }

    @Override
    public PatientDTO map(Patient patient) {
        if ( patient == null ) {
            return null;
        }

        PatientDTO patientDTO = new PatientDTO();

        patientDTO.setId( patient.getId() );
        patientDTO.setFirstName( patient.getFirstName() );
        patientDTO.setLastName( patient.getLastName() );
        patientDTO.setMiddleName( patient.getMiddleName() );
        patientDTO.setSex( patient.getSex() );
        patientDTO.setBirthday( patient.getBirthday() );
        if ( patient.getNumberOfPolicy() != null ) {
            patientDTO.setNumberOfPolicy( String.valueOf( patient.getNumberOfPolicy() ) );
        }

        return patientDTO;
    }

    @Override
    public void update(PatientUpdateDTO dto, Patient patient) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            patient.setId( dto.getId() );
        }
        if ( dto.getFirstName() != null ) {
            patient.setFirstName( jsonNullableMapper.unwrap( dto.getFirstName() ) );
        }
        if ( dto.getLastName() != null ) {
            patient.setLastName( jsonNullableMapper.unwrap( dto.getLastName() ) );
        }
        if ( dto.getMiddleName() != null ) {
            patient.setMiddleName( jsonNullableMapper.unwrap( dto.getMiddleName() ) );
        }
        if ( dto.getSex() != null ) {
            patient.setSex( jsonNullableMapper.unwrap( dto.getSex() ) );
        }
        if ( dto.getBirthday() != null ) {
            patient.setBirthday( jsonNullableMapper.unwrap( dto.getBirthday() ) );
        }
        if ( dto.getNumberOfPolicy() != null ) {
            patient.setNumberOfPolicy( Long.parseLong( jsonNullableMapper.unwrap( dto.getNumberOfPolicy() ) ) );
        }
    }
}
