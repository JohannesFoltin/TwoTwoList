package hwr.oop.userinterface;

import hwr.oop.application.ChangePermissionUseCase;
import hwr.oop.application.Project;
import hwr.oop.application.User;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EditProjectPermissionsMenu {
    private final Scanner input;
    private final PrintStream output;
    private final EditProjectMenu editProjectMenu;
    private final ChangePermissionUseCase changePermissionUseCase;

    public EditProjectPermissionsMenu(InputStream input, OutputStream output, EditProjectMenu editProjectMenu,
                                      ChangePermissionUseCase changePermissionUseCase) {
        this.input = new Scanner(input);
        this.output = new PrintStream(output);
        this.editProjectMenu = editProjectMenu;
        this.changePermissionUseCase = changePermissionUseCase;
    }

    public void start(Project project, User user) {
        checkPermissions(project, user);
        output.println("What do you want to do? ");
        output.println("Type 1 to edit or delete a");
        User toBeEdited = chooseUser(project, user);
    }

    void checkPermissions(Project project, User user) {
        if (!project.getPermissions().containsKey(user) || project.getPermissions().get(user).equals(Boolean.FALSE)) {
            output.println("You do not have the necessary permissions to edit the permissions of this project");
            editProjectMenu.start(user, project);
        }
    }

    User chooseUser(Project project, User user) {
        List<User> userList = new ArrayList<>(project.getPermissions().keySet());
        output.println("These are the users, that currently have Permissions on this project. ");
        for (int i = 0; i < userList.size(); i++) {
            output.println(i+1 + ": " + userList.get(i));
        }
        output.println("Which users permission do you want to edit? (1 - " + userList.size() + ") \n");
        String choice = input.nextLine();
        if (Integer.parseInt(choice) < 1 || Integer.parseInt(choice) > userList.size()) {
            output.println("Invalid input, please try again. \n");
            return chooseUser(project, user);
        } else {
            if (userList.get(Integer.parseInt(choice)).equals(user)) {
                output.println("You can't edit your own permission. \n");
                return chooseUser(project, user);
            } else {
                return userList.get(Integer.parseInt(choice));
            }
        }
    }
}
