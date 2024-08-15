package org.example.exam.mapper;

import org.example.exam.domain.Project;
import org.example.exam.domain.ProjectDto;
import org.example.exam.domain.Task;
import org.example.exam.domain.TaskDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

    @Autowired
    private ModelMapper modelMapper;

    public ProjectDto toDto(Project project) {
        return modelMapper.map(project, ProjectDto.class);
    }

    public Project toEntity(ProjectDto projectDto) {
        return modelMapper.map(projectDto, Project.class);
    }

    public TaskDto toDto(Task task) {
        return modelMapper.map(task, TaskDto.class);
    }

    public List<ProjectDto> toProjectDtoList(List<Project> projects) {
        return projects.stream()
                .map(project -> modelMapper.map(project, ProjectDto.class))
                .collect(Collectors.toList());
    }

    public List<TaskDto> toTaskDtoList(List<Task> tasks) {
        return tasks.stream()
                .map(task -> modelMapper.map(task, TaskDto.class))
                .collect(Collectors.toList());
    }
}
