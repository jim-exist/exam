package org.example.exam.service;

import org.example.exam.domain.ProjectDto;

import java.util.List;

public interface ProjectService {

    ProjectDto createProject(ProjectDto projectDto);

    List<ProjectDto> getAllProjects();

    ProjectDto getProject(Long id);

    ProjectDto calculateTaskSchedule(Long id);
}

