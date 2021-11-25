package com.example.happy.hr.domain.enums;

public enum LocationType {
    OFFICE("ОФИС"),
    TELEWORKING("УДАЛЕННО");

    private final String text;

    LocationType(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
