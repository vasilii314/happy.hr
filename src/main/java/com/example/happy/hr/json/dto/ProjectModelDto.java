package com.example.happy.hr.json.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectModelDto {

    private Integer id;

    private String projectModelType;

    @Length(max = 300)
    @NotBlank
    private String interviewerName;

    @PositiveOrZero
    @NotNull
    private Integer numOfInterviews;

    @Length(max = 300)
    @NotBlank
    private String cvTo;
}
