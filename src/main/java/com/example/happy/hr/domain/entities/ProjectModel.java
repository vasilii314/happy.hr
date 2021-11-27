package com.example.happy.hr.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "project_model")
@NoArgsConstructor
@Data
public class ProjectModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proj_mod_id")
    private Integer id;

    @Column(name = "model_type")
    @NotBlank
    private String projectModelType;

    @Length(max = 300)
    @Column(name = "interviewer_name")
    private String interviewerName;

    @PositiveOrZero
    @Column(name = "num_of_interviews")
    @NotNull
    private Integer numOfInterviews;

    @Length(max = 300)
    @Column(name = "cv_to")
    @NotBlank
    private String cvTo;

    public ProjectModel(String projectModelType, String interviewerName,
                        Integer numOfInterviews, String cvTo) {
        this.projectModelType = projectModelType;
        this.interviewerName = interviewerName;
        this.numOfInterviews = numOfInterviews;
        this.cvTo = cvTo;
    }
}
