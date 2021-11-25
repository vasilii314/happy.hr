package com.example.happy.hr.domain.enums;

public enum ProjectStage {
    INITIATION("ИНИЦИАЦИЯ"),
    BEGINNING("НАЧАЛО"),
    MIDDLE("СЕРЕДИНА"),
    LAST("ЗАВЕРШАЮЩАЯ");

    private final String text;

    ProjectStage(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
