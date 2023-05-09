package hwr.oop.dataclassesTest;

import hwr.oop.application.CreateTaskService;
import hwr.oop.application.Task;
import hwr.oop.application.TaskState;
import hwr.oop.persistence.LoadTaskPort;
import hwr.oop.persistence.SaveTaskPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CanCreateTask {
    LoadTaskPort loadTaskPort;
    SaveTaskPort saveTaskPort;

    List<Task> tasks;

    @BeforeEach
    void setUp() {
        tasks = new ArrayList<>();
        Task taskTmp = new Task("","",null,null,null,null);
        tasks.add(taskTmp);
        loadTaskPort = new LoadTaskPort() {
            @Override
            public Task loadTask(int id) {
                return null;
            }

            @Override
            public List<Task> loadTask() {
                return tasks;
            }
        };
        saveTaskPort = new SaveTaskPort() {
            @Override
            public void saveTask(List<Task> list) {
                tasks = list;
            }

            @Override
            public void saveTask(Task task) {
                return;
            }
        };
    }

    @Test
    void canCreateTask(){
        CreateTaskService createTaskService = new CreateTaskService(loadTaskPort,saveTaskPort);
        createTaskService.createTask("Test","sa",TaskState.DONE,null,null, LocalDateTime.now());
        Assertions.assertThat()
    };
}
