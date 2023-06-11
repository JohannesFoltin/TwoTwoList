package hwr.oop.userinterface;

import hwr.oop.application.AddTaskToProjectUseCase;
import hwr.oop.application.CreateTaskUseCase;
import hwr.oop.application.Project;
import hwr.oop.application.User;

import java.io.PrintStream;
import java.util.Scanner;

public class AddTaskToProjectMenu {
    private final Scanner input;
    private final PrintStream output;
    private final AddTaskToProjectUseCase addTaskToProjectUseCase;
    private final EditProjectMenu editProjectMenu;
    private final CreateTaskUseCase createTaskUseCase;

    public AddTaskToProjectMenu(Scanner input, PrintStream output, AddTaskToProjectUseCase addTaskToProjectUseCase,
                                EditProjectMenu editProjectMenu, CreateTaskUseCase createTaskUseCase) {
        this.input = input;
        this.output = output;
        this.addTaskToProjectUseCase = addTaskToProjectUseCase;
        this.editProjectMenu = editProjectMenu;
        this.createTaskUseCase = createTaskUseCase;
    }

    public void start(Project project, User user) {
        output.println("What do you want to name your task? \n");
        String title = input.nextLine();
        output.println("Add content of your new task: \n");
        String content = input.nextLine();
        output.println("What state should your task have?");
        output.println("Type 1 to set the taskState to IN_PROGRESS");
        output.println("Type 2 to set the taskState to BACKLOG0 \n");
        String choice = input.nextLine();
        output.println("Do you want to add a deadline to your task? (y/n) \n");
        String deadlineChoice = input.nextLine();
        if (deadlineChoice.equals("y")) {
            output.println("Please enter deadline: \n");
            String deadline = input.nextLine();
            createTask(title, content, choice, deadline);
        } else if (deadlineChoice.equals("n")) {
            createTask(title, content, choice);
        } else {
            output.println("Invalid input, please try again. \n");
            start(project, user);
        }

    }
    private void createTask(String title, String content, String choice) {

    }

    private void createTask(String title, String content, String choice, String deadline) {

    }
}
