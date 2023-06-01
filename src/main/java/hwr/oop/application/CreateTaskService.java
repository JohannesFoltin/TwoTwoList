package hwr.oop.application;

import hwr.oop.persistence.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.SavePort;

import java.time.LocalDateTime;

public class CreateTaskService implements CreateTaskUseCase{

    public CreateTaskService(LoadPort loadPort, SavePort savePort) {
        this.loadPort = loadPort;
        this.savePort = savePort;
    }

    private final LoadPort loadPort;
    private final SavePort savePort;


    @Override
    public void createTaskInProject(String title, String content, TaskState taskState, LocalDateTime deadLine, Project project) {
        Task taskTmp = new Task(title,content,taskState,deadLine);
        int ind= loadPort.loadData().getProjectList().indexOf(project);
        if(ind >= 0){
            AppData appData = loadPort.loadData();
            appData.getProjectList().get(ind).getTaskList().add(taskTmp);
            savePort.saveData(appData);
        }
        else {
            throw new CreateTaskException("Project not found");
        }
    }

    @Override
    public void createTaskInContextList(String title, String content, TaskState taskState, LocalDateTime deadLine, User user) {
        Task taskTmp = new Task(title,content,taskState,deadLine);
        int ind= loadPort.loadData().getUserList().indexOf(user);
        if(ind >= 0){
            AppData appData = loadPort.loadData();
            appData.getUserList().get(ind).getContextList().add(taskTmp);
            savePort.saveData(appData);
        }
        else {
            throw new CreateTaskException("User not found");
        }
    }
}
