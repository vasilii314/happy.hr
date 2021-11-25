package com.example.happy.hr.domain.enums;

public enum SystemType {
    MODERNIZATION("МОДЕРНИЗАЦИЯ"),
    FROM_SCRATCH("С НУЛЯ");

    private final String text;

    SystemType(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
