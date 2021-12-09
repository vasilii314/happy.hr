package com.example.happy.hr.services;

import com.example.happy.hr.controllers.query.params.PageInfo;
import com.example.happy.hr.controllers.query.params.ProjectRegistryFilter;
import com.example.happy.hr.controllers.query.params.SortInfo;
import com.example.happy.hr.json.dto.ProjectCardDto;
import com.example.happy.hr.json.dto.auxiliary.ProjectCardInfo;

import java.util.List;
import java.util.Map;

public interface ProjectCardService {

    List<ProjectCardInfo> getProjectCardPage(ProjectRegistryFilter filter, PageInfo info, Map<String, SortInfo> sortInfo);

    ProjectCardDto getProjectCardById(Integer projectCardId);

    void save(ProjectCardDto cardDto);

    void archiveById(Integer id);

    void unarchiveById(Integer id);

    void deleteById(Integer id);
}
