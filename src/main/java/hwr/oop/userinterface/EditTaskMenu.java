package hwr.oop.userinterface;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import hwr.oop.application.*;

public class EditTaskMenu {
    private final Scanner input;
    private final PrintStream output;

    private final ChangeTaskUseCase changeTaskUseCase;

    public EditTaskMenu(InputStream input, OutputStream output, ChangeTaskUseCase changeTaskUseCase) {
        this.input = new Scanner(input);
        this.output = new PrintStream(output);
        this.changeTaskUseCase = changeTaskUseCase;
    }

    public void startWithUser(Task task,User user) {
        output.print("What do you want to do?\n");
        output.println("Type 1 to edit the task title.");
        output.println("Type 2 to edit the task content.");
        output.println("Type 3 to edit the task state.");
        output.println("Type 4 to edit the task deadline.");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            editTaskTitle(task);
        } else if (choice.equals("2")) {
            editTaskContent(task);
        } else if (choice.equals("3")) {
            editTaskState(task);
        } else if (choice.equals("4")) {
            editTaskDeadline(task);
        } else {
            output.println("Choice invalid. \n");
            startWithUser(task,user);
        }
    }
    public void startWithProjekt(Task task, Project project) {
        output.println("What do you want to do?");
        output.println("Type 1 to edit the task title.");
        output.println("Type 2 to edit the task content.");
        output.println("Type 3 to edit the task state.");
        output.println("Type 4 to edit the task deadline.");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            editTaskTitle(task);
        } else if (choice.equals("2")) {
            editTaskContent(task);
        } else if (choice.equals("3")) {
            editTaskState(task);
        } else if (choice.equals("4")) {
            editTaskDeadline(task);
        } else {
            output.println("Choice invalid. \n");
            //start(task);
        }
    }

    public void editTaskTitle(Task task) {
        output.println("Please enter a new title for your Task\n");
        String title = input.nextLine();
    }

    public void editTaskContent(Task task) {
        output.println("Please enter a new content for your Task\n");
        String content = input.nextLine();
        // ...
    }

    public void editTaskState(Task task) {
        output.println("Please enter a new state for your Task\n");
        String state = input.nextLine();
        // ...
    }

    public void editTaskDeadline(Task task) {
        output.println("Please enter a new deadline for your Task\n");
        String deadline = input.nextLine();
        // ...
    }
}
