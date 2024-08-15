package org.example.exam.mapper;

import org.example.exam.domain.Project;
import org.example.exam.domain.ProjectDto;
import org.example.exam.domain.Task;
import org.example.exam.domain.TaskDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

}
