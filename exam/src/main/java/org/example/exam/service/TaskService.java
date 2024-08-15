package org.example.exam.service;

import org.example.exam.domain.Task;
import org.example.exam.domain.TaskDto;

import java.util.List;

public interface TaskService {

    Task createTask(Task task);

    TaskDto getTask(Long id);

    List<TaskDto> getAllTasks();
}
