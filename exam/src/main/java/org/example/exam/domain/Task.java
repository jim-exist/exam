package org.example.exam.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Task {

    private Long id;
    private String name;
    private int duration; // Duration in days
    private LocalDate startDate;
    private LocalDate endDate;
    private Project project;
    private List<Task> dependencies;

}
