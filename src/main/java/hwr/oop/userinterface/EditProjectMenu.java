package hwr.oop.userinterface;

import hwr.oop.application.Project;
import hwr.oop.application.Task;
import hwr.oop.application.User;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class EditProjectMenu {
    private final Scanner input;
    private final PrintStream output;
    private final EditTaskMenu editTaskMenu;
    private final AddTaskToProjectMenu addTaskToProjectMenu;
    private final EditProjectPermissionsMenu editProjectPermissionsMenu;

    public EditProjectMenu(InputStream input, OutputStream output,
                           EditTaskMenu editTaskMenu,
                           AddTaskToProjectMenu addTaskToProjectMenu,
                           EditProjectPermissionsMenu editProjectPermissionsMenu) {
        this.input = new Scanner(input);
        this.output = new PrintStream(output);
        this.editTaskMenu = editTaskMenu;
        this.addTaskToProjectMenu = addTaskToProjectMenu;
        this.editProjectPermissionsMenu = editProjectPermissionsMenu;
    }

    public void start(User user, Project project) {

    }


}
