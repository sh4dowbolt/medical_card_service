package com.suraev.medical_card_service.service;

import com.suraev.medical_card_service.domain.entity.CodeDisease;
import com.suraev.medical_card_service.repository.CodeDiseaseRepository;
import com.suraev.medical_card_service.util.CSVParser;
import com.suraev.medical_card_service.util.DownloadFileUtil;
import com.suraev.medical_card_service.util.eventListener.CustomEventPublisher;
import com.suraev.medical_card_service.util.eventListener.UpdateEvent;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ParseCodeServiceImpl implements ParseCodeService {
    @Value("${source_link_for_update_code_dictionary}")
    private String target;
    @Value("${file.upload-dir}")
    private String source;

    private final CustomEventPublisher applicationEventPublisher;

    private Logger log = LoggerFactory.getLogger(ParseCodeServiceImpl.class);

    @PostConstruct
    public void init() throws MalformedURLException {
        log.info("метод для инициализации вызван");
        publishEvent();
        log.info("метод по инициализации закончил работу");
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduledTask() {
        publishEvent();
    }
    @Async
    public void publishEvent() {
        try {
            prepareCodeDictionaryList();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    public void prepareCodeDictionaryList() throws MalformedURLException {
        DownloadFileUtil.downloadFile(target,source);
        log.info("Скачал файл по ссылке");
        List<CodeDisease> codeDiseaseList = CSVParser.parse(source);
        log.info("Распарсил документ и вернул лист для публикатора");
        applicationEventPublisher.publishEvent(new UpdateEvent(codeDiseaseList));
        log.info("Опубликовал данные");
    }
}
