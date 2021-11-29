package com.example.happy.hr.json.mapper;

import com.example.happy.hr.domain.entities.ProjectCard;
import com.example.happy.hr.domain.entities.Team;
import com.example.happy.hr.json.dto.ProjectCardDto;
import com.example.happy.hr.json.dto.TeamDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface ProjectCardMapper {

    @Mapping(target = "team", expression = "java(toTeamDto(card.getTeam()))")
    ProjectCardDto toProjectCardDto(ProjectCard card);

    ProjectCard toProjectCard(ProjectCardDto dto);

    default TeamDto toTeamDto(Team team) {
        if ( team == null ) {
            return null;
        }

        TeamDto teamDto = new TeamDto();

        teamDto.setId( team.getId() );
        teamDto.setDevMethodology( team.getDevMethodology() );
        teamDto.setProductDevelopment( team.getProductDevelopment() );
        teamDto.setTesters( team.getTesters() );
        teamDto.setTechWriters( team.getTechWriters() );
        teamDto.setAnalystsNum( team.getAnalystsNum() );
        teamDto.setDevsNum( team.getDevsNum() );
        teamDto.setTeamReady( team.getTeamReady() );
        teamDto.setPeopleInTeamNum( team.getPeopleInTeamNum() );
        teamDto.setHasDevs(mapDevsNumToHasDevs(team));
        teamDto.setHasAnalysts(mapAnalystsNumToHasAnalysts(team));

        return teamDto;
    }

    default Boolean mapDevsNumToHasDevs(Team team) {
        if (team == null) {
            return false;
        }
        return team.getDevsNum() != null && team.getDevsNum() > 0;
    }

    default Boolean mapAnalystsNumToHasAnalysts(Team team) {
        if (team == null) {
            return false;
        }
        return team.getDevsNum() != null && team.getAnalystsNum() > 0;
    }
}
