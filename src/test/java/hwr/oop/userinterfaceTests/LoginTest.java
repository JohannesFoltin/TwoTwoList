package hwr.oop.userinterfaceTests;

import hwr.oop.application.*;
import hwr.oop.persistence.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.SavePort;
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
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginTest {
    AppData appDataMock;
    @Mock
    private MainMenu mainMenu;
    private ValidateUserUseCase validateUserUseCase;
    private CreateUserUseCase createUserUseCase;
    private SavePort savePort;

    @BeforeEach
    void setUp(){
        appDataMock = new AppData(new ArrayList<>(),new ArrayList<>());
        mainMenu = mock();
        
        LoadPort loadPort = () -> appDataMock;

        savePort = mock();

        validateUserUseCase = new ValidateUserService(loadPort);

        createUserUseCase = new CreateUserService(savePort,loadPort);
    }
    @Test
    void startTestToMainMenu(){
        appDataMock.getUserList().add(new User(UUID.randomUUID(),"Test",null,null));

        InputStream inputStream = createInputStreamForInput("1\nTest\n");
        OutputStream outputStream = new ByteArrayOutputStream();

        Login login = new Login(inputStream,outputStream,mainMenu, validateUserUseCase, createUserUseCase);

        login.start();

        String output = outputStream.toString().strip(); //This is how you can get the result output (sadly the complete, not the last line!)
        verify(mainMenu).start(any());
        assertThat(output).isEqualTo("What do you wanna do??????\n" +
                "Type 1 to login\n" +
                "Type 2 to register a new user\n" +
                "Enter Username:");

    }
    @Test
    void startTestToMainMenu_Exception(){

        appDataMock.getUserList().add(new User(UUID.randomUUID(),"Test",null,null));

        InputStream inputStream = createInputStreamForInput("1\ntest\n1\nTest");
        OutputStream outputStream = new ByteArrayOutputStream();

        Login login = new Login(inputStream,outputStream,mainMenu,validateUserUseCase, createUserUseCase);

        login.start();

        String output = outputStream.toString(); //This is how you can get the result output (sadly the complete, not the last line!)
        verify(mainMenu).start(any());
        assertThat(output).isEqualTo("What do you wanna do??????\n" +
                "Type 1 to login\n" +
                "Type 2 to register a new user\n" +
                "Enter Username:\n" +
                "Username not found\n" +
                "What do you wanna do??????\n" +
                "Type 1 to login\n" +
                "Type 2 to register a new user\n" +
                "Enter Username:\n");

    }
    @Test
    void  startTestToCreateNewUserToMain(){
        InputStream inputStream = createInputStreamForInput("2\nTest\n");
        OutputStream outputStream = new ByteArrayOutputStream();

        Login login = new Login(inputStream,outputStream,mainMenu,validateUserUseCase, createUserUseCase);

        login.start();
        String output = outputStream.toString();
        verify(mainMenu).start(any());
        verify(savePort).saveData(any());
        assertThat(output).isEqualTo("What do you wanna do??????\n" +
                "Type 1 to login\n" +
                "Type 2 to register a new user\n" +
                "Enter username for new user:\n" +
                "New User created with name: Test");
    }

    private InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }

}