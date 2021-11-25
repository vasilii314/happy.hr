package com.example.happy.hr.domain.enums;

public enum Overtime {
    NO("НЕТ"),
    SOMETIMES("ИНОГДА"),
    CONSTANTLY("ПОСТОЯННО");

    private final String text;

    Overtime(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
