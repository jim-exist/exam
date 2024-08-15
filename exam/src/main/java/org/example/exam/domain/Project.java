package org.example.exam.domain;


import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class Project {

    private Long id;
    private String name;
    private List<Task> tasks;

}
