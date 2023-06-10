package hwr.oop.application;

import hwr.oop.application.Project;
import hwr.oop.application.Task;
import hwr.oop.application.User;
import hwr.oop.persistence.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.SavePort;

public class DeleteTaskService implements DeleteTaskUseCase {

    private final SavePort savePort;
    private final LoadPort loadPort;

    public DeleteTaskService(LoadPort loadPort,SavePort savePort) {
        this.savePort = savePort;
        this.loadPort = loadPort;
    }

    @Override
    public void deleteTaskFromProject(Task task, Project project) {
        AppData appData = loadPort.loadData();
        int projectIndex = appData.getProjectList().indexOf(project);
        if (projectIndex >= 0) {
            boolean isDeleted = appData.getProjectList().get(projectIndex).getTaskList().remove(task);
            if (isDeleted) {
                savePort.saveData(appData);
            } else {
                throw new CannotDeleteTaskException("Task could not be deleted, because this task is not in this project");
            }

        } else {

            throw new CannotDeleteTaskException("Project not found!");

        }
    }

    @Override
    public void deleteTaskFromContextList(Task task, User user) {
        AppData appData = loadPort.loadData();
        int userIndex = appData.getUserList().indexOf(user);
        if (userIndex >= 0) {
            boolean isDeleted = appData.getUserList().get(userIndex).getContextList().remove(task);
            if (isDeleted) {
                savePort.saveData(appData);
            } else {
                throw new CannotDeleteTaskException("Task could not be deleted, because this task is not in this context-list of this User");
            }

        } else {

            throw new CannotDeleteTaskException("User not found!");

        }
    }
}
