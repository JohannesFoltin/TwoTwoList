package hwr.oop.application.createproject;

import hwr.oop.application.Project;
import hwr.oop.application.Task;
import hwr.oop.application.User;
import hwr.oop.application.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.SavePort;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CreateProjectService implements CreateProjectUseCase {
    private final LoadPort loadPort;
    private final SavePort savePort;

    public CreateProjectService(LoadPort loadPort, SavePort savePort) {
        this.loadPort = loadPort;
        this.savePort = savePort;
    }

    @Override
    public Project createProject(String title, List<Task> taskList, User user) {
        Project project = new Project(UUID.randomUUID(), taskList, title, Map.of(user, Boolean.TRUE));
        AppData appData = loadPort.loadData();
        appData.addProject(project);
        savePort.saveData(appData);
        return project;
    }
}