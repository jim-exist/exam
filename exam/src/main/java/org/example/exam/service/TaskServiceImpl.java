package org.example.exam.service;

import org.example.exam.domain.Task;
import org.example.exam.domain.TaskDto;
import org.example.exam.mapper.Mapper;
import org.example.exam.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private Mapper mapper;


    @Override
    @Transactional
    public Task createTask(Task task) {
        List<Task> dependencies = task.getDependencies();

        if (dependencies != null && !dependencies.isEmpty()) {
            for (Task dependency : dependencies) {
                taskRepository.findById(dependency.getId())
                        .orElseThrow(() -> new IllegalStateException(
                                "Dependent Task ID " + dependency.getId() + " does not exist"));
            }
        }

        return taskRepository.save(task);
    }

    @Override
    public TaskDto getTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task does not exist"));
        return mapper.toDto(task);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

}
