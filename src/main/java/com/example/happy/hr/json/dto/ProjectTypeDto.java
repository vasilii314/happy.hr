package com.example.happy.hr.json.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectTypeDto {

    private Integer id;

    private String softwareComplex;

    private Boolean mvp;

    private String systemType;

    private ProjectModelDto projectModel;
}
