package hwr.oop.userinterface;

import hwr.oop.application.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;

public class AddTaskToProjectMenu {
    private final Scanner input;
    private final PrintStream output;
    private final EditProjectMenu editProjectMenu;
    private final CreateTaskUseCase createTaskUseCase;

    public AddTaskToProjectMenu(InputStream input, OutputStream output, EditProjectMenu editProjectMenu,
                                CreateTaskUseCase createTaskUseCase) {
        this.input = new Scanner(input);
        this.output = new PrintStream(output);
        this.editProjectMenu = editProjectMenu;
        this.createTaskUseCase = createTaskUseCase;
    }

    public void start(Project project, User user) {
         if (checkPermission(project, user)) {
             output.print("What do you want to name your task? \n\n");
             String title = input.nextLine();
             output.print("Add content of your new task: \n\n");
             String content = input.nextLine();

             TaskState state = chooseTaskState(project);
             Optional<LocalDateTime> deadline = chooseDeadline(project);

             if (deadline.isEmpty()) {
                 createTaskUseCase.createTaskInProject(title, content, state, null, project);
             } else {
                 createTaskUseCase.createTaskInProject(title, content, state, deadline.get(), project);
             }
         } else {
             editProjectMenu.start(user, project);
         }
    }

    private boolean checkPermission(Project project, User user) {
        if (!project.getPermissions().containsKey(user) || project.getPermissions().get(user).equals(Boolean.FALSE)) {
            output.print("You do not have the necessary permissions to add a task to this project\n");
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    private Optional<LocalDateTime> chooseDeadline(Project project) {
        output.print("Do you want to add a deadline to your task? (y/n) \n\n");
        String deadlineChoice = input.nextLine();
        if (deadlineChoice.equals("y")) {
            output.print("Please enter deadline: \n\n");
            try {
                return Optional.of(LocalDateTime.parse(input.nextLine()));
            } catch (Exception e) {
                output.print("Invalid input, please try again. \n");
                return chooseDeadline(project);
            }
        } else if (deadlineChoice.equals("n")) {
            return Optional.empty();
        } else {
            output.print("Invalid input, please try again. \n\n");
            return chooseDeadline(project);
        }
    }

    private TaskState chooseTaskState(Project project) {
        output.print("What state should your task have?\n");
        output.print("Type 1 to set the taskState to IN_PROGRESS\n");
        output.print("Type 2 to set the taskState to BACKLOG \n\n");
        String choice = input.nextLine();
        if (choice.equals("1")) {
            return TaskState.IN_PROGRESS;
        } else if (choice.equals("2")) {
            return TaskState.BACKLOG;
        } else {
            output.print("Invalid input, please try again. \n\n");
            return chooseTaskState(project);
        }
    }
}
