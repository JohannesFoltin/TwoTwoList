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
        listTasks(project);
        output.println("What do you want to do?");
        output.println("Type 1 to edit a task from this project.");
        output.println("Type 2 to add a new task to this project.");
        output.println("Type 3 to delete a task from this project.");
        output.println("Type 4 to edit the user permissions of this project. \n");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            Task task = chooseTask(project);
            editTaskMenu.start(project, user, task);
        } else if (choice.equals("2")) {
            addTaskToProjectMenu.start(project, user);
        } else if (choice.equals("3")) {
            Task task = chooseTask(project);
            deleteTask(project, user, task);
        } else if (choice.equals("4")) {
            editProjectPermissionsMenu.start(project, user);
        } else {
            output.println("Invalid input, please try again. \n");
            start(user, project);
        }
    }

    private void deleteTask(Project project, User user, Task task) {
    }

    private Task chooseTask(Project project) {
    }

    private void listTasks(Project project) {
        output.println("These are all the tasks of Project " + project.getTitle() + ": \n");
        for (int i=0; i<project.getTaskList().size(); i++) {
            output.println(i+1 + " " + project.getTaskList().get(i));
        }
    }
}
