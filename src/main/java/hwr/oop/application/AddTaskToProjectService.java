package hwr.oop.application;

import hwr.oop.persistence.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.SavePort;

public class AddTaskToProjectService implements AddTaskToProjectUseCase {
    private final LoadPort load;
    private final SavePort save;

    public AddTaskToProjectService(LoadPort load, SavePort save) {
        this.load = load;
        this.save = save;
    }

    @Override
    public Project addTaskToProject(Project project, Task task) {
        AppData appData = load.loadData();
        appData.deleteProject(project);
        project.addTask(task);
        appData.addProject(project);
        save.saveData(appData);
        return project;
    }
}
