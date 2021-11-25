package com.example.happy.hr.domain.entities;

import com.example.happy.hr.domain.enums.DevMethodology;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "team")
@NoArgsConstructor
@Data
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "dev_method")
    private DevMethodology devMethodology;

    @Column(name = "is_product_dev")
    private Boolean productDevelopment;

    @Column(name = "has_testers")
    private Boolean testers;

    @Column(name = "has_technical_writers")
    private Boolean techWriters;

    @PositiveOrZero
    @Column(name = "analysts_num")
    private Integer analystsNum;

    @PositiveOrZero
    @Column(name = "devs_num")
    private Integer devsNum;

    @Column(name = "team_ready")
    private Boolean teamReady;

    @PositiveOrZero
    @Column(name = "people_in_team_num")
    private Integer peopleInTeamNum;

    public Team(DevMethodology devMethodology, Boolean productDevelopment,
                Boolean testers, Boolean techWriters,
                Integer analystsNum, Integer devsNum,
                Boolean teamReady, Integer peopleInTeamNum) {
        this.devMethodology = devMethodology;
        this.productDevelopment = productDevelopment;
        this.testers = testers;
        this.techWriters = techWriters;
        this.analystsNum = analystsNum;
        this.devsNum = devsNum;
        this.teamReady = teamReady;
        this.peopleInTeamNum = peopleInTeamNum;
    }
}
