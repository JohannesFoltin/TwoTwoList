package hwr.oop.userinterface;

import hwr.oop.application.CreateTaskUseCase;
import hwr.oop.application.Task;
import hwr.oop.application.TaskState;
import hwr.oop.application.User;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ContextMenu {
    private final Scanner input;
    private final PrintStream output;
    private final EditTaskMenu editTaskMenu;
    private final CreateTaskUseCase createTaskUseCase;

    public ContextMenu(InputStream input, OutputStream out, EditTaskMenu editTaskMenu,
                       hwr.oop.application.CreateTaskUseCase createTaskUseCase) {
        this.input = new Scanner(input);
        this.output = new PrintStream(out);
        this.editTaskMenu = editTaskMenu;
        this.createTaskUseCase = createTaskUseCase;
    }
    public void start(User user) {
        List<Task> contextlist= user.getContextList();
        listTasks(contextlist);
        output.println("What do you want to do?");
        output.println("Type 1 to create a new Task");
        output.println("Type 2 to delete a Task");
        output.println("Type 3 to edit a Task");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            createTask(user);
        } else if (choice.equals("2")) {
            deleteTask();
        } else if (choice.equals("3")) {
            editTaskMenu.start();
        } else {
            output.println("Choice invalid. \n");
            start(user);
        }
    }
    public void listTasks(List<Task> tasks) {
        output.println("These are your tasks: \n");
        for (int i = 0; i < tasks.size(); i++) {
            output.println(i+1 + ": " + tasks.get(i).toString());
        }
    }
    public void createTask(User user){
        output.println("Please enter a title for your Task");
        String title= input.nextLine();
        output.println("Please enter the content for your Task");
        String content= input.nextLine();
        TaskState taskState =taskStateChoice();
        LocalDateTime deadline= deadline();
        createTaskUseCase.createTaskInContextList(title,content, taskState, deadline, user);
    }
    LocalDateTime deadline(){
        output.println("Please enter a deadline for the Task in the format yyyy-mm-dd HH:MM");
        String date= input.nextLine();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(date, formatter);
        }catch (DateTimeParseException e){
            return deadline();
        }
    }
    TaskState taskStateChoice(){
        output.println("Please enter the state your Task is in");
        output.println("Type 1 if your Task is in backlog");
        output.println("Type 2 if your Task is in progress");
        output.println("Type 3 if your Task is in review");
        String choice = input.nextLine();
        if (choice.equals("1")) {
            return TaskState.BACKLOG;
        } else if (choice.equals("2")) {
            return TaskState.IN_PROGRESS;
        } else if (choice.equals("3")) {
            return TaskState.IN_REVIEW;
        } else {
            output.println("Choice invalid. \n");
            return taskStateChoice();
        }
    }
    public void deleteTask(){

    }
}
