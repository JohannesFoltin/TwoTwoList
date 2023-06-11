package hwr.oop.userinterface;

import hwr.oop.application.*;
import hwr.oop.inports.CreateTaskUseCase;
import hwr.oop.inports.DeleteTaskUseCase;

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
        output.print("What do you want to do\n");
        output.print("Type 1 to create a new Task\n");
        output.print("Type 2 to delete a Task\n");
        output.print("Type 3 to edit a Task\n");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            createTask(user);
        } else if (choice.equals("2")) {
            deleteTask(contextlist,user);
        } else if (choice.equals("3")) {
            editTask(contextlist, user);
        } else {
            output.print("Choice invalid. \n");
            start(user);
        }
    }
    public void editTask(List<Task> contextlist, User user){
        output.print("Please choose the number of the task you want to edit \n");
        Integer taskNumber = Integer.parseInt(input.nextLine());
        if (contextlist.size()>=taskNumber){
            editTaskMenu.startWithUser(contextlist.get(taskNumber),user);
        }
        else{
            output.print("Invalid Number \n");
            editTask(contextlist, user);
        }
    }
    public void listTasks(List<Task> tasks) {
        output.print("These are your tasks: \n");
        for (int i = 0; i < tasks.size(); i++) {
            output.print(i+1 + ": " + tasks.get(i).getId().toString()+" "+tasks.get(i).getTitle()+"\n");
        }
    }
    public void createTask(User user){
        output.print("Please enter a title for your Task \n");
        String title= input.nextLine();
        output.print("Please enter the content for your Task \n");
        String content= input.nextLine();
        TaskState taskState =taskStateChoice();
        LocalDateTime deadline= deadline();
        createTaskUseCase.createTaskInContextList(title,content, taskState, deadline, user);
    }
    LocalDateTime deadline(){
        output.print("Please enter a deadline for the Task in the format yyyy-mm-dd HH:MM \n");
        String date= input.nextLine();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(date, formatter);
        }catch (DateTimeParseException e){
            return deadline();
        }
    }
    TaskState taskStateChoice(){
        output.print("Please enter the state your Task is in\n");
        output.print("Type 1 if your Task is in backlog\n");
        output.print("Type 2 if your Task is in progress\n");
        output.print("Type 3 if your Task is in review\n");
        String choice = input.nextLine();
        if (choice.equals("1")) {
            return TaskState.BACKLOG;
        } else if (choice.equals("2")) {
            return TaskState.IN_PROGRESS;
        } else if (choice.equals("3")) {
            return TaskState.IN_REVIEW;
        } else {
            output.print("Choice invalid. \n");
            return taskStateChoice();
        }
    }
    public void deleteTask(List<Task> taskList, User user){
        output.print("Please choose the number of the task you want to delete\n");
        Integer taskNumber = Integer.parseInt(input.nextLine());
        if (taskList.size()>=taskNumber){
            Task task = taskList.get(taskNumber-1);
            deleteTaskUseCase.deleteTaskFromContextList(task, user);
        }
        else{
            output.print("Invalid Number\n");
            deleteTask(taskList,user);
        }

    }
}
