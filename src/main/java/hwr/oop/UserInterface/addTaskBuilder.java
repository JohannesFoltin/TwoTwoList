package hwr.oop.UserInterface;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class addTaskBuilder {

    private final Scanner inputStream;
    private final PrintStream outputStream;
    private String taskName;
    private int taskId;
    public addTaskBuilder(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = new Scanner(inputStream);
        this.outputStream = new PrintStream(outputStream);
    }
    public void get_taskName() {
        OutputStream.Println("Enter a taskname:");
        taskName = InputStream.nextLine();
        return taskName;
    }

    public void get_taskid() {
        OutputStream.Println("Enter a taskID:");
        taskId = InputStream.nextLine();
        return taskId;
    }
}
