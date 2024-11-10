package com.suraev.medical_card_service.exception;

import lombok.Getter;
import lombok.Setter;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class BadRequestAlertException extends AbstractThrowableProblem {

    private final String entityName;
    private final String errorKey;

    public BadRequestAlertException(String defaultMessage, Status status,String entityName,String errorKey) {
        this(ErrorConstants.DEFAULT_TYPE,defaultMessage, status, entityName,errorKey);
    }
    public BadRequestAlertException(URI type, String defaultMessage, Status status, String entityName, String errorKey) {
        super(type, defaultMessage, status, null, null, null, getAlertParameters(entityName,errorKey));
        this.entityName = entityName;
        this.errorKey = errorKey;
    }
    private static Map<String, Object> getAlertParameters(String entityName, String errorKey) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", "error." + errorKey);
        parameters.put("params", entityName);
        return parameters;
    }

}
