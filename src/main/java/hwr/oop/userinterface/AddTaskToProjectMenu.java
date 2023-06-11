package hwr.oop.userinterface;

import hwr.oop.application.*;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Optional;
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

        TaskState state = chooseTaskState(project);
        Optional<LocalDateTime> deadline = chooseDeadline(project);

        if (deadline.isEmpty()) {
            createTask(title, content, state);
        } else {
            createTask(title, content, state, deadline.get());
        }
    }

    private Optional<LocalDateTime> chooseDeadline(Project project) {
        output.println("Do you want to add a deadline to your task? (y/n) \n");
        String deadlineChoice = input.nextLine();
        if (deadlineChoice.equals("y")) {
            output.println("Please enter deadline: \n");
            try {
                return Optional.of(LocalDateTime.parse(input.nextLine()));
            } catch (Exception e) {
                output.println("Invalid input, please try again. ");
                return chooseDeadline(project);
            }
        } else if (deadlineChoice.equals("n")) {
            return Optional.empty();
        } else {
            output.println("Invalid input, please try again. \n");
            return chooseDeadline(project);
        }
    }

    private TaskState chooseTaskState(Project project) {
        output.println("What state should your task have?");
        output.println("Type 1 to set the taskState to IN_PROGRESS");
        output.println("Type 2 to set the taskState to BACKLOG \n");
        String choice = input.nextLine();
        if (choice.equals("1")) {
            return TaskState.IN_PROGRESS;
        } else if (choice.equals("2")) {
            return TaskState.BACKLOG;
        } else {
            output.println("Invalid input, please try again. \n");
            return chooseTaskState(project);
        }
    }


    private void createTask(String title, String content, TaskState state) {

    }

    private void createTask(String title, String content, TaskState state, LocalDateTime deadline) {

    }
}
