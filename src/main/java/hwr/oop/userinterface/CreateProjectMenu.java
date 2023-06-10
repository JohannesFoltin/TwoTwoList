package hwr.oop.userinterface;

import hwr.oop.application.CreateProjectUseCase;
import hwr.oop.application.Task;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Optional;
import java.util.Scanner;

public class CreateProjectMenu {

    private final Scanner input;
    private final PrintStream output;
    private final CreateProjectUseCase createProjectUseCase;

    public CreateProjectMenu(InputStream input, OutputStream output, CreateProjectUseCase createProjectUseCase) {
        this.input = new Scanner(input);
        this.output = new PrintStream(output);
        this.createProjectUseCase = createProjectUseCase;
    }

    public void start() {
    }
}
