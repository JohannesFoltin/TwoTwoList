package hwr.oop.inports;

import hwr.oop.application.Project;
import hwr.oop.application.Task;
import hwr.oop.application.TaskState;
import hwr.oop.application.User;

public interface ChangeTaskUseCase {
    void changeTitleInProject(Task task, String newTitle, Project project);
    void changeContentInProject(Task task, String newContent,Project project);
    void changeTaskStateInProject(Task task, TaskState taskState , Project project);
    void changeDeadlineInProject(Task task, String newDeadline,Project project);
    void changeTitleInUser(Task task, String newTitle, User user);
    void changeContentInUser(Task task, String newContent,User user);
    void changeTaskStateInUser(Task task, TaskState taskState ,User user);
    void changeDeadlineInUser(Task task, String newDeadline ,User user);
}
