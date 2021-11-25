package com.example.happy.hr.domain.entities;

import com.example.happy.hr.domain.enums.SoftwareComplex;
import com.example.happy.hr.domain.enums.SystemType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "project_type")
@NoArgsConstructor
@Data
public class ProjectType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proj_type_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Length(max = 150)
    @Column(name = "software_complex")
    private SoftwareComplex softwareComplex;

    @Column(name = "is_mvp")
    private Boolean mvp;

    @Length(max = 150)
    @Enumerated(EnumType.STRING)
    @Column(name = "system")
    private SystemType systemType;

    @OneToOne
    @JoinColumn(name = "proj_model_id")
    private ProjectModel projectModel;

    public ProjectType(SoftwareComplex softwareComplex, Boolean mvp,
                       SystemType systemType, ProjectModel projectModel) {
        this.softwareComplex = softwareComplex;
        this.mvp = mvp;
        this.systemType = systemType;
        this.projectModel = projectModel;
    }
}
