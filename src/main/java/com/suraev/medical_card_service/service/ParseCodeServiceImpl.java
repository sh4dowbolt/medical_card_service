package com.suraev.medical_card_service.service;

import com.suraev.medical_card_service.domain.entity.CodeDisease;
import com.suraev.medical_card_service.util.CSVParser;
import com.suraev.medical_card_service.util.DownloadFileUtil;
import com.suraev.medical_card_service.util.eventListener.UpdateEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParseCodeServiceImpl implements ParseCodeService {
    @Value("${source_link_for_update_code_dictionary}")
    private String target;
    @Value("${path_to_save_files}")
    private String source;

    private ApplicationEventPublisher applicationEventPublisher;


    @Override
    public void prepareCodeDictionaryList() throws MalformedURLException {
        DownloadFileUtil.downloadFile(target,source);
        List<CodeDisease> codeDiseaseList = CSVParser.parse(source);
        applicationEventPublisher.publishEvent(new UpdateEvent(codeDiseaseList));
    }
}
