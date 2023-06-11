package hwr.oop.userinterface;

import hwr.oop.application.*;

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
    private final DeleteTaskUseCase deleteTaskUseCase;
    private final GetProjectUseCase getProjectUseCase;

    public EditProjectMenu(InputStream input, OutputStream output,
                           EditTaskMenu editTaskMenu,
                           AddTaskToProjectMenu addTaskToProjectMenu,
                           EditProjectPermissionsMenu editProjectPermissionsMenu, DeleteTaskUseCase deleteTaskUseCase, GetProjectUseCase getProjectUseCase) {
        this.input = new Scanner(input);
        this.output = new PrintStream(output);
        this.editTaskMenu = editTaskMenu;
        this.addTaskToProjectMenu = addTaskToProjectMenu;
        this.editProjectPermissionsMenu = editProjectPermissionsMenu;
        this.deleteTaskUseCase = deleteTaskUseCase;
        this.getProjectUseCase = getProjectUseCase;
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
        if (!project.getPermissions().containsKey(user) || project.getPermissions().get(user).equals(Boolean.FALSE)) {
            output.println("You do not have the necessary permissions to delete this task. \n");
            start(user, project);
        } else {
            deleteTaskUseCase.deleteTaskFromProject(task, project);
            start(user, getProjectUseCase.getProject(project.getId()));
        }
    }

    private Task chooseTask(Project project) {
        output.println("Which task? (1 - " + project.getTaskList().size() + ") \n");
        String choice = input.nextLine();
        if (Integer.parseInt(choice) < 1 || Integer.parseInt(choice) > project.getTaskList().size()) {
            output.println("Invalid Choice. \n");
            return chooseTask(project);
        } else {
            return project.getTaskList().get(Integer.parseInt(choice) - 1);
        }
    }

    private void listTasks(Project project) {
        output.println("These are all the tasks of Project " + project.getTitle() + ": \n");
        for (int i=0; i<project.getTaskList().size(); i++) {
            output.println(i+1 + " " + project.getTaskList().get(i));
        }
    }
}
