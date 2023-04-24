package hwr.oop.clicode;

import hwr.oop.dataclasses.Task;
import hwr.oop.dataclasses.TaskList;
import hwr.oop.dataclasses.TaskState;
import hwr.oop.dataclasses.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public List<Task> taskList;

    public static void main(String[] args) {
        System.out.println("we have a main class");
    }
    public void createTask(String title, String content, User creator,LocalDate deadline){
        Task task = new Task(getID(),title,content,TaskState.IN_PROGRESS,null,creator, LocalDate.now(),deadline);
        taskList.add(task);
    }

    public int getID(){
        return 0;
    }
}
