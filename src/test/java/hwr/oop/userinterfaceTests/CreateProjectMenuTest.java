package hwr.oop.userinterfaceTests;

import hwr.oop.application.CreateProjectUseCase;
import hwr.oop.application.Project;
import hwr.oop.application.Task;
import hwr.oop.application.User;
import hwr.oop.applicationTest.RandomTestData;
import hwr.oop.userinterface.CreateProjectMenu;
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

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateProjectMenuTest {
    @Mock
    CreateProjectUseCase createProjectUseCase;
    @Mock
    ProjectMenu projectMenu;
    OutputStream outputStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
    }

    private InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }

    @Test
    void createProject_withOneInvalidInput_withoutTaskTest() {
        User user = RandomTestData.getRandomUser();
        Project project = new Project(UUID.randomUUID(), new ArrayList<>(), "testTitle", Map.of(user, Boolean.TRUE));
        CreateProjectMenu createProjectMenu = new CreateProjectMenu(createInputStreamForInput("   \ntestTitle\n"),
                outputStream, createProjectUseCase, projectMenu);

        when(createProjectUseCase.createProject(any(), any(), any())).thenReturn(project);
        createProjectMenu.start(user);

        verify(createProjectUseCase).createProject("testTitle", new ArrayList<>(), user);
        verify(projectMenu).start(user);
        assertThat(outputStream.toString()).hasToString("What do you want to name your new project? \n" +
                "\n" +
                "Input invalid, please try again. \n" +
                "\n" +
                "What do you want to name your new project? \n" +
                "\n");
    }

    @Test
    void createProject_withOneInvalidInput_withTaskTest() {
        Task task = RandomTestData.getRandomTask();
        User user = RandomTestData.getRandomUser();
        Project project = new Project(UUID.randomUUID(), List.of(task), "testTitle", Map.of(user, Boolean.TRUE));
        CreateProjectMenu createProjectMenu = new CreateProjectMenu(createInputStreamForInput("\ntestTitle\n"),
                outputStream, createProjectUseCase, projectMenu);

        when(createProjectUseCase.createProject(any(), any(), any())).thenReturn(project);
        createProjectMenu.start(user, task);

        verify(createProjectUseCase).createProject("testTitle", List.of(task), user);
        verify(projectMenu).start(user);
        assertThat(outputStream.toString()).hasToString("What do you want to name your new project? \n" +
                "\n" +
                "Input invalid, please try again. \n" +
                "\n" +
                "What do you want to name your new project? \n" +
                "\n");
    }
}
