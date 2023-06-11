package hwr.oop.userinterface;

import hwr.oop.application.*;

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
    private final DeleteTaskUseCase deleteTaskUseCase;

    public ContextMenu(InputStream input, OutputStream out, EditTaskMenu editTaskMenu,
                       CreateTaskUseCase createTaskUseCase, DeleteTaskUseCase deleteTaskUseCase) {
        this.input = new Scanner(input);
        this.output = new PrintStream(out);
        this.editTaskMenu = editTaskMenu;
        this.createTaskUseCase = createTaskUseCase;
        this.deleteTaskUseCase = deleteTaskUseCase;
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
            deleteTask(contextlist,user);
        } else if (choice.equals("3")) {
            editTask(contextlist);
        } else {
            output.println("Choice invalid. \n");
            start(user);
        }
    }
    public void editTask(List<Task> contextlist){
        output.println("Please choose the number of the task you want to edit");
        Integer taskNumber = Integer.parseInt(input.nextLine());
        if (contextlist.size()>=taskNumber){
            editTaskMenu.start(contextlist.get(taskNumber));
        }
        else{
            output.println("Invalid Number");
            editTask(contextlist);
        }
    }
    public void listTasks(List<Task> tasks) {
        output.print("These are your tasks: \n");
        for (int i = 0; i < tasks.size(); i++) {
            output.print(i+1 + ": " + tasks.get(i).getId().toString()+" "+tasks.get(i).getTitle()+"\n");
        }
    }
    public void createTask(User user){
        output.println("Please enter a title for your Task\n");
        String title= input.nextLine();
        output.println("Please enter the content for your Task\n");
        String content= input.nextLine();
        TaskState taskState =taskStateChoice();
        LocalDateTime deadline= deadline();
        createTaskUseCase.createTaskInContextList(title,content, taskState, deadline, user);
    }
    LocalDateTime deadline(){
        output.println("Please enter a deadline for the Task in the format yyyy-mm-dd HH:MM\n");
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
        output.println("Type 3 if your Task is in review\n");
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
    public void deleteTask(List<Task> taskList, User user){
        output.println("Please choose the number of the task you want to delete");
        Integer taskNumber = Integer.parseInt(input.nextLine());
        if (taskList.size()>=taskNumber){
            Task task = taskList.get(taskNumber-1);
            deleteTaskUseCase.deleteTaskFromContextList(task, user);
        }
        else{
            output.println("Invalid Number");
            deleteTask(taskList,user);
        }

    }
}
