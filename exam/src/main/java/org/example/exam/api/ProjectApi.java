package org.example.exam.api;

import org.example.exam.service.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectApi {

    @Autowired
    private SchedulingService schedulingService;

    public void calculateProjectTasksSchedule() {

        schedulingService.calculateTaskSchedule();

    }
}
