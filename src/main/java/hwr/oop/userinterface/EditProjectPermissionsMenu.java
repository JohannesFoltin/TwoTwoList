package hwr.oop.userinterface;

import hwr.oop.application.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class EditProjectPermissionsMenu {
    private final Scanner input;
    private final PrintStream output;
    private final EditProjectMenu editProjectMenu;
    private final GetUsersUseCase getUsersUseCase;
    private final ChangePermissionUseCase changePermissionUseCase;

    public EditProjectPermissionsMenu(InputStream input, OutputStream output, EditProjectMenu editProjectMenu,
                                      GetUsersUseCase getUsersUseCase, ChangePermissionUseCase changePermissionUseCase) {
        this.input = new Scanner(input);
        this.output = new PrintStream(output);
        this.editProjectMenu = editProjectMenu;
        this.getUsersUseCase = getUsersUseCase;
        this.changePermissionUseCase = changePermissionUseCase;
    }

    public void start(Project project, User user) {
        checkPermissions(project, user);
        User toBeEdited = chooseUser(user);
        changePermission(project, toBeEdited, user);
    }

    void checkPermissions(Project project, User user) {
        if (!project.getPermissions().containsKey(user) || project.getPermissions().get(user).equals(Boolean.FALSE)) {
            output.println("You do not have the necessary permissions to edit the permissions of this project");
            editProjectMenu.start(user, project);
        }
    }

    User chooseUser(User user) {
        List<User> userList = getUsersUseCase.getUsers();
        output.println("These are all current users: ");
        for (int i = 0; i < userList.size(); i++) {
            output.println(i+1 + ": " + userList.get(i));
        }

        output.println("Which users permission do you want to edit? (1 - " + userList.size() + ") \n");
        String choice = input.nextLine();
        if (Integer.parseInt(choice) < 1 || Integer.parseInt(choice) > userList.size()) {
            output.println("Invalid input, please try again. \n");
            return chooseUser(user);
        } else {
            if (userList.get(Integer.parseInt(choice)).equals(user)) {
                output.println("You can't edit your own permission. \n");
                return chooseUser(user);
            } else {
                return userList.get(Integer.parseInt(choice));
            }
        }
    }

    private void changePermission(Project project, User toBeEdited, User user) {
        output.print("What Permission should this user have on this project? \n");
        output.print("Type 1 if the user should not have any permissions. \n");
        output.print("Type 2 if the user should have read-access. \n");
        output.print("Type 3 if the user should have write-access. \n");
        String choice = input.nextLine();
        if (choice.equals("1")) {
            changePermissionUseCase.removePermission(project, toBeEdited);
        } else if (choice.equals("2")) {
            changePermissionUseCase.changePermission(project, toBeEdited, Boolean.FALSE);
        } else if (choice.equals("3")) {
            changePermissionUseCase.changePermission(project, toBeEdited, Boolean.TRUE);
        } else {
            output.print("Input invalid, please try again. \n");
            changePermission(project, toBeEdited, user);
        }
        editProjectMenu.start(user, project);
    }
}
