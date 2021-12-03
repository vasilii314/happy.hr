package com.example.happy.hr.repositories.custom;

import com.example.happy.hr.controllers.query.params.PageInfo;
import com.example.happy.hr.controllers.query.params.ProjectRegistryFilter;
import com.example.happy.hr.domain.wrappers.ProjectCardWrapper;

import java.util.List;

public interface CustomProjectCardRepository {
    List<ProjectCardWrapper> getProjectCardPage(ProjectRegistryFilter filter, PageInfo pageInfo);
}
