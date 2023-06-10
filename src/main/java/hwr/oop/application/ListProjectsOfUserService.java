package hwr.oop.application;

import hwr.oop.persistence.LoadPort;

import java.util.List;

public class ListProjectsOfUserService implements ListProjectsOfUserUseCase {
    LoadPort load;

    public ListProjectsOfUserService(LoadPort load) {
        this.load = load;
    }

    @Override
    public List<Project> listProjects(User user) {
        return load.loadAllUserProjects(user.getId());
    }
}
