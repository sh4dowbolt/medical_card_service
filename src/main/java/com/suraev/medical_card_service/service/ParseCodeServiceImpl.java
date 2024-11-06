package com.suraev.medical_card_service.service;

import org.springframework.beans.factory.annotation.Value;

public class ParseCodeServiceImpl {
    @Value("${source_link_for_update_code_dictionary}")
    private String link;

}
