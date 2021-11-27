package com.example.happy.hr.json.mapper;

import com.example.happy.hr.domain.entities.WorkingHoursPattern;
import com.example.happy.hr.json.dto.WorkingHoursPatternDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkingHoursPatternMapper {

    WorkingHoursPatternDto toWorkingHoursPatternDto(WorkingHoursPattern pattern);

    WorkingHoursPattern toWorkingHOursPattern(WorkingHoursPatternDto dto);
}
