package com.suraev.medical_card_service.exception;

import java.net.URI;

public class ErrorConstants {
        public static final String ERR_VALIDATION = "error.validation";
        public static final URI DEFAULT_TYPE = URI.create("");
        public static final URI CONSTRAINT_VIOLATION_TYPE = URI.create("constraint-violation");
}
