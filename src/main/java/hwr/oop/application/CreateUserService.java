package hwr.oop.application;

import hwr.oop.inports.CreateUserUseCase;
import hwr.oop.outports.LoadPort;
import hwr.oop.outports.SavePort;

import java.util.ArrayList;
import java.util.UUID;

public class CreateUserService implements CreateUserUseCase {
    private final SavePort savePort;
    private final LoadPort loadPort;

    public CreateUserService(SavePort savePort, LoadPort loadPort) {
        this.savePort = savePort;
        this.loadPort = loadPort;
    }

    @Override
    public User createUser(String name) {
        User user = new User(UUID.randomUUID(),name, new ArrayList<>(),new ArrayList<>());
        AppData appData = loadPort.loadData();
        appData.getUserList().add(user);
        savePort.saveData(appData);
        return user;

    }
}
