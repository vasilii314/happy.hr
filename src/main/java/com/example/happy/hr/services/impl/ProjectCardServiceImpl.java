package com.example.happy.hr.services.impl;

import com.example.happy.hr.controllers.query.params.PageInfo;
import com.example.happy.hr.controllers.query.params.ProjectRegistryFilter;
import com.example.happy.hr.controllers.query.params.SortInfo;
import com.example.happy.hr.domain.entities.ProjectCard;
import com.example.happy.hr.json.dto.ProjectCardDto;
import com.example.happy.hr.json.dto.auxiliary.ProjectCardInfo;
import com.example.happy.hr.json.mapper.ProjectCardMapper;
import com.example.happy.hr.repositories.ProjectCardRepository;
import com.example.happy.hr.services.ProjectCardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProjectCardServiceImpl implements ProjectCardService {

    private ProjectCardRepository projectCardRepository;
    private ProjectCardMapper projectCardMapper;

    @Override
    public List<ProjectCardInfo> getProjectCardPage(ProjectRegistryFilter filter, PageInfo info, Map<String, SortInfo> sortInfo) {

        log.info("Getting project registry page " + info.getPageNum());

        String urlPrefix = "http://localhost:8080/api/cards";

        return projectCardRepository
                .getProjectCardPage(filter, info, sortInfo)
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
        log.info("Getting card " + projectCardId);

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
        log.info("Saving card: {}", cardDto);
        ProjectCard projectCard = projectCardMapper.toProjectCard(cardDto);
        projectCardRepository.save(projectCard);
    }

    @Override
    @Transactional
    public void archiveById(Integer id) {
        log.info("Archiving card " + id);
        projectCardRepository.archiveById(id);
    }

    @Override
    @Transactional
    public void unarchiveById(Integer id) {
        log.info("Unarchiving card " + id);
        projectCardRepository.unarchiveById(id);
    }

    @Override
    public void deleteById(Integer id) {
        log.info("Deleting card " + id);
        projectCardRepository.deleteById(id);
    }
}
