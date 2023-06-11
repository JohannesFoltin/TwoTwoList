package hwr.oop.inports;

import hwr.oop.application.Project;
import hwr.oop.application.User;

import java.util.List;

public interface GetProjectsOfUserUseCase {
    List<Project> getProjects(User user);
}
