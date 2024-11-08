package com.suraev.medical_card_service.service;

import com.suraev.medical_card_service.domain.entity.CodeDisease;
import com.suraev.medical_card_service.repository.CodeDiseaseRepository;
import com.suraev.medical_card_service.util.eventListener.UpdateEvent;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

public class UpdateCodeServiceImpl {

    private CodeDiseaseRepository codeDiseaseRepository;



    @EventListener
    @Transactional
    @CacheEvict(value = "codeDiseases",allEntries = true)
    public void handle(UpdateEvent event) {
       if(isListOfCodeDisease(event)) {
           var listOfCodeDiseases = (List<CodeDisease>)event.getSource();
           codeDiseaseRepository.saveAll(listOfCodeDiseases);
       }
    }

    public static boolean isListOfCodeDisease(Object object) {
        if(object instanceof List<?> list) {
            return !list.isEmpty() && list.get(0) instanceof CodeDisease;
        }
        return false;
    }
}
