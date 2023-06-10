package hwr.oop.application;


public interface DeleteTaskUseCase {
    void deleteTaskFromProject(Task task, Project project);

    void deleteTaskFromContextList(Task task, User user);

}
