package com.example.happy.hr.services.impl;

import com.example.happy.hr.controllers.query.params.PageInfo;
import com.example.happy.hr.controllers.query.params.ProjectRegistryFilter;
import com.example.happy.hr.domain.entities.ProjectCard;
import com.example.happy.hr.json.dto.ProjectCardDto;
import com.example.happy.hr.json.dto.auxiliary.ProjectCardInfo;
import com.example.happy.hr.json.mapper.ProjectCardMapper;
import com.example.happy.hr.repositories.ProjectCardRepository;
import com.example.happy.hr.services.ProjectCardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectCardServiceImpl implements ProjectCardService {

    private ProjectCardRepository projectCardRepository;
    private ProjectCardMapper projectCardMapper;

    @Override
    public List<ProjectCardInfo> getProjectCardPage(ProjectRegistryFilter filter, PageInfo info) {

        String urlPrefix = "http://localhost:8080/api/cards";

        return projectCardRepository
                .getProjectCardPage(filter, info)
                .stream()
                .map(wrapper -> new ProjectCardInfo(
                        wrapper,
                        urlPrefix + wrapper.getId(),
                        urlPrefix + "/archive/" + wrapper.getId(),
                        urlPrefix + "/archive/" + wrapper.getId()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ProjectCardDto getProjectCardById(Integer projectCardId) {
        ProjectCardDto projectCardDto = projectCardMapper
                .toProjectCardDto(
                        projectCardRepository.getById(projectCardId)
                );
        if (projectCardDto == null) {
            throw new RuntimeException("Project card not found");
        }
        return projectCardDto;
    }

    @Override
    public void save(ProjectCardDto cardDto) {
        ProjectCard projectCard = projectCardMapper.toProjectCard(cardDto);
        projectCardRepository.save(projectCard);
    }

    @Override
    @Transactional
    public void archiveById(Integer id) {
        projectCardRepository.archiveById(id);
    }

    @Override
    @Transactional
    public void unarchiveById(Integer id) {
        projectCardRepository.unarchiveById(id);
    }

    @Override
    public void deleteById(Integer id) {
        projectCardRepository.deleteById(id);
    }
}
