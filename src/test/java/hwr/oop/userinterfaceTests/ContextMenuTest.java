package hwr.oop.userinterfaceTests;

import hwr.oop.application.*;
import hwr.oop.persistence.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.SavePort;
import hwr.oop.userinterface.ContextMenu;
import hwr.oop.userinterface.EditTaskMenu;
import hwr.oop.userinterface.Login;
import hwr.oop.userinterface.MainMenu;
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
    @BeforeEach
    void setUp(){
        appDataMock = new AppData(new ArrayList<>(),new ArrayList<>());
        List<Task> tasks= new ArrayList<>();
        tasks.add(new Task(UUID.randomUUID(),"Title","content",TaskState.DONE,null));
        User user=new User(UUID.randomUUID(),"Test",null, tasks);
        appDataMock.getUserList().add(user);
        editTaskMenu = mock();
        LoadPort loadPort = () -> appDataMock;
        savePort = new MySavePort();

        createTaskUseCase = new CreateTaskService(loadPort, savePort);
    }
    @Test
    void startTestToEditTaskMenu(){

        InputStream inputStream = createInputStreamForInput("3\n");
        OutputStream outputStream = new ByteArrayOutputStream();

        ContextMenu contextMenu = new ContextMenu(inputStream, outputStream,editTaskMenu,createTaskUseCase);
        User user = appDataMock.getUserList().get(0);
        contextMenu.start(user);

        String output = outputStream.toString(); //This is how you can get the result output (sadly the complete, not the last line!)
        verify(editTaskMenu).start();

    }
    @Test
    void createNewTaskTest(){
        InputStream inputStream = createInputStreamForInput("1\nhallo\ncontent\n1\n2004-05-22 13:02");
        OutputStream outputStream = new ByteArrayOutputStream();

        ContextMenu contextMenu = new ContextMenu(inputStream, outputStream,editTaskMenu,createTaskUseCase);
        User user = appDataMock.getUserList().get(0);
        contextMenu.start(user);

        String output = outputStream.toString(); //This is how you can get the result output (sadly the complete, not the last line!)
        assertThat(appDataMock.getUserList().get(0).getContextList().get(1).getTitle()).isEqualTo("hallo");

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
