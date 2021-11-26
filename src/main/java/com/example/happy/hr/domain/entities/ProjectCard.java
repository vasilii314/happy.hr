package com.example.happy.hr.domain.entities;

import com.example.happy.hr.domain.enums.ProjectCardStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "project_card")
@NoArgsConstructor
@Data
public class ProjectCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proj_card_id")
    private Integer id;

    @Length(max = 500)
    @Column(name = "project_name")
    private String projectName;

    @Length(max = 500)
    @Column(name = "proj_client_name")
    private String projClientName;

    @Column(name = "project_stage")
    private String projectStage;

    @Column(name = "functional_direction")
    @Type(type = "com.example.happy.hr.domain.custom.types.CustomStringArrayClass")
    private String[] functionalDirection;

    @Column(name = "subject_area")
    @Type(type = "com.example.happy.hr.domain.custom.types.CustomStringArrayClass")
    private String[] subjectArea;

    @Column(name = "project_description")
    private String projectDescription;

    private String objectives;

    @Type(type = "com.example.happy.hr.domain.custom.types.CustomStringArrayClass")
    private String[] technologies;

    private String stakeholders;

    @Column(name = "completion_date")
    private Date completionDate;

    @Column(name = "people_launch_date")
    private Date peopleLaunchDate;

    @Column(name = "has_gost")
    private Boolean gost;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_status")
    private ProjectCardStatus cardStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_type_id")
    private ProjectType projectType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User cardAuthor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "whp_id")
    private WorkingHoursPattern workingHoursPattern;

    public ProjectCard(String projectName, String projClientName, String projectStage,
                       String[] functionalDirection, String[] subjectArea, String projectDescription,
                       String objectives, String[] technologies, String stakeholders,
                       Date completionDate, Date peopleLaunchDate, Boolean gost,
                       ProjectCardStatus cardStatus, ProjectType projectType, Team team,
                       Location location, User cardAuthor, WorkingHoursPattern workingHoursPattern) {
        this.projectName = projectName;
        this.projClientName = projClientName;
        this.projectStage = projectStage;
        this.functionalDirection = functionalDirection;
        this.subjectArea = subjectArea;
        this.projectDescription = projectDescription;
        this.objectives = objectives;
        this.technologies = technologies;
        this.stakeholders = stakeholders;
        this.completionDate = completionDate;
        this.peopleLaunchDate = peopleLaunchDate;
        this.gost = gost;
        this.cardStatus = cardStatus;
        this.projectType = projectType;
        this.team = team;
        this.location = location;
        this.cardAuthor = cardAuthor;
        this.workingHoursPattern = workingHoursPattern;
    }
}
