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

    public void startWithUser(Task task, User user) {
        output.print("What do you want to do?\n");
        output.print("Type 1 to edit the task title.\n");
        output.print("Type 2 to edit the task content.\n");
        output.print("Type 3 to edit the task state.\n");
        output.print("Type 4 to edit the task deadline.\n");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            editTaskTitleInUser(task, user);
        } else if (choice.equals("2")) {
            editTaskContentInUser(task, user);
        } else if (choice.equals("3")) {
            choseTaskState(task);
        } else if (choice.equals("4")) {
            editTaskDeadlineInUser(task, user);
        } else {
            output.println("Choice invalid. \n");
            startWithUser(task, user);
        }
    }

    public void startWithProject(Task task, Project project) {
        output.print("What do you want to do?\n");
        output.print("Type 1 to edit the task title.\n");
        output.print("Type 2 to edit the task content.\n");
        output.print("Type 3 to edit the task state.\n");
        output.print("Type 4 to edit the task deadline.\n");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            editTaskTitleInProject(task, project);
        } else if (choice.equals("2")) {
            editTaskContentInProject(task, project);
        } else if (choice.equals("3")) {
            choseTaskState(task);
        } else if (choice.equals("4")) {
            editTaskDeadlineInProject(task, project);
        } else {
            output.println("Choice invalid. \n");
            startWithProject(task, project);
        }
    }

    public TaskState choseTaskState(Task task) {
        output.print("What do you want to do?\n");
        output.print("Type 1 to set the task state to 'BACKLOG'.\n");
        output.print("Type 2 to set the task state to 'IN_PROGRESS'.\n");
        output.print("Type 3 to set the task state to 'IN_REVIEW'.\n");
        output.print("Type 4 to set the task state to 'DONE'.\n");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            return TaskState.BACKLOG;
        } else if (choice.equals("2")) {
            return TaskState.IN_PROGRESS;
        } else if (choice.equals("3")) {
            return TaskState.IN_REVIEW;
        } else if (choice.equals("4")) {
            return TaskState.DONE;
        } else {
            output.println("Choice invalid. \n");
            choseTaskState(task);
        }
        return null;
    }

    public void editTaskTitleInUser(Task task, User user) {
        output.println("Please enter a new title for your Task\n");
        String newTitle = input.nextLine();
        changeTaskUseCase.changeTitleInUser(task, newTitle, user);
    }

    public void editTaskContentInUser(Task task, User user) {
        output.println("Please enter a new content for your Task\n");
        String newContent = input.nextLine();
        changeTaskUseCase.changeContentInUser(task, newContent, user);
    }

    public void editTaskStateInUser(Task task, User user) {
        TaskState taskState = choseTaskState(task);
        changeTaskUseCase.changeTaskStateInUser(task, taskState, user);
    }

    public void editTaskDeadlineInUser(Task task, User user) {
        output.println("Please enter a new deadline for your Task\n");
        String newDeadline = input.nextLine();
        changeTaskUseCase.changeDeadlineInUser(task, newDeadline, user);
    }

    public void editTaskTitleInProject(Task task, Project project) {
        output.println("Please enter a new title for your Task\n");
        String newTitle = input.nextLine();
        changeTaskUseCase.changeTitleInProject(task, newTitle, project);
    }

    public void editTaskContentInProject(Task task, Project project) {
        output.println("Please enter a new content for your Task\n");
        String newContent = input.nextLine();
        changeTaskUseCase.changeContentInProject(task, newContent, project);
    }

    public void editTaskStateInProject(Task task, Project project) {
        TaskState taskState = choseTaskState(task);
        changeTaskUseCase.changeTaskStateInProject(task, taskState, project);
    }

    public void editTaskDeadlineInProject(Task task, Project project) {
        output.println("Please enter a new deadline for your Task\n");
        String newDeadline = input.nextLine();
        changeTaskUseCase.changeDeadlineInProject(task, newDeadline, project);
    }
}
