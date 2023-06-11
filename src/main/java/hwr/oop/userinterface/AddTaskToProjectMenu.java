package hwr.oop.userinterface;

import hwr.oop.application.AddTaskToProjectUseCase;
import hwr.oop.application.Project;
import hwr.oop.application.User;

import java.io.PrintStream;
import java.util.Scanner;

public class AddTaskToProjectMenu {
    private final Scanner input;
    private final PrintStream output;
    private final AddTaskToProjectUseCase addTaskToProjectUseCase;
    private final EditProjectMenu editProjectMenu;

    public AddTaskToProjectMenu(Scanner input, PrintStream output, AddTaskToProjectUseCase addTaskToProjectUseCase,
                                EditProjectMenu editProjectMenu) {
        this.input = input;
        this.output = output;
        this.addTaskToProjectUseCase = addTaskToProjectUseCase;
        this.editProjectMenu = editProjectMenu;
    }

    public void start(Project project, User user) {
    }
}
