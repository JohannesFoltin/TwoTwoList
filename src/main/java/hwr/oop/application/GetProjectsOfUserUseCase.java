package hwr.oop.application;

import java.util.List;

public interface GetProjectsOfUserUseCase {
    List<Project> getProjects(User user);
}
