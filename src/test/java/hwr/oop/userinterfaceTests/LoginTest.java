package hwr.oop.userinterfaceTests;

import hwr.oop.application.User;
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
    private LoadPort loadPort;
    AppData appDataMock;
    private Login login;
    private MainMenu mainMenu;
    @BeforeEach
    void setUp(){
        appDataMock = new AppData(new ArrayList<>(),new ArrayList<>());
        mainMenu = new MainMenu();
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

        InputStream inputStream = createInputStreamFroInput("1\n");
        OutputStream outputStream = new ByteArrayOutputStream();

        Login login = new Login(inputStream,outputStream,mainMenu,loadPort);

        login.start();

        String output = retrieveResultFrom(outputStream);
        assertThat(output).isEqualTo("Enter Username: \n");
    }

    private String retrieveResultFrom(OutputStream outputStream) {
        String outputText = outputStream.toString();
        String key = "output:";
        return outputText.substring(outputText.indexOf(key) + key.length()).trim();
    }

    private InputStream createInputStreamFroInput(String input){
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }
}
