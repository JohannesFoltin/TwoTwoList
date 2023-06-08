package hwr.oop.application.createproject;

import hwr.oop.application.Project;
import hwr.oop.application.Task;
import hwr.oop.application.User;

import java.util.List;

public interface CreateProjectUseCase {
    Project createProject(String title, List<Task> taskList, User user);
}
