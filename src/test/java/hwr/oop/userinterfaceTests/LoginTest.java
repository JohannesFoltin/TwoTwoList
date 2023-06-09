package hwr.oop.userinterfaceTests;

import hwr.oop.application.User;
import hwr.oop.application.ValidateUserService;
import hwr.oop.application.ValidateUserUseCase;
import hwr.oop.persistence.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.userinterface.Login;
import hwr.oop.userinterface.MainMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class LoginTest {
    AppData appDataMock;
    private MyMainMenu mainMenu;
    private ValidateUserUseCase validateUserUseCase;
    @BeforeEach
    void setUp(){
        appDataMock = new AppData(new ArrayList<>(),new ArrayList<>());
        mainMenu = new MyMainMenu();
        LoadPort loadPort = () -> appDataMock;

        validateUserUseCase = new ValidateUserService(loadPort);
    }
    @Test
    void startTestToMainMenu(){
        appDataMock.getUserList().add(new User(UUID.randomUUID(),"Test",null,null));

        InputStream inputStream = createInputStreamForInput("1\nTest\n");
        OutputStream outputStream = new ByteArrayOutputStream();

        Login login = new Login(inputStream,outputStream,mainMenu, validateUserUseCase);

        login.start();

        String output = retrieveResultFrom(outputStream); //This is how you can get the result output (sadly the complete, not the last line!)
        assertThat(mainMenu.isCalled()).isTrue();
        assertThat(output).isEqualTo("o you wanna do??????\n" +
                "Type 1 to login\n" +
                "Type 2 to register a new user\n" +
                "Enter Username:");

    }
    @Test
    void startTestToMainMenu_Exception(){

        appDataMock.getUserList().add(new User(UUID.randomUUID(),"Test",null,null));

        InputStream inputStream = createInputStreamForInput("1\ntest\n1\nTest");
        OutputStream outputStream = new ByteArrayOutputStream();

        Login login = new Login(inputStream,outputStream,mainMenu,validateUserUseCase);

        login.start();

        String output = retrieveResultFrom(outputStream); //This is how you can get the result output (sadly the complete, not the last line!)
        assertThat(mainMenu.isCalled()).isTrue();
        assertThat(output).isEqualTo("o you wanna do??????\n" +
                "Type 1 to login\n" +
                "Type 2 to register a new user\n" +
                "Enter Username: \n" +
                "\n" +
                "Username not found\n" +
                "What do you wanna do??????\n" +
                "Type 1 to login\n" +
                "Type 2 to register a new user\n" +
                "Enter Username:");

    }

    private String retrieveResultFrom(OutputStream outputStream) {
        String outputText = outputStream.toString();
        String key = "output:";
        return outputText.substring(outputText.indexOf(key) + key.length()).trim();
    }

    private InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }

    private static class MyMainMenu extends MainMenu {
        public boolean isCalled() {
            return isCalled;
        }

        private boolean isCalled = false;
        @Override
        public void start(User user){
            isCalled = true;
        }
    }
}