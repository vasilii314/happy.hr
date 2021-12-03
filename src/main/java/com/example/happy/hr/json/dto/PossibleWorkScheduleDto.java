package com.example.happy.hr.json.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PossibleWorkScheduleDto {

    private Integer id;

    private Boolean flextime;

    private String scheduleDescription;
}
