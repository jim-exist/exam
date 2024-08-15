Project Summary:
1. We need to calculate calendar schedules for project plans
2. Each project plan consists of tasks. Every task has a certain duration.
3. A task can depend on zero or more other tasks. If a task depends on some other tasks, it can only be started after these tasks are completed
4. So, for a set of tasks (with durations and dependencies), the solution for the challenge should generate a schedule, i.e. assign Start and End Dates for every task
5. It is ok to have a console app


Setup to run the application:
1. Using maven and Java 8 minimum
2. Open application.properties, Update spring.datasource.username=root and update spring.datasource.url if you have different port for mysql
3. Create new database with name:exam
4. Run the following command: mvn clean install
5. Run command "mvn spring-boot:run" to run the program or run the application by clicking the RUN button or simply [Shift] + [F10]


API endpoints:
1. POST (Create Project) -  http://localhost:8080/api/project
2. GET (Get All Project) - http://localhost:8080/api/project/all-projects
3. GET (Get Project) - http://localhost:8080/api/project/{id}
4. GET (Calculate Project Task Schedules) - http://localhost:8080/api/project/calculate-project-tasks-schedule/{id}
5. POST (Create Task) - http://localhost:8080/api/task
6. GET (Get Task) - http://localhost:8080/api/task/{id}
7. GET (Get All Tasks) - http://localhost:8080/api/task/all-tasks


Steps suggested for testing:
1. Use the create project POST method 
2. Use the create task POST method, create multiple tasks and each dependencies, and assign them to the project you created in step 1.
3. Use the Calculate Project Task Schedules GET method to assign start date and end date according to the dependencies of the tasks in a specific project.
4. Results will be shown in the console logs and responses in Postman, and they will also be saved to the database
5. Below is an API postman collection that can be used as a reference when testing APIs.


[BPI - Exam.postman_collection.json](https://github.com/user-attachments/files/16627122/BPI.-.Exam.postman_collection.json)
