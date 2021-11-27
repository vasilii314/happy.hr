package com.example.happy.hr.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "project_type")
@NoArgsConstructor
@Data
public class ProjectType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proj_type_id")
    private Integer id;

    @Column(name = "software_complex")
    @NotBlank
    private String softwareComplex;

    @Column(name = "is_mvp")
    @NotNull
    private Boolean mvp;

    @Column(name = "system")
    @NotBlank
    private String systemType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "proj_model_id")
    @NotNull
    private ProjectModel projectModel;

    public ProjectType(String softwareComplex, Boolean mvp,
                       String systemType, ProjectModel projectModel) {
        this.softwareComplex = softwareComplex;
        this.mvp = mvp;
        this.systemType = systemType;
        this.projectModel = projectModel;
    }
}
