package hwr.oop.applicationTest;

import hwr.oop.application.*;
import hwr.oop.application.deletetask.DeleteTaskService;
import hwr.oop.application.deletetask.DeleteTaskUseCase;
import hwr.oop.application.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.SavePort;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class DeleteTaskTest {
    private  LoadPort loadPort;
    private DeleteTaskUseCase deleteTaskUseCase;
    AppData appDataMock;
    @BeforeEach
    void setUp() {
        List<Project> projects = new ArrayList<>();
        UUID id = UUID.randomUUID();
        projects.add(new Project(id,new ArrayList<>(),"Test Project",null));

        Task taskTmp = new Task(UUID.randomUUID(),"TestTask","This is a task for testing", TaskState.DONE,null);
        projects.get(0).getTaskList().add(taskTmp);

        List<User> users = new ArrayList<>();
        users.add(new User(UUID.randomUUID(),"TestUser",new ArrayList<>(),new ArrayList<>()));

        loadPort = () -> appDataMock;
        SavePort savePort = appData -> appDataMock = appData;

        savePort.saveData(new AppData(projects,users));

        deleteTaskUseCase = new DeleteTaskService(loadPort, savePort);
    }


}
