package hwr.oop.application;


import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.SavePort;

public class ChangeTaskService implements ChangeTaskUseCase {
    private final LoadPort loadPort;
    private final SavePort savePort;

    public ChangeTaskService(LoadPort loadPort, SavePort savePort) {
        this.loadPort = loadPort;
        this.savePort = savePort;
    }

    @Override
    public void changeTitleInProject(Task task, String newTitle, Project project) {

    }

    @Override
    public void changeContentInProject(Task task, String newContent, Project project) {

    }

    @Override
    public void changeTaskStateInProject(Task task, TaskState taskState, Project project) {

    }

    @Override
    public void changeDeadlineInProject(Task task, String newDeadline, Project project) {

    }

    @Override
    public void changeTitleInUser(Task task, String newTitle, User user) {

    }

    @Override
    public void changeContentInUser(Task task, String newContent, User user) {

    }

    @Override
    public void changeTaskStateInUser(Task task, TaskState taskState, User user) {

    }

    @Override
    public void changeDeadlineInUser(Task task, String newDeadline, User user) {

    }
}
