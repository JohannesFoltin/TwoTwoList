package hwr.oop.inports;


import hwr.oop.application.Project;
import hwr.oop.application.Task;
import hwr.oop.application.User;

public interface DeleteTaskUseCase {
    void deleteTaskFromProject(Task task, Project project);

    void deleteTaskFromContextList(Task task, User user);

}
