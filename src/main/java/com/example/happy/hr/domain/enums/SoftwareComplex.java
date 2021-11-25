package com.example.happy.hr.domain.enums;

public enum SoftwareComplex {
    SOFTWARE("ПО"),
    SOFTWARE_COMPLEX("ПАК");

    private final String text;

    SoftwareComplex(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
