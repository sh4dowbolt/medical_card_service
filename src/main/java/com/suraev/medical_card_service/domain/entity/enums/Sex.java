package com.suraev.medical_card_service.domain.entity.enums;

public enum Sex  {

    F ("FEMALE"),
    M ("MALE");
    private final String sex;

    Sex(String sex) {
        this.sex = sex;
    }
    public String getSex() {
        return sex;
    }

}
