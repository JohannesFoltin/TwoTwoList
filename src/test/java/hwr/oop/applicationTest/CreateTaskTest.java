package hwr.oop.applicationTest;

import hwr.oop.application.*;
import hwr.oop.persistence.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.SavePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

class CreateTaskTest {
    private  LoadPort loadPort;
    private CreateTaskService createTaskService;
    @Mock
    private SavePort savePort;
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
        savePort = mock();

        appDataMock = new AppData(projects,users);

        createTaskService = new CreateTaskService(loadPort, savePort);
    }

    @Test
    void testCreateTaskInProject() {
        String title = "Task Title";
        String content = "Task Content";
        TaskState taskState = TaskState.IN_PROGRESS;
        LocalDateTime deadline = LocalDateTime.now();
        Project project = loadPort.loadData().getProjectList().get(0);

        Task task =  createTaskService.createTaskInProject(title,content,taskState,deadline,project);

        Task createdTask = loadPort.loadData().getProjectList().get(0).getTaskList().get(1);

        Optional<LocalDateTime> result = createdTask.getDeadline();

        assertThat(createdTask.getId()).isEqualTo(task.getId());
        assertThat(createdTask.getTitle()).isEqualTo(title);
        assertThat(createdTask.getContent()).isEqualTo(content);
        assertThat(createdTask.getTaskState()).isEqualTo(taskState);
        result.ifPresent(localDate -> assertThat(localDate).isBetween(LocalDateTime.now().minusHours(1),LocalDateTime.now()));
        verify(savePort).saveData(any());
    }

    @Test
     void testCreateTaskInProject_ThrowsExceptionWhenProjectNotFound() {
        String title = "Task Title";
        String content = "Task Content";
        TaskState taskState = TaskState.IN_PROGRESS;
        LocalDateTime deadline = LocalDateTime.now();

        Project project = new Project(UUID.randomUUID(),new ArrayList<>(),"Test",null);

        assertThatThrownBy(() -> createTaskService.createTaskInProject(title, content, taskState, deadline, project))
                .isInstanceOf(CreateTaskException.class)
                .hasMessage("Project not found");

    }

    @Test
     void testCreateTaskInContextList() {
        String title = "Task Title";
        String content = "Task Content";
        TaskState taskState = TaskState.IN_PROGRESS;
        LocalDateTime deadline = LocalDateTime.now();

        User user = loadPort.loadData().getUserList().get(0);

        Task task =createTaskService.createTaskInContextList(title,content,taskState,deadline,user);

        Task createdTask = loadPort.loadData().getUserList().get(0).getContextList().get(0);

        Optional<LocalDateTime> result = createdTask.getDeadline();

        assertThat(createdTask.getId()).isEqualTo(task.getId());
        assertThat(createdTask.getTitle()).isEqualTo(title);
        assertThat(createdTask.getContent()).isEqualTo(content);
        assertThat(createdTask.getTaskState()).isEqualTo(taskState);
        result.ifPresent(localDate -> assertThat(localDate).isBetween(LocalDateTime.now().minusHours(1),LocalDateTime.now()));
        verify(savePort).saveData(any());

    }

    @Test
     void testCreateTaskInContextList_ThrowsExceptionWhenUserNotFound() {

        String title = "Task Title";
        String content = "Task Content";
        TaskState taskState = TaskState.IN_PROGRESS;
        LocalDateTime deadline = LocalDateTime.now();

        User user = new User(UUID.randomUUID(),"Test not known", new ArrayList<>(),new ArrayList<>());

        assertThatThrownBy(() -> createTaskService.createTaskInContextList(title, content, taskState, deadline, user))
                .isInstanceOf(CreateTaskException.class)
                .hasMessage("User not found");
    }
}