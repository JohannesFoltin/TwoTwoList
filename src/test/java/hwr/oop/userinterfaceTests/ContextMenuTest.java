package hwr.oop.userinterfaceTests;

import hwr.oop.application.*;
import hwr.oop.persistence.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.SavePort;
import hwr.oop.userinterface.ContextMenu;
import hwr.oop.userinterface.EditTaskMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContextMenuTest {
    AppData appDataMock;
    @Mock
    private EditTaskMenu editTaskMenu;
    private MySavePort savePort;
    private CreateTaskUseCase  createTaskUseCase;
    private DeleteTaskUseCase deleteTaskUseCase;
    @BeforeEach
    void setUp(){
        appDataMock = new AppData(new ArrayList<>(),new ArrayList<>());
        List<Task> tasks= new ArrayList<>();
        UUID id0 = UUID.fromString("41340433-7709-40d8-99c5-c576309f690a");
        tasks.add(new Task(id0,"Title","content",TaskState.DONE,null));
        User user=new User(UUID.randomUUID(),"Test",null, tasks);
        appDataMock.getUserList().add(user);
        editTaskMenu = mock();
        LoadPort loadPort = () -> appDataMock;
        savePort = new MySavePort();

        createTaskUseCase = new CreateTaskService(loadPort, savePort);
        deleteTaskUseCase = new DeleteTaskService(loadPort,savePort);

    }
    @Test
    void startTestToEditTaskMenu(){

        InputStream inputStream = createInputStreamForInput("3\n0\n");
        OutputStream outputStream = new ByteArrayOutputStream();

        ContextMenu contextMenu = new ContextMenu(inputStream, outputStream,editTaskMenu,createTaskUseCase, deleteTaskUseCase);
        User user = appDataMock.getUserList().get(0);
        contextMenu.start(user);

        String output = outputStream.toString(); //This is how you can get the result output (sadly the complete, not the last line!)
        verify(editTaskMenu).startWithUser(any(),any());

    }
    @Test
    void startTestUnsuccessfully(){
        InputStream inputStream = createInputStreamForInput("5\n");
        OutputStream outputStream = new ByteArrayOutputStream();

        ContextMenu contextMenu = new ContextMenu(inputStream, outputStream,editTaskMenu,createTaskUseCase, deleteTaskUseCase);
        User user = appDataMock.getUserList().get(0);
        contextMenu.start(user);

    }
    @Test
    void createNewTaskTest(){
        InputStream inputStream = createInputStreamForInput("1\nhallo\ncontent\n1\n2004-05-22 13:02");
        OutputStream outputStream = new ByteArrayOutputStream();

        ContextMenu contextMenu = new ContextMenu(inputStream, outputStream,editTaskMenu,createTaskUseCase, deleteTaskUseCase);
        User user = appDataMock.getUserList().get(0);
        contextMenu.start(user);

        String output = outputStream.toString(); //This is how you can get the result output (sadly the complete, not the last line!)
        assertThat(appDataMock.getUserList().get(0).getContextList().get(1).getTitle()).isEqualTo("hallo");

    }
    @Test
    void deleteTaskTest(){
        Task task= appDataMock.getUserList().get(0).getContextList().get(0);
        InputStream inputStream = createInputStreamForInput("2\n1\n");
        OutputStream outputStream = new ByteArrayOutputStream();

        ContextMenu contextMenu = new ContextMenu(inputStream, outputStream,editTaskMenu,createTaskUseCase, deleteTaskUseCase);
        User user = appDataMock.getUserList().get(0);
        contextMenu.start(user);

        String output = outputStream.toString(); //This is how you can get the result output (sadly the complete, not the last line!)
        assertThat(appDataMock.getUserList().get(0).getContextList().contains(task)).isEqualTo(false);

    }
    @Test
    void listTaskTest() {
        OutputStream outputStream = new ByteArrayOutputStream();
        ContextMenu contextMenu = new ContextMenu(createInputStreamForInput(""), outputStream,editTaskMenu,createTaskUseCase, deleteTaskUseCase);

        contextMenu.listTasks(appDataMock.getUserList().get(0).getContextList());
        assertThat(outputStream.toString()).isEqualTo(
                "These are your tasks: \n" +
                        "1: 41340433-7709-40d8-99c5-c576309f690a Title\n");
    }


    private InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }
    private class MySavePort implements SavePort {
        private boolean flag = false;

        public boolean isFlag() {
            return flag;
        }

        @Override
        public void saveData(AppData appData) {
            this.flag = true;
            appDataMock = appData;
        }
    }

}
