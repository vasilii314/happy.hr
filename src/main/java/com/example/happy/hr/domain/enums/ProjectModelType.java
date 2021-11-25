package com.example.happy.hr.domain.enums;

public enum ProjectModelType {
    FIXED("ФИКСИРОВАННАЯ"),
    TM("T&M");

    private final String text;

    ProjectModelType(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
