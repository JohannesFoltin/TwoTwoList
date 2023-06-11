package hwr.oop.userinterface;

import hwr.oop.application.CreateProjectUseCase;
import hwr.oop.application.Task;
import hwr.oop.application.User;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateProjectMenu {

    private final Scanner input;
    private final PrintStream output;
    private final CreateProjectUseCase createProjectUseCase;
    private final ProjectMenu projectMenu;

    public CreateProjectMenu(InputStream input, OutputStream output, CreateProjectUseCase createProjectUseCase, ProjectMenu projectMenu) {
        this.input = new Scanner(input);
        this.output = new PrintStream(output);
        this.createProjectUseCase = createProjectUseCase;
        this.projectMenu = projectMenu;
    }

    public void start(User user) {
        output.println("What do you want to name your new project? \n");
        String title = input.nextLine();
        if (title.isEmpty() || title.isBlank()) {
            output.println("Input invalid, please try again. \n");
            start(user);
        } else {
            createProjectUseCase.createProject(title, new ArrayList<>(), user);
            projectMenu.start(user);
        }
    }

    public void start(User user, Task task) {
        output.println("What do you want to name your new project? \n");
        String title = input.nextLine();
        if (title.isEmpty() || title.isBlank()) {
            output.println("Input invalid, please try again. \n");
            start(user, task);
        } else {
            createProjectUseCase.createProject(title, List.of(task), user);
            projectMenu.start(user);
        }
    }
}
