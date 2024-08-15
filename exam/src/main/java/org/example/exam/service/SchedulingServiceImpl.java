package org.example.exam.service;

import org.example.exam.domain.Project;
import org.example.exam.domain.Task;
import org.example.exam.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class SchedulingServiceImpl implements SchedulingService {

    private static final Logger logger = LoggerFactory.getLogger(SchedulingServiceImpl.class);
    private final TaskRepository taskRepository;

    public SchedulingServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void calculateTaskSchedule(Project project) {
        if (project == null || project.getTasks() == null) {
            throw new IllegalArgumentException("Project or tasks cannot be null");
        }

        Map<Task, LocalDate> taskStartDates = new HashMap<>();
        logger.info("=== Project & Task Details ===");

        for (Task task : project.getTasks()) {
            calculateStartDate(task, taskStartDates);
            loggerTaskDetails(task);
        }
    }

    private void calculateStartDate(Task task, Map<Task, LocalDate> taskStartDates) {
        if (taskStartDates.containsKey(task)) {
            updateTaskDates(task, taskStartDates.get(task));
            return;
        }

        LocalDate startDate = LocalDate.now();
        if (task.getDependencies() == null || task.getDependencies().isEmpty()) {
            updateTaskDates(task, startDate);
        } else {
            startDate = findLatestDependencyEndDate(task, taskStartDates);
            updateTaskDates(task, startDate);
        }

        taskStartDates.put(task, startDate);
        taskRepository.save(task);
    }

    private LocalDate findLatestDependencyEndDate(Task task, Map<Task, LocalDate> taskStartDates) {
        LocalDate latestEndDate = LocalDate.now();
        for (Task dependency : task.getDependencies()) {
            if (dependency.getEndDate() == null) {
                calculateStartDate(dependency, taskStartDates);
            }
            latestEndDate = dependency.getEndDate().isAfter(latestEndDate) ? dependency.getEndDate() : latestEndDate;
        }
        return latestEndDate;
    }

    private void updateTaskDates(Task task, LocalDate startDate) {
        task.setStartDate(startDate);
        task.setEndDate(startDate.plusDays(task.getDuration()));
    }

    private void loggerTaskDetails(Task task) {
        logger.info("==============================");
        logger.info("Project Name: {}", task.getProject().getProjectName());
        logger.info("Task Name: {}", task.getTaskName());
        logger.info("Task Duration: {}", task.getDuration());
        logger.info("Task Start Date: {}", task.getStartDate());
        logger.info("Task End Date: {}", task.getEndDate());
    }
}
