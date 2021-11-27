package com.example.happy.hr.json.mapper;

import com.example.happy.hr.domain.entities.ProjectCard;
import com.example.happy.hr.json.dto.ProjectCardDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProjectCardMapper {

    ProjectCardDto toProjectCardDto(ProjectCard card);

    ProjectCard toProjectCard(ProjectCardDto dto);
}
