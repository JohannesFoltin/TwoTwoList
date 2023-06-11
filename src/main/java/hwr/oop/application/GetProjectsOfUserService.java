package hwr.oop.application;

import hwr.oop.inports.GetProjectsOfUserUseCase;
import hwr.oop.outports.LoadPort;

import java.util.List;

public class GetProjectsOfUserService implements GetProjectsOfUserUseCase {
    LoadPort load;

    public GetProjectsOfUserService(LoadPort load) {
        this.load = load;
    }

    @Override
    public List<Project> getProjects(User user) {
        return load.loadAllUserProjects(user.getId());
    }
}
