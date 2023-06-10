package hwr.oop.application;

import java.util.List;

public interface ListProjectsOfUserUseCase {
    List<Project> listProjects(User user);
}
