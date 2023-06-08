package hwr.oop.application.createtask;

import hwr.oop.application.Project;
import hwr.oop.application.Task;
import hwr.oop.application.TaskState;
import hwr.oop.application.User;

import java.time.LocalDateTime;

public interface CreateTaskUseCase {
    Task createTaskInProject(String title, String content, TaskState taskState, LocalDateTime deadLine, Project project);
    Task createTaskInContextList(String title, String content, TaskState taskState, LocalDateTime deadLine, User user);
}
