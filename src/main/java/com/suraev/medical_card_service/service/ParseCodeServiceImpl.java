package com.suraev.medical_card_service.service;

import com.suraev.medical_card_service.domain.entity.CodeDisease;
import com.suraev.medical_card_service.util.CSVParser;
import com.suraev.medical_card_service.util.DownloadFileUtil;
import com.suraev.medical_card_service.util.eventListener.CustomEventPublisher;
import com.suraev.medical_card_service.util.eventListener.UpdateEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("!test")
@RequiredArgsConstructor
public class ParseCodeServiceImpl implements ParseCodeService {
    @Value("${source_link_for_update_code_dictionary}")
    private String target;
    @Value("${file.upload-dir}")
    private String source;

    private final CustomEventPublisher applicationEventPublisher;

    private Logger log = LoggerFactory.getLogger(ParseCodeServiceImpl.class);

    @Scheduled(cron = "#{@cronForUpdate}")
    @Async
    public void scheduledUpdate() {
        publishUpdateEvent();
    }
    @EventListener(ContextRefreshedEvent.class)
    @Async
    public void initCodeDiseaseDatabaseWhenAppIsUp(){
        publishUpdateEvent();
    }
    @Override
    @Async
    public void publishUpdateEvent() {
        prepareCodeDictionaryList();
    }
    private void prepareCodeDictionaryList(){
        DownloadFileUtil.downloadFile(target,source);
        log.info("The file for CodeDiseaseDB has downloaded");
        List<CodeDisease> codeDiseaseList = CSVParser.parse(source);
        log.info("The file for CodeDiseaseDB has parsed by CSVParser. The CSVParser has returned a codeDiseaseList");
        applicationEventPublisher.publishEvent(new UpdateEvent(codeDiseaseList));
        log.info("The publisher has done");
    }
}
