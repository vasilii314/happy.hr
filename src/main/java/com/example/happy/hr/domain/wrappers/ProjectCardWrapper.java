package com.example.happy.hr.domain.wrappers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectCardWrapper {

    private Integer id;

    private String projectName;

    private String projClientName;

    private String cardAuthor;

    private String cardStatus;

    private String[] functionalDirection;

    private String[] subjectArea;

    private String projectStage;
}
