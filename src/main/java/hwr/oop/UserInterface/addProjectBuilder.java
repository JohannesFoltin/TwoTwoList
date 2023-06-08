package hwr.oop.UserInterface;



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
    
    public String get_projectname() {
        outputStream.println("Enter a projectname:");
        projectName = inputStream.nextLine();
        return projectName;
    }

    public int get_projectid() {
        outputStream.println("Enter a ProjectId:");
        projectId = inputStream.nextInt();
        return projectId;
    }
}
