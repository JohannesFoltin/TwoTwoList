package hwr.oop.userinterfaceTests;

import hwr.oop.application.DeleteProjectUseCase;
import hwr.oop.application.ListProjectsOfUserUseCase;
import hwr.oop.application.Project;
import hwr.oop.application.User;
import hwr.oop.applicationTest.RandomTestData;
import hwr.oop.userinterface.CreateProjectMenu;
import hwr.oop.userinterface.EditProjectMenu;
import hwr.oop.userinterface.ProjectMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    User user;
    List<Project> projects;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();

        user = new User(UUID.randomUUID(), "testUser", null, null);
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
                "These are your projects: \n" +
                        "\n" +
                        "1: 41340433-7709-40d8-99c5-c576309f690a - testProject0\n" +
                        "2: 92d064a9-2ae6-420b-b426-a4a899bc6e1a - testProject1\n" +
                        "3: 0dc02f14-1434-405b-b110-04aae925b85b - testProject2\n");
    }

    @Test
    void chooseProjectUnsuccessfullyOnce_then3Test() {
        ProjectMenu projectMenu = new ProjectMenu(createInputStreamForInput("0\n3\n"), outputStream,
                listProjectsOfUserUseCase, deleteProjectUseCase, editProjectMenu, createProjectMenu);
        Project project = projectMenu.chooseProject(projects);

        assertThat(outputStream.toString()).hasToString("Which project? (1 - 3) \n" +
                "\n" +
                "Invalid Choice. \n" +
                "\n" +
                "Which project? (1 - 3) \n" +
                "\n");
        assertThat(project).isEqualTo(projects.get(2));
    }

    @Test
    void chooseProjectUnsuccessfullyOnce_then1Test() {
        ProjectMenu projectMenu = new ProjectMenu(createInputStreamForInput("8\n1\n"), outputStream,
                listProjectsOfUserUseCase, deleteProjectUseCase, editProjectMenu, createProjectMenu);
        Project project = projectMenu.chooseProject(projects);

        assertThat(outputStream.toString()).hasToString("Which project? (1 - 3) \n" +
                "\n" +
                "Invalid Choice. \n" +
                "\n" +
                "Which project? (1 - 3) \n" +
                "\n");
        assertThat(project).isEqualTo(projects.get(0));
    }

    @Test
    void deleteProjectWithPermissionsTest() {
        ProjectMenu projectMenu = new ProjectMenu(createInputStreamForInput("2\n"), outputStream,
                listProjectsOfUserUseCase, deleteProjectUseCase, editProjectMenu, createProjectMenu);

        projectMenu.deleteProject(projects, user);
        verify(deleteProjectUseCase).deleteProject(any());
    }

    @Test
    void deleteProjectWithoutPermission() {
        ProjectMenu projectMenu = new ProjectMenu(createInputStreamForInput("2\n2\n"), outputStream,
                listProjectsOfUserUseCase, deleteProjectUseCase, editProjectMenu, createProjectMenu);

        projectMenu.deleteProject(projects, RandomTestData.getRandomUser());

        assertThat(outputStream.toString())
                .hasToString("Which project? (1 - 3) \n" +
                        "\n" +
                        "You do not have the necessary permissions to delete this Project. \n" +
                        "\n" +
                        "These are your projects: \n" +
                        "\n" +
                        "What do you want to do? \n" +
                        "\n" +
                        "Type 1 to edit a Project \n" +
                        "\n" +
                        "Type 2 to create a new Project \n" +
                        "\n" +
                        "Type 3 to delete a Project \n" +
                        "\n");
        verify(deleteProjectUseCase, never()).deleteProject(any());
    }

    @Test
    void createProjectTest() {
        ProjectMenu projectMenu = new ProjectMenu(createInputStreamForInput(""), outputStream,
                listProjectsOfUserUseCase, deleteProjectUseCase, editProjectMenu, createProjectMenu);

        projectMenu.createProject(user);
        verify(createProjectMenu).start(user);
    }

    @Test
    void editProjectWithoutPermissionTest() {
        ProjectMenu projectMenu = new ProjectMenu(createInputStreamForInput("2\n2\n"), outputStream,
                listProjectsOfUserUseCase, deleteProjectUseCase, editProjectMenu, createProjectMenu);

        user = RandomTestData.getRandomUser();
        projectMenu.editProject(projects, user);
        verify(editProjectMenu, never()).start(any(), any());
        assertThat(outputStream.toString()).hasToString("Which project? (1 - 3) \n" +
                "\n" +
                "You do not have the necessary permissions to edit this Project. \n" +
                "\n" +
                "These are your projects: \n" +
                "\n" +
                "What do you want to do? \n" +
                "\n" +
                "Type 1 to edit a Project \n" +
                "\n" +
                "Type 2 to create a new Project \n" +
                "\n" +
                "Type 3 to delete a Project \n" +
                "\n");
    }

    @Test
    void editProjectWithPermissions(){
        ProjectMenu projectMenu = new ProjectMenu(createInputStreamForInput("2\n"), outputStream,
                listProjectsOfUserUseCase, deleteProjectUseCase, editProjectMenu, createProjectMenu);

        projectMenu.editProject(projects, user);
        verify(editProjectMenu).start(user,projects.get(1));
        assertThat(outputStream.toString()).hasToString("Which project? (1 - 3) \n" +
                "\n");
    }

    @ParameterizedTest
    @ValueSource(strings = {"7\n1\n2\n", "7\n3\n2\n"})
    void startUnsuccessfullyOnce(String input) {
        ProjectMenu projectMenu = new ProjectMenu(createInputStreamForInput(input), outputStream,
                listProjectsOfUserUseCase, deleteProjectUseCase, editProjectMenu, createProjectMenu);

        when(listProjectsOfUserUseCase.listProjects(any())).thenReturn(projects);
        projectMenu.start(user);

        assertThat(outputStream.toString()).hasToString("These are your projects: \n" +
                "\n" +
                "1: 41340433-7709-40d8-99c5-c576309f690a - testProject0\n" +
                "2: 92d064a9-2ae6-420b-b426-a4a899bc6e1a - testProject1\n" +
                "3: 0dc02f14-1434-405b-b110-04aae925b85b - testProject2\n" +
                "What do you want to do? \n" +
                "\n" +
                "Type 1 to edit a Project \n" +
                "\n" +
                "Type 2 to create a new Project \n" +
                "\n" +
                "Type 3 to delete a Project \n" +
                "\n" +
                "Choice invalid. \n" +
                "\n" +
                "These are your projects: \n" +
                "\n" +
                "1: 41340433-7709-40d8-99c5-c576309f690a - testProject0\n" +
                "2: 92d064a9-2ae6-420b-b426-a4a899bc6e1a - testProject1\n" +
                "3: 0dc02f14-1434-405b-b110-04aae925b85b - testProject2\n" +
                "What do you want to do? \n" +
                "\n" +
                "Type 1 to edit a Project \n" +
                "\n" +
                "Type 2 to create a new Project \n" +
                "\n" +
                "Type 3 to delete a Project \n" +
                "\n" +
                "Which project? (1 - 3) \n" +
                "\n");
    }

    @Test
    void startThenCreateProjectTest() {
        ProjectMenu projectMenu = new ProjectMenu(createInputStreamForInput("2\n"), outputStream,
                listProjectsOfUserUseCase, deleteProjectUseCase, editProjectMenu, createProjectMenu);

        when(listProjectsOfUserUseCase.listProjects(any())).thenReturn(projects);
        projectMenu.start(user);

        assertThat(outputStream.toString()).hasToString("These are your projects: \n" +
                "\n" +
                "1: 41340433-7709-40d8-99c5-c576309f690a - testProject0\n" +
                "2: 92d064a9-2ae6-420b-b426-a4a899bc6e1a - testProject1\n" +
                "3: 0dc02f14-1434-405b-b110-04aae925b85b - testProject2\n" +
                "What do you want to do? \n" +
                "\n" +
                "Type 1 to edit a Project \n" +
                "\n" +
                "Type 2 to create a new Project \n" +
                "\n" +
                "Type 3 to delete a Project \n" +
                "\n");
        verify(createProjectMenu).start(user);
    }
}
