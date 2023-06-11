package hwr.oop.applicationTest;

import hwr.oop.application.AddTaskToProjectService;
import hwr.oop.application.Project;
import hwr.oop.application.Task;
import hwr.oop.persistence.AppData;
import hwr.oop.persistence.PersistenceAdapter;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class AddTaskToProjectTest {
    PersistenceAdapter load;
    PersistenceAdapter save;
    AppData appData;

    @Test
    void addTaskToProjectTest() {
        load = new PersistenceAdapter("./OOPTest/");
        save = new PersistenceAdapter("./OOPTest/");
        Project project = RandomTestData.getRandomProject();
        appData = new AppData(List.of(project), new ArrayList<>());
        save.saveData(appData);
        Task task = RandomTestData.getRandomTask();


        AddTaskToProjectService addTaskToProjectService = new AddTaskToProjectService(load, save);

        assertThat(addTaskToProjectService.addTaskToProject(project, task).getTaskList()).contains(task);
        assertThat(load.loadProjectById(project.getId()).getTaskList()).contains(task);
    }
}
