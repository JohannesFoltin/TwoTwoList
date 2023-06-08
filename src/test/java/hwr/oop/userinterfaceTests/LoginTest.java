package hwr.oop.userinterfaceTests;

import hwr.oop.persistence.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.userinterface.Login;
import hwr.oop.userinterface.MainMenu;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

class LoginTest {

    @Test
    void startTest(){
        InputStream inputStream = createInputStreamFroInput("1\n");
        OutputStream outputStream = new ByteArrayOutputStream();
        MainMenu mainMenu = new MainMenu();
        LoadPort loadPort = new LoadPort() {
            @Override
            public AppData loadData() {
                return null;
            }
        };
    };
    private InputStream createInputStreamFroInput(String input){
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }
}
