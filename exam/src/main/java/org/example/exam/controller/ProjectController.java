package org.example.exam.controller;

import org.example.exam.domain.ProjectDto;
import org.example.exam.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
        return ResponseEntity.ok(projectService.createProject(projectDto));
    }

    @GetMapping("/all-projects")
    public ResponseEntity<List<ProjectDto>> getAllProject() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProject(id));
    }

    @GetMapping("/calculate-project-tasks-schedule/{id}")
    public ResponseEntity<ProjectDto> calculateProjectTasksSchedule(@PathVariable Long id) {
        ProjectDto projectDto = projectService.calculateTaskSchedule(id);
        return ResponseEntity.ok(projectDto);
    }
}
