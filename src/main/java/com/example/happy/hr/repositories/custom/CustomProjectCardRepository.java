package com.example.happy.hr.repositories.custom;

import com.example.happy.hr.controllers.query.params.PageInfo;
import com.example.happy.hr.controllers.query.params.ProjectRegistryFilter;
import com.example.happy.hr.controllers.query.params.SortInfo;
import com.example.happy.hr.domain.wrappers.ProjectCardWrapper;

import java.util.List;
import java.util.Map;

public interface CustomProjectCardRepository {
    List<ProjectCardWrapper> getProjectCardPage(ProjectRegistryFilter filter, PageInfo pageInfo, Map<String, SortInfo> sortInfo);
    ProjectCardWrapper getRegistryRecordById(Integer id);
    Long count(ProjectRegistryFilter filter);
}
