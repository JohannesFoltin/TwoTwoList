package hwr.oop.dataclassesTest;

import hwr.oop.application.CreateTaskService;
import hwr.oop.application.Task;
import hwr.oop.application.TaskState;
import hwr.oop.persistence.LoadTaskPort;
import hwr.oop.persistence.SaveTaskPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class CanCreateTaskTest {

    //The "Persistence". You can copy that (and the setUp() Method) for future Tests
    LoadTaskPort loadTaskPort;
    SaveTaskPort saveTaskPort;
    List<Task> tasks;

    //Is executed before every Test
    @BeforeEach
    void setUp() {
        tasks = new ArrayList<>();
        //Add empty Task (for fun ;) )
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

            }
        };
    }

    @Test
    void canCreateTask(){
        //JavaClass init
        CreateTaskService createTaskService = new CreateTaskService(loadTaskPort,saveTaskPort);

        LocalDateTime now = LocalDateTime.now();
        createTaskService.createTask("Test","sa",TaskState.DONE,null,null, now);

        //Get last Task (that theoretically has been added)
        Task createdTask = tasks.get(tasks.size()-1);

        //Assertions
        assertThat(createdTask.getTitle()).isEqualTo("Test");
        assertThat(createdTask.getContent()).isEqualTo("sa");
        assertThat(createdTask.getTaskState()).isEqualTo(TaskState.DONE);
        assertThat(createdTask.getCreator()).isNull();
        createdTask.getDeadline().ifPresent(localDate -> assertThat(localDate).isEqualTo(now));
    }
}
