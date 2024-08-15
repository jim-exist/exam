package org.example.exam.service;

import org.example.exam.domain.Project;
import org.example.exam.domain.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class SchedulingServiceImpl implements SchedulingService {

    private static final Logger logger = LoggerFactory.getLogger(SchedulingServiceImpl.class);

    @Override
    public void calculateTaskSchedule() {
        Project project = createProject();

        if (project == null || project.getTasks() == null) {
            throw new IllegalArgumentException("Project or tasks cannot be null");
        }

        Map<Task, LocalDate> taskStartDates = new HashMap<>();
        logger.info("=== Project & Task Details ===");

        // Schedule tasks
        for (Task task : project.getTasks()) {
            calculateStartDate(task, taskStartDates);
            task.setEndDate(task.getStartDate().plusDays(task.getDuration()));

            // Logging the project and task details
            logger.info("==============================");
            logger.info("Project Name: {}", task.getProject().getName());
            logger.info("Task Name: {}", task.getName());
            logger.info("Task Start Date: {}", task.getStartDate());
            logger.info("Task End Date: {}", task.getEndDate());
        }
    }

    private void calculateStartDate(Task task, Map<Task, LocalDate> taskStartDates) {
        if (taskStartDates.containsKey(task)) {
            task.setStartDate(taskStartDates.get(task));
            return;
        }

        LocalDate startDate = LocalDate.now();
        if (task.getDependencies() != null) {
            LocalDate latestEndDate = startDate;
            for (Task dependency : task.getDependencies()) {
                if (dependency.getEndDate() == null) {
                    calculateStartDate(dependency, taskStartDates);
                }
                latestEndDate = dependency.getEndDate().isAfter(latestEndDate) ? dependency.getEndDate() : latestEndDate;
            }
            startDate = latestEndDate.plusDays(1);
        }

        task.setStartDate(startDate);
        taskStartDates.put(task, startDate);
    }

    public Project createProject() {
        Project project = new Project();
        project.setName("project1");

        List<Task> tasks = createTasks(project);
        project.setTasks(tasks);
        return project;
    }

    private static List<Task> createTasks(Project project) {
        List<Task> tasks = new ArrayList<>();

        Task task1 = new Task();
        task1.setName("task1");
        task1.setDuration(1);
        task1.setProject(project);
        task1.setStartDate(LocalDate.now());
        tasks.add(task1);

        Task task2 = new Task();
        List<Task> dependencies1 = Collections.singletonList(task1);
        task2.setName("task2");
        task2.setDuration(2);
        task2.setProject(project);
        task2.setDependencies(dependencies1);
        tasks.add(task2);

        Task task3 = new Task();
        List<Task> dependencies2 = Arrays.asList(task1, task2);
        task3.setName("task3");
        task3.setDuration(3);
        task3.setProject(project);
        task3.setDependencies(dependencies2);
        tasks.add(task3);

        return tasks;
    }
}
