package com.jordifierro.androidbase.domain.entity;

public class VersionEntity {

    private String expirationDate;

    public VersionEntity() {}

    public VersionEntity(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

}
