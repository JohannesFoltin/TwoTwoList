package hwr.oop.UserInterface;

import hwr.oop.application.CreateProjectService;
import hwr.oop.application.CreateProjectUseCase;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class addProjectBuilder {

    private final Scanner inputStream;
    private final PrintStream outputStream;
 private String projectName;
 private int projectId;
    public addProjectBuilder(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = new Scanner(inputStream);
        this.outputStream = new PrintStream(outputStream);
    }
    public void get_projectname() {
        OutputStream.Println("Enter a projectname:");
        projectName = InputStream.nextLine();
        return projectName;
}

    public void get_projectid() {
        OutputStream.Println("Enter a ProjectId:");
        projectId = InputStream.nextLine();
        return projectId;
    }