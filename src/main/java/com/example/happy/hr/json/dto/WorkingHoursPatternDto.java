package com.example.happy.hr.json.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkingHoursPatternDto {

    private Integer id;

    private Time whpFrom;

    private Time whpTill;

    private String overtime;

    private PossibleWorkScheduleDto possibleWorkSchedule;
}
