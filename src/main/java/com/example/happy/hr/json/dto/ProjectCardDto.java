package com.example.happy.hr.json.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectCardDto {

    private Integer id;

    @Length(max = 500)
    @NotBlank
    private String projectName;

    @Length(max = 500)
    @NotBlank
    private String projClientName;

    @NotBlank
    private String projectStage;

    private String[] functionalDirection;

    private String[] subjectArea;

    @NotBlank
    private String projectDescription;

    private String objectives;

    private String[] technologies;

    private String stakeholders;

    private String completionDate;

    private String peopleLaunchDate;

    private Boolean gost;

    private String cardStatus;

    @NotNull
    private ProjectTypeDto projectType;

    private TeamDto team;

    private LocationDto location;

    private WorkingHoursPatternDto workingHoursPattern;

    private UserDto cardAuthor;
}
