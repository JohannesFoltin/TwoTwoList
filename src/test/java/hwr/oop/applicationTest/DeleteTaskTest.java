package hwr.oop.applicationTest;

import hwr.oop.application.*;
import hwr.oop.application.AppData;
import hwr.oop.inports.DeleteTaskUseCase;
import hwr.oop.outports.LoadPort;
import hwr.oop.outports.SavePort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DeleteTaskTest {
    private  LoadPort loadPort;
    private MySavePort savePort;
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
        users.add(new User(UUID.randomUUID(),"TestUser",new ArrayList<>(),projects.get(0).getTaskList()));

        loadPort = () -> appDataMock;

        savePort = new MySavePort();

        appDataMock =new AppData(projects,users);

        deleteTaskUseCase = new DeleteTaskService(loadPort, savePort);
    }

    @Test
    void deleteTaskFromProject(){
        Project toDeleteTaskFrom = loadPort.loadData().getProjectList().get(0);
        Task taskToDelete = toDeleteTaskFrom.getTaskList().get(0);

        deleteTaskUseCase.deleteTaskFromProject(taskToDelete,toDeleteTaskFrom);

        Assertions.assertThat(toDeleteTaskFrom.getTaskList()).isEmpty();
        assertThat(savePort.isFlag()).isTrue();
    }
    @Test
    void testDeleteTaskFromProject_ThrowsExceptionWhenTaskNotFound() {
        Task notThereTask = new Task(UUID.randomUUID(),"","", null,null);
        Project project = loadPort.loadData().getProjectList().get(0);

        assertThatThrownBy(() -> deleteTaskUseCase.deleteTaskFromProject(notThereTask,project))
                .isInstanceOf(CannotDeleteTaskException.class)
                .hasMessage("Task could not be deleted, because this task is not in this project");
    }
    @Test
    void testDeleteTaskFromProject_ThrowsExceptionWhenProjektNotFound() {
        Task notThereTask = new Task(UUID.randomUUID(),"","", null,null);
        Project notThereProject = new Project(UUID.randomUUID(),new ArrayList<>(),"",null);

        assertThatThrownBy(() -> deleteTaskUseCase.deleteTaskFromProject(notThereTask,notThereProject))
                .isInstanceOf(CannotDeleteTaskException.class)
                .hasMessage("Project not found!");
    }
    @Test
    void deleteTaskFromContextList(){
        User toDeleteTaskFrom = loadPort.loadData().getUserList().get(0);
        Task taskToDelete = toDeleteTaskFrom.getContextList().get(0);

        deleteTaskUseCase.deleteTaskFromContextList(taskToDelete,toDeleteTaskFrom);

        Assertions.assertThat(toDeleteTaskFrom.getContextList()).isEmpty();
        assertThat(savePort.isFlag()).isTrue();
    }
    @Test
    void testDeleteTaskFromContextList_ThrowsExceptionWhenTaskNotFound() {
        Task notThereTask = new Task(UUID.randomUUID(),"","", null,null);
        User user = loadPort.loadData().getUserList().get(0);

        assertThatThrownBy(() -> deleteTaskUseCase.deleteTaskFromContextList(notThereTask,user))
                .isInstanceOf(CannotDeleteTaskException.class)
                .hasMessage("Task could not be deleted, because this task is not in this context-list of this User");
    }
    @Test
    void testDeleteTaskFromContextList_ThrowsExceptionWhenProjektNotFound() {
        Task notThereTask = new Task(UUID.randomUUID(),"","", null,null);
        User notThereUser = new User(UUID.randomUUID(),null,null,null);

        assertThatThrownBy(() -> deleteTaskUseCase.deleteTaskFromContextList(notThereTask,notThereUser))
                .isInstanceOf(CannotDeleteTaskException.class)
                .hasMessage("User not found!");
    }


    private class MySavePort implements SavePort {

        public boolean isFlag() {
            return flag;
        }

        private boolean flag = false;

        @Override
        public void saveData(AppData appData) {
            this.flag = true;
            appDataMock = appData;
        }
    }
}
