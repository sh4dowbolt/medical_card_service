package com.suraev.medical_card_service.repository;

import com.suraev.medical_card_service.domain.entity.CodeDisease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeDiseaseRepository extends JpaRepository<CodeDisease, Long> {
    @Override
    List<CodeDisease> findAll();

}
