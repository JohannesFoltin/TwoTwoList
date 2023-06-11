package hwr.oop.userinterfaceTests;

import hwr.oop.application.*;
import hwr.oop.applicationTest.RandomTestData;
import hwr.oop.userinterface.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class EditProjectMenuTest {

    @Mock
    EditTaskMenu editTaskMenu;
    @Mock
    AddTaskToProjectMenu addTaskToProjectMenu;
    @Mock
    EditProjectPermissionsMenu editProjectPermissionsMenu;
    @Mock
    DeleteTaskUseCase deleteTaskUseCase;
    @Mock
    GetProjectUseCase getProjectUseCase;
    @Mock
    ProjectMenu projectMenu;
    OutputStream output;
    Project project;
    User user;

    @BeforeEach
    void setUp() {
        output = new ByteArrayOutputStream();

        user = new User(UUID.randomUUID(), "testUser", null, null);
        List<Task> taskList = new ArrayList<>();
        UUID id0 = UUID.fromString("41340433-7709-40d8-99c5-c576309f690a");
        taskList.add(new Task(id0, "testTitle0", null, null, null));
        UUID id1 = UUID.fromString("92d064a9-2ae6-420b-b426-a4a899bc6e1a");
        taskList.add(new Task(id1, "testTitle1", null, null, null));
        UUID id2 = UUID.fromString("0dc02f14-1434-405b-b110-04aae925b85b");
        taskList.add(new Task(id2, "testTitle2", null, null, null));
        project = new Project(UUID.randomUUID(), taskList, "testTitle", Map.of(user, Boolean.TRUE));
    }

    private InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }

    @Test
    void invalidInputOnce_thenEditTask() {
        EditProjectMenu editProjectMenu = new EditProjectMenu(createInputStreamForInput("g\n1\n2\n"), output,
                editTaskMenu, addTaskToProjectMenu, editProjectPermissionsMenu, deleteTaskUseCase, getProjectUseCase, projectMenu);

        editProjectMenu.start(user, project);

        verify(editTaskMenu).start(project, user, project.getTaskList().get(1));
        assertThat(output.toString()).hasToString("These are all the tasks of Project testTitle: \n" +
                "\n" +
                "1 41340433-7709-40d8-99c5-c576309f690a - testTitle0\n" +
                "2 92d064a9-2ae6-420b-b426-a4a899bc6e1a - testTitle1\n" +
                "3 0dc02f14-1434-405b-b110-04aae925b85b - testTitle2\n" +
                "\n" +
                "\n" +
                "What do you want to do? \n" +
                "\n" +
                "Type 1 to edit a task from this project.\n" +
                "Type 2 to add a new task to this project.\n" +
                "Type 3 to delete a task from this project.\n" +
                "Type 4 to edit the user permissions of this project. \n" +
                "\n" +
                "Invalid input, please try again. \n" +
                "\n" +
                "These are all the tasks of Project testTitle: \n" +
                "\n" +
                "1 41340433-7709-40d8-99c5-c576309f690a - testTitle0\n" +
                "2 92d064a9-2ae6-420b-b426-a4a899bc6e1a - testTitle1\n" +
                "3 0dc02f14-1434-405b-b110-04aae925b85b - testTitle2\n" +
                "\n" +
                "\n" +
                "What do you want to do? \n" +
                "\n" +
                "Type 1 to edit a task from this project.\n" +
                "Type 2 to add a new task to this project.\n" +
                "Type 3 to delete a task from this project.\n" +
                "Type 4 to edit the user permissions of this project. \n" +
                "\n" +
                "Which task? (1 - 3) \n" +
                "\n");
    }

    @Test
    void deleteTaskWithPermission_chooseInvalidOnce_thenEditPermissionsTest() {
        EditProjectMenu editProjectMenu = new EditProjectMenu(createInputStreamForInput("3\n0\n1\n4\n"), output,
                editTaskMenu, addTaskToProjectMenu, editProjectPermissionsMenu, deleteTaskUseCase, getProjectUseCase,
                projectMenu);
        Task toBeDeleted = project.getTaskList().get(0);
        List<Task> taskList = new ArrayList<>();
        taskList.add(project.getTaskList().get(1));
        taskList.add(project.getTaskList().get(2));
        Project projectAfterDeletion = new Project(project.getId(), taskList, project.getTitle(),
                project.getPermissions());

        when(getProjectUseCase.getProject(any())).thenReturn(projectAfterDeletion);
        editProjectMenu.start(user, project);

        verify(getProjectUseCase).getProject(project.getId());
        verify(editProjectPermissionsMenu).start(any(), any());
        verify(deleteTaskUseCase).deleteTaskFromProject(toBeDeleted, project);
        assertThat(output.toString()).hasToString("These are all the tasks of Project testTitle: \n" +
                "\n" +
                "1 41340433-7709-40d8-99c5-c576309f690a - testTitle0\n" +
                "2 92d064a9-2ae6-420b-b426-a4a899bc6e1a - testTitle1\n" +
                "3 0dc02f14-1434-405b-b110-04aae925b85b - testTitle2\n" +
                "\n" +
                "\n" +
                "What do you want to do? \n" +
                "\n" +
                "Type 1 to edit a task from this project.\n" +
                "Type 2 to add a new task to this project.\n" +
                "Type 3 to delete a task from this project.\n" +
                "Type 4 to edit the user permissions of this project. \n" +
                "\n" +
                "Which task? (1 - 3) \n" +
                "\n" +
                "Invalid Choice. \n" +
                "\n" +
                "Which task? (1 - 3) \n" +
                "\n" +
                "These are all the tasks of Project testTitle: \n" +
                "\n" +
                "1 92d064a9-2ae6-420b-b426-a4a899bc6e1a - testTitle1\n" +
                "2 0dc02f14-1434-405b-b110-04aae925b85b - testTitle2\n" +
                "\n" +
                "\n" +
                "What do you want to do? \n" +
                "\n" +
                "Type 1 to edit a task from this project.\n" +
                "Type 2 to add a new task to this project.\n" +
                "Type 3 to delete a task from this project.\n" +
                "Type 4 to edit the user permissions of this project. \n" +
                "\n");
    }

    @Test
    void deleteTaskWithoutPermission_chooseInvalidOnce() {
        EditProjectMenu editProjectMenu = new EditProjectMenu(createInputStreamForInput("3\n4\n3\n"), output,
                editTaskMenu, addTaskToProjectMenu, editProjectPermissionsMenu, deleteTaskUseCase, getProjectUseCase,
                projectMenu);

        project = new Project(project.getId(), project.getTaskList(), project.getTitle(),
                Map.of(RandomTestData.getRandomUser(), Boolean.TRUE));

        editProjectMenu.start(user, project);

        verify(projectMenu).start(user);
        assertThat(output.toString()).hasToString("These are all the tasks of Project testTitle: \n" +
                "\n" +
                "1 41340433-7709-40d8-99c5-c576309f690a - testTitle0\n" +
                "2 92d064a9-2ae6-420b-b426-a4a899bc6e1a - testTitle1\n" +
                "3 0dc02f14-1434-405b-b110-04aae925b85b - testTitle2\n" +
                "\n" +
                "\n" +
                "What do you want to do? \n" +
                "\n" +
                "Type 1 to edit a task from this project.\n" +
                "Type 2 to add a new task to this project.\n" +
                "Type 3 to delete a task from this project.\n" +
                "Type 4 to edit the user permissions of this project. \n" +
                "\n" +
                "Which task? (1 - 3) \n" +
                "\n" +
                "Invalid Choice. \n" +
                "\n" +
                "Which task? (1 - 3) \n" +
                "\n" +
                "You do not have the necessary permissions to delete this task. \n" +
                "\n");
    }

    @Test
    void addTaskToProjectTest() {
        EditProjectMenu editProjectMenu = new EditProjectMenu(createInputStreamForInput("2\n"), output,
                editTaskMenu, addTaskToProjectMenu, editProjectPermissionsMenu, deleteTaskUseCase, getProjectUseCase,
                projectMenu);

        editProjectMenu.start(user, project);

        verify(addTaskToProjectMenu).start(project, user);
        assertThat(output.toString()).hasToString("These are all the tasks of Project testTitle: \n" +
                "\n" +
                "1 41340433-7709-40d8-99c5-c576309f690a - testTitle0\n" +
                "2 92d064a9-2ae6-420b-b426-a4a899bc6e1a - testTitle1\n" +
                "3 0dc02f14-1434-405b-b110-04aae925b85b - testTitle2\n" +
                "\n" +
                "\n" +
                "What do you want to do? \n" +
                "\n" +
                "Type 1 to edit a task from this project.\n" +
                "Type 2 to add a new task to this project.\n" +
                "Type 3 to delete a task from this project.\n" +
                "Type 4 to edit the user permissions of this project. \n" +
                "\n");
    }
}
