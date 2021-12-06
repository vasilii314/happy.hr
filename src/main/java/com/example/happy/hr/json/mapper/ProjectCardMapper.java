package com.example.happy.hr.json.mapper;

import com.example.happy.hr.domain.entities.*;
import com.example.happy.hr.json.dto.PossibleWorkScheduleDto;
import com.example.happy.hr.json.dto.ProjectCardDto;
import com.example.happy.hr.json.dto.TeamDto;
import com.example.happy.hr.json.dto.WorkingHoursPatternDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.sql.Time;

@Mapper(componentModel = "spring")
public interface ProjectCardMapper {

    @Mapping(target = "team", expression = "java(toTeamDto(card.getTeam()))")
    @Mapping(target = "completionDate",
            expression = "java(card.getCompletionDate() == null ? null : card.getCompletionDate().toString())")
    @Mapping(target = "peopleLaunchDate",
            expression = "java(card.getPeopleLaunchDate() == null ? null : card.getPeopleLaunchDate().toString())")
    @Mapping(target = "workingHoursPattern", expression = "java(toWorkingHoursPatternDto(card.getWorkingHoursPattern()))")
    ProjectCardDto toProjectCardDto(ProjectCard card);

    @Mapping(target = "completionDate",
            expression = "java(dto.getCompletionDate() == null || dto.getCompletionDate().trim() == \"\" ? null : java.sql.Date.valueOf(dto.getCompletionDate()))")
    @Mapping(target = "peopleLaunchDate",
            expression = "java(dto.getPeopleLaunchDate() == null || dto.getPeopleLaunchDate().trim() == \"\" ? null : java.sql.Date.valueOf(dto.getPeopleLaunchDate()))")
    @Mapping(target = "workingHoursPattern", expression = "java(toWorkingHoursPattern(dto.getWorkingHoursPattern()))")
    @Mapping(target = "cardStatus", expression = "java(evalCardStatus(dto))")
    ProjectCard toProjectCard(ProjectCardDto dto);

    default String evalCardStatus(ProjectCardDto dto) {
        if (dto.getProjectType() != null
                && dto.getProjectType().getProjectModel() != null
                && dto.getProjectType().getProjectModel().getProjectModelType() != null
                && dto.getProjectType().getSoftwareComplex() != null
                && dto.getProjectType().getSystemType() != null
                && dto.getProjectType().getMvp() != null
                && dto.getProjectName() != null
                && dto.getProjClientName() != null
                && dto.getGost() != null
                && dto.getProjectStage() != null
        ) {
            return "Активна";
        }
        return "Черновик";
    }

    default WorkingHoursPattern toWorkingHoursPattern(WorkingHoursPatternDto patternDto) {
        if (patternDto == null) {
            return null;
        }

        WorkingHoursPattern pattern = new WorkingHoursPattern();

        pattern.setId(patternDto.getId());
        pattern.setWhpFrom(patternDto.getWhpFrom() == null ? null : Time.valueOf(patternDto.getWhpFrom()));
        pattern.setWhpTill(patternDto.getWhpTill() == null ? null : Time.valueOf(patternDto.getWhpTill()));
        pattern.setOvertime(patternDto.getOvertime());
        pattern.setPossibleWorkSchedule(toPossibleWorkSchedule(patternDto.getPossibleWorkSchedule()));

        return pattern;
    }

    default PossibleWorkSchedule toPossibleWorkSchedule(PossibleWorkScheduleDto scheduleDto) {
        if (scheduleDto == null) {
            return null;
        }

        PossibleWorkSchedule schedule = new PossibleWorkSchedule();

        schedule.setId(scheduleDto.getId());
        schedule.setScheduleDescription(scheduleDto.getScheduleDescription());
        schedule.setFlextime(scheduleDto.getFlextime());

        return schedule;
    }


    default WorkingHoursPatternDto toWorkingHoursPatternDto(WorkingHoursPattern pattern) {
        if (pattern == null) {
            return null;
        }

        WorkingHoursPatternDto patternDto = new WorkingHoursPatternDto();

        patternDto.setId(pattern.getId());
        patternDto.setWhpFrom(pattern.getWhpFrom() == null ? null : pattern.getWhpFrom().toString());
        patternDto.setWhpTill(pattern.getWhpTill() == null ? null : pattern.getWhpTill().toString());
        patternDto.setOvertime(pattern.getOvertime());
        patternDto.setPossibleWorkSchedule(toPossibleWorkScheduleDto(pattern.getPossibleWorkSchedule()));

        return patternDto;
    }

    default PossibleWorkScheduleDto toPossibleWorkScheduleDto(PossibleWorkSchedule schedule) {
        if (schedule == null) {
            return null;
        }

        PossibleWorkScheduleDto scheduleDto = new PossibleWorkScheduleDto();

        scheduleDto.setId(schedule.getId());
        scheduleDto.setScheduleDescription(schedule.getScheduleDescription());
        scheduleDto.setFlextime(schedule.getFlextime());

        return scheduleDto;
    }

    default TeamDto toTeamDto(Team team) {

        if (team == null) {
            return null;
        }

        TeamDto teamDto = new TeamDto();

        teamDto.setId(team.getId());
        teamDto.setDevMethodology(team.getDevMethodology());
        teamDto.setProductDevelopment(team.getProductDevelopment());
        teamDto.setTesters(team.getTesters());
        teamDto.setTechWriters(team.getTechWriters());
        teamDto.setAnalystsNum(team.getAnalystsNum());
        teamDto.setDevsNum(team.getDevsNum());
        teamDto.setTeamReady(team.getTeamReady());
        teamDto.setPeopleInTeamNum(team.getPeopleInTeamNum());
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
        return team.getAnalystsNum() != null && team.getAnalystsNum() > 0;
    }
}
