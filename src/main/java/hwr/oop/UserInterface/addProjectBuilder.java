package hwr.oop.UserInterface;



import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class addProjectBuilder {

<<<<<<< HEAD
private final Scanner inputStream;
private final PrintStream outputStream;
private String projectName;
private int projectId;
=======
    private final Scanner inputStream;
    private final PrintStream outputStream;
    private String projectName;
>>>>>>> 00d037f8831515a97c1b86580954a5d7883673bc

    public addProjectBuilder(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = new Scanner(inputStream);
        this.outputStream = new PrintStream(outputStream);
    }
<<<<<<< HEAD
    
    public String get_projectname() {
        outputStream.println("Enter a projectname:");
        projectName = inputStream.nextLine();
        return projectName;
    }

    public int get_projectid() {
        outputStream.println("Enter a ProjectId:");
        projectId = inputStream.nextInt();
=======

    public void get_projectname() {
        outputStream.println("Enter a projectname:");
        projectName = inputStream.nextLine();
        return projectName;
    }

    public String get_projectid() {
        outputStream.println("Enter a ProjectId:");
        String projectId = inputStream.nextLine();
>>>>>>> 00d037f8831515a97c1b86580954a5d7883673bc
        return projectId;
    }
}