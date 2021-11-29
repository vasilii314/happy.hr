package com.example.happy.hr.json.mapper;

import com.example.happy.hr.domain.entities.ProjectCard;
import com.example.happy.hr.domain.entities.Team;
import com.example.happy.hr.json.dto.TeamDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    @Mapping(target = "hasDevs", expression = "java(mapDevNumToHasDevs(team))")
    TeamDto toTeamDto(Team team);

    Team toTeam(TeamDto dto);

    default Boolean mapDevNumToHasDevs(Team team) {
        if (team == null) {
            return false;
        }
        return team.getDevsNum() != null && team.getDevsNum() > 0;
    }
}
