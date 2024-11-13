package com.suraev.medical_card_service.repository;

import com.suraev.medical_card_service.domain.entity.CodeDisease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeDiseaseRepository extends JpaRepository<CodeDisease, String> {
}
