package com.example.happy.hr.json.dto;

import com.example.happy.hr.domain.enums.ProjectCardStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectCardDto {

    private Integer id;

    @Length(max = 500)
    private String projectName;

    @Length(max = 500)
    private String projClientName;

    private String projectStage;

    private String[] functionalDirection;

    private String[] subjectArea;

    private String projectDescription;

    private String objectives;

    private String[] technologies;

    private String stakeholders;

    private Date completionDate;

    private Date peopleLaunchDate;

    private Boolean gost;

    private ProjectCardStatus cardStatus;

    private ProjectTypeDto projectType;

    private TeamDto team;

    private LocationDto location;

    private WorkingHoursPatternDto workingHoursPattern;

    private UserDto cardAuthor;
}
