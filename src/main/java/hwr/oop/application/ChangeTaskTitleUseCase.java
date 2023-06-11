package hwr.oop.application;

public interface ChangeTaskTitleUseCase {
    void changeTitleInProject(Task task, String newTitle,Project project);
    void changeContentInProject(Task task, String newContent,Project project);
    void changeTaskStateInProject(Task task, TaskState taskState ,Project project);
    void changeDeadlineInProject(Task task, String newTitle,Project project);
    void changeTitleInUser(Task task, String newTitle);
    void changeContentInUser(Task task, String newTitle);
    void changeTaskStateInUser();
    void changeDeadlineInUser();

}
