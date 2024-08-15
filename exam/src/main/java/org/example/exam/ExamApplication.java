package org.example.exam;

import org.example.exam.api.ProjectApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ExamApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(ExamApplication.class, args);
        ProjectApi projectApi = context.getBean(ProjectApi.class);

        projectApi.calculateProjectTasksSchedule();

    }

}
