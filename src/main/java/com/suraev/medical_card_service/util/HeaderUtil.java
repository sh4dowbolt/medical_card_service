package com.suraev.medical_card_service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

public class HeaderUtil {
    private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);
    private HeaderUtil() {}

    public static HttpHeaders createAlert(String applicationName, String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-"+applicationName + "-alert", message);
        return headers;
    }

    public static HttpHeaders createEntityCreationAlert(String applicationName, String entityName, String param) {
        String message = "A new"+entityName+" is created with identifier "+param;
        return createAlert(applicationName, message, param);
    }

    public static HttpHeaders createEntityUpdateAlert(String applicationName, String entityName, String param) {
        String message = "A "+entityName+" is updated with identifier "+param;
        return createAlert(applicationName, message, param);
    }

    public static HttpHeaders createEntityDeletionAlert(String applicationName, String entityName, String param) {
        String message = "A "+entityName+" is deleted with identifier "+param;
        return createAlert(applicationName, message, param);
    }

    public static HttpHeaders createFailureAlert(String applicationName, boolean enableTranslation, String entityName, String errorKey, String defaultMessage) {
        log.error("Entity processing failed, {}", defaultMessage);
        String message = enableTranslation ? "error." +errorKey : defaultMessage;
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-"+applicationName+"-error", message);
        headers.add("X-"+applicationName+"-params", entityName);
        return headers;
    }
}
