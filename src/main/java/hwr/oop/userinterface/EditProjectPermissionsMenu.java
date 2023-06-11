package hwr.oop.userinterface;

import hwr.oop.application.ChangePermissionUseCase;
import hwr.oop.application.Project;
import hwr.oop.application.User;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class EditProjectPermissionsMenu {
    private final Scanner input;
    private final PrintStream output;
    private final EditProjectMenu editProjectMenu;
    private final ChangePermissionUseCase changePermissionUseCase;

    public EditProjectPermissionsMenu(InputStream input, OutputStream output, EditProjectMenu editProjectMenu, ChangePermissionUseCase changePermissionUseCase) {
        this.input = new Scanner(input);
        this.output = new PrintStream(output);
        this.editProjectMenu = editProjectMenu;
        this.changePermissionUseCase = changePermissionUseCase;
    }

    public void start(Project project, User user) {
    }
}
