package hwr.oop.userinterfaceTests;

import hwr.oop.application.User;
import hwr.oop.persistence.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.PersistenceAdapter;
import hwr.oop.userinterface.Login;
import hwr.oop.userinterface.MainMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class LoginTest {
    private LoadPort loadPort;
    AppData appDataMock;
    private MyMainMenu mainMenu;
    @BeforeEach
    void setUp(){
        appDataMock = new AppData(new ArrayList<>(),new ArrayList<>());
        mainMenu = new MyMainMenu();
        loadPort = new LoadPort() {
            @Override
            public AppData loadData() {
                return appDataMock;
            }
        };
    }
    @Test
    void startTest(){
        appDataMock.getUserList().add(new User(UUID.randomUUID(),"Test",null,null));

        InputStream inputStream = createInputStreamForInput("1\nTest\n");
        OutputStream outputStream = new ByteArrayOutputStream();

        Login login = new Login(inputStream,outputStream,mainMenu,loadPort);

        login.start();

        String output = retrieveResultFrom(outputStream); // This is how you can get the result output (sadly the complete, not the last line!
        assertThat(mainMenu.isCalled()).isTrue();
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

    private class MyMainMenu extends MainMenu {
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