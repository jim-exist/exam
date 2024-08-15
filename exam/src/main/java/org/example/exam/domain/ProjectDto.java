package org.example.exam.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectDto {
    private Long id;
    private String projectName;
    private List<TaskDto> tasks;
}
