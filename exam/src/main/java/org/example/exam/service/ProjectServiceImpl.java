package org.example.exam.service;

import org.example.exam.domain.Project;
import org.example.exam.domain.ProjectDto;
import org.example.exam.mapper.Mapper;
import org.example.exam.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private SchedulingService schedulingService;

    @Autowired
    private Mapper mapper;

    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        Project project = mapper.toEntity(projectDto);
        Project savedProject = projectRepository.save(project);
        return mapper.toDto(savedProject);
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDto getProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project does not exist"));
        return mapper.toDto(project);
    }


    @Override
    public ProjectDto calculateTaskSchedule(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project does not exist"));
        schedulingService.calculateTaskSchedule(project);
        return mapper.toDto(project);
    }

}
