package com.suraev.medical_card_service.repository;

import com.suraev.medical_card_service.domain.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
