package com.jordifierro.androidbase.domain.entity;

public class VersionEntity {

    public static final String VERSION_OK = "OK";
    public static final String VERSION_WARNED = "WARNED";
    public static final String VERSION_EXPIRED = "EXPIRED";

    private String state;

    public VersionEntity() {}

    public VersionEntity(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isWarned() {
        return state == VERSION_WARNED;
    }

    public boolean hasExpired() {
        return state == VERSION_EXPIRED;
    }

}
