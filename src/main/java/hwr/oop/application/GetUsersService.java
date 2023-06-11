package hwr.oop.application;

import hwr.oop.persistence.LoadPort;
import java.util.List;

public class GetUsersService implements GetUsersUseCase{

    private final LoadPort load;

    public GetUsersService(LoadPort load) {
        this.load = load;
    }

    @Override
    public List<User> getUsers() {
        return load.loadData().getUserList();
    }
}
