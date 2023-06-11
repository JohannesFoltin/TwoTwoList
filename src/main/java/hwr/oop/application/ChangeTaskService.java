package hwr.oop.application;


import hwr.oop.persistence.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.SavePort;

import java.time.LocalDateTime;
import java.util.List;

public class ChangeTaskService implements ChangeTaskUseCase {
    private final LoadPort loadPort;
    private final SavePort savePort;

    public ChangeTaskService(LoadPort loadPort, SavePort savePort) {
        this.loadPort = loadPort;
        this.savePort = savePort;
    }

    public int getProjectIndex(Project project, AppData appData){
        int projectIndex = appData.getProjectList().indexOf(project);
        if (projectIndex >= 0) {
            return projectIndex;
        } else {
            throw new CannotChangeTaskException("Project not found!");
        }
    }

    public int getTaskIndex(Task task, List<Task> taskList){
        int taskIndex = taskList.indexOf(task);
        if (taskIndex >= 0) {
            return taskIndex;
        } else {
            throw new CannotChangeTaskException("Task not in Project found!");
        }
    }

    @Override
    public void changeTitleInProject(Task task, String newTitle, Project project) {
        AppData appData = loadPort.loadData();
        int projectIndex = getProjectIndex(project,appData);
        List<Task> taskList = appData.getProjectList().get(projectIndex).getTaskList();
        int taskIndex = getTaskIndex(task,taskList);

        LocalDateTime taskDeadline = null;
        if (task.getDeadline().isPresent()){
            taskDeadline = task.getDeadline().get();
        }

        Task newTask = new Task(task.getId(),newTitle,task.getContent(),task.getTaskState(),taskDeadline);
        appData.getProjectList().get(projectIndex).getTaskList().set(taskIndex,newTask);
        savePort.saveData(appData);
    }

    @Override
    public void changeContentInProject(Task task, String newContent, Project project) {
        AppData appData = loadPort.loadData();
        int projectIndex = getProjectIndex(project,appData);
        List<Task> taskList = appData.getProjectList().get(projectIndex).getTaskList();
        int taskIndex = getTaskIndex(task,taskList);

        LocalDateTime taskDeadline = null;
        if (task.getDeadline().isPresent()){
            taskDeadline = task.getDeadline().get();
        }

        Task newTask = new Task(task.getId(),task.getTitle(),newContent,task.getTaskState(),taskDeadline);
        appData.getProjectList().get(projectIndex).getTaskList().set(taskIndex,newTask);
        savePort.saveData(appData);
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
