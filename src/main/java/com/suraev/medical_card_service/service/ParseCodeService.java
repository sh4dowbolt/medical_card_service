package com.suraev.medical_card_service.service;

import com.suraev.medical_card_service.domain.entity.CodeDisease;
import lombok.Value;

import java.net.MalformedURLException;
import java.util.List;

public interface ParseCodeService {
     void prepareCodeDictionaryList() throws MalformedURLException;
}
