package org.example.exam.controller;

import org.example.exam.domain.Task;
import org.example.exam.domain.TaskDto;
import org.example.exam.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @GetMapping("/all-tasks")
    public ResponseEntity<List<TaskDto>> getAllProject() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }
}
