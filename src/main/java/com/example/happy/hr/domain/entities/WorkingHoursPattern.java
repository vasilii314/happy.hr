package com.example.happy.hr.domain.entities;

import com.example.happy.hr.domain.enums.Overtime;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "working_hours_pattern")
@Data
@NoArgsConstructor
public class WorkingHoursPattern {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "whp_id")
    private Integer id;

    @Column(name = "whp_from")
    private Time whpFrom;

    @Column(name = "whp_till")
    private Time whpTill;

    @Enumerated(EnumType.STRING)
    @Column(name = "overtime")
    @Length(max = 150)
    private Overtime overtime;

    @OneToOne
    @JoinColumn(name = "possible_work_schedule_id")
    private PossibleWorkSchedule possibleWorkSchedule;

    public WorkingHoursPattern(Time whpFrom,
                               Time whpTill,
                               Overtime overtime,
                               PossibleWorkSchedule possibleWorkSchedule) {
        this.whpFrom = whpFrom;
        this.whpTill = whpTill;
        this.overtime = overtime;
        this.possibleWorkSchedule = possibleWorkSchedule;
    }
}
