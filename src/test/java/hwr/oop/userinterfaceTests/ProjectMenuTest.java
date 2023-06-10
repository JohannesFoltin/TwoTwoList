package hwr.oop.userinterfaceTests;

import hwr.oop.application.DeleteProjectUseCase;
import hwr.oop.application.ListProjectsOfUserUseCase;
import hwr.oop.application.Project;
import hwr.oop.application.User;
import hwr.oop.persistence.AppData;
import hwr.oop.userinterface.CreateProjectMenu;
import hwr.oop.userinterface.EditProjectMenu;
import hwr.oop.userinterface.ProjectMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectMenuTest {
    @Mock
    ListProjectsOfUserUseCase listProjectsOfUserUseCase;
    @Mock
    DeleteProjectUseCase deleteProjectUseCase;
    @Mock
    EditProjectMenu editProjectMenu;
    @Mock
    CreateProjectMenu createProjectMenu;
    OutputStream outputStream;
    List<Project> projects;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();

        User user = new User(UUID.randomUUID(), "testUser", null, null);
        projects = new ArrayList<>();
        UUID id0 = UUID.fromString("41340433-7709-40d8-99c5-c576309f690a");
        projects.add(new Project(id0, null, "testProject0", Map.of(user, Boolean.TRUE)));
        UUID id1 = UUID.fromString("92d064a9-2ae6-420b-b426-a4a899bc6e1a");
        projects.add(new Project(id1, null, "testProject1", Map.of(user, Boolean.TRUE)));
        UUID id2 = UUID.fromString("0dc02f14-1434-405b-b110-04aae925b85b");
        projects.add(new Project(id2, null, "testProject2", Map.of(user, Boolean.TRUE)));
    }

    private InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }

    @Test
    void listProjectsTest() {
        ProjectMenu projectMenu = new ProjectMenu(createInputStreamForInput(""), outputStream, listProjectsOfUserUseCase,
                deleteProjectUseCase, editProjectMenu, createProjectMenu);

        projectMenu.listProjects(projects);
        assertThat(outputStream.toString()).isEqualTo(
                "1: 41340433-7709-40d8-99c5-c576309f690a - testProject0\n" +
                "2: 92d064a9-2ae6-420b-b426-a4a899bc6e1a - testProject1\n" +
                "3: 0dc02f14-1434-405b-b110-04aae925b85b - testProject2\n");
    }
}
