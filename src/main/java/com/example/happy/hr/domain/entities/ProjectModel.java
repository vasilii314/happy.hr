package com.example.happy.hr.domain.entities;

import com.example.happy.hr.domain.enums.ProjectModelType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
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

    @Length(max = 150)
    @Enumerated(EnumType.STRING)
    @Column(name = "model_type")
    private ProjectModelType modelType;

    @Length(max = 300)
    @Column(name = "interviewer_name")
    private String interviewerName;

    @PositiveOrZero
    @Column(name = "num_of_interviews")
    private Integer numOfInterviews;

    @Length(max = 300)
    @Column(name = "cv_to")
    private String cvTo;

    public ProjectModel(ProjectModelType modelType, String interviewerName,
                        Integer numOfInterviews, String cvTo) {
        this.modelType = modelType;
        this.interviewerName = interviewerName;
        this.numOfInterviews = numOfInterviews;
        this.cvTo = cvTo;
    }
}
