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

    public String get_taskName() {
        outputStream.println("Enter a taskname:");
        taskName = inputStream.nextLine();
        return taskName;
    }

    public int get_taskid() {
        outputStream.println("Enter a taskID:");
        taskId = inputStream.nextInt();
        return taskId;
    }
}
