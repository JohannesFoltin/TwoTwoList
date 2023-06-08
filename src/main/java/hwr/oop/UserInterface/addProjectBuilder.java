package hwr.oop.UserInterface;



import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class addProjectBuilder {

    private final Scanner inputStream;
    private final PrintStream outputStream;
    private String projectName;

    public addProjectBuilder(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = new Scanner(inputStream);
        this.outputStream = new PrintStream(outputStream);
    }

    public void get_projectname() {
        outputStream.println("Enter a projectname:");
        projectName = inputStream.nextLine();
        return projectName;
    }

    public String get_projectid() {
        outputStream.println("Enter a ProjectId:");
        String projectId = inputStream.nextLine();
        return projectId;
    }
}