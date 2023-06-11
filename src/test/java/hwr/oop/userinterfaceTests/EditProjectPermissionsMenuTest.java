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
import java.util.Map;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EditProjectPermissionsMenuTest {
    @Mock
    EditProjectMenu editProjectMenu;
    @Mock
    GetUsersUseCase getUsersUseCase;
    @Mock
    ChangePermissionUseCase changePermissionUseCase;
    OutputStream output;
    Project project;
    User user;

    @BeforeEach
    void setUp() {
        output = new ByteArrayOutputStream();
        user = new User(UUID.fromString("41340433-7709-40d8-99c5-c576309f690a"), "testUser", null,
                null);
        project = new Project(UUID.fromString("92d064a9-2ae6-420b-b426-a4a899bc6e1a"), null,
                "testProject", Map.of(user, Boolean.TRUE));

    }

    private InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }

    @Test
    void editWithoutPermissionsTest() {
        EditProjectPermissionsMenu editProjectPermissionsMenu = new EditProjectPermissionsMenu(
                createInputStreamForInput(""), output, editProjectMenu, getUsersUseCase, changePermissionUseCase);

        editProjectPermissionsMenu.start(project, RandomTestData.getRandomUser());

        assertThat(output.toString()).hasToString(
                "You do not have the necessary permissions to edit the permissions of this project\n");
    }


}
