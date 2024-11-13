package com.suraev.medical_card_service.service;

import com.suraev.medical_card_service.domain.entity.CodeDisease;
import com.suraev.medical_card_service.repository.CodeDiseaseRepository;
import com.suraev.medical_card_service.util.eventListener.UpdateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;



@Service
@RequiredArgsConstructor
public class UpdateCodeServiceImpl implements UpdateCodeService {

    private final CodeDiseaseRepository codeDiseaseRepository;
    private Logger log = LoggerFactory.getLogger(UpdateCodeServiceImpl.class);


    @EventListener
    @CacheEvict(value = "codeDiseases",allEntries = true)
    public void handle(UpdateEvent event) {
        log.info("ЗАШЕЛ В АПДЕЙТ");
       if(!isListOfCodeDisease(event)) {
           var listOfCodeDiseases = (List<CodeDisease>)event.getSource();
           codeDiseaseRepository.saveAll(listOfCodeDiseases);
           log.info("СОХРАНИЛ КОДЫ ");
       }
    }

   private boolean isListOfCodeDisease(Object object) {
        if(object instanceof List<?> list) {
            return !list.isEmpty() && list.get(0) instanceof CodeDisease;
        }
        return false;
    }
}
