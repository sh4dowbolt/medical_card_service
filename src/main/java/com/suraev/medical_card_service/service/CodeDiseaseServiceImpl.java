package com.suraev.medical_card_service.service;

import com.suraev.medical_card_service.domain.entity.CodeDisease;
import com.suraev.medical_card_service.repository.CodeDiseaseRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CodeDiseaseServiceImpl implements CodeDiseaseService{
    private final CodeDiseaseRepository codeDiseaseRepository;
    @Override
    public List<CodeDisease> getAllCodeDiseases() {
        return codeDiseaseRepository.findAll();
    }
}
