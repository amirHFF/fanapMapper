package com.fanap.model;

public enum Gender {
    MALE("MALE","مرد"),
    FEMALE("FEMALE","مرد");

    private String code;
    private String value;

    Gender(String code, String value) {
        this.code=code;
        this.value=value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
