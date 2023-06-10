package hwr.oop.applicationTest;

import hwr.oop.application.ListProjectsOfUserService;
import hwr.oop.application.Project;
import hwr.oop.application.User;
import hwr.oop.persistence.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.PersistenceAdapter;
import hwr.oop.persistence.SavePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.*;


class ListProjectsOfUserTest {
    LoadPort load;
    SavePort save;
    AppData appData;
    ListProjectsOfUserService service;
    List<Project> projects;
    User user;

    @BeforeEach
    void setUp() {
        load = new PersistenceAdapter("./OOPTest");
        save = new PersistenceAdapter("./OOPTest");
        appData = new AppData(new ArrayList<>(), new ArrayList<>());
        service = new ListProjectsOfUserService(load);

        projects = new ArrayList<>();
        user = RandomTestData.getRandomUser();
        for (int i=0; i<5; i++) {
            Project rando = RandomTestData.getRandomProject();
            projects.add(new Project(rando.getId(), null, null, Map.of(user, Boolean.TRUE)));
        }
    }

    @Test
    void listProjectsTest() {
        for (Project project : projects) {
            appData.addProject(project);
        }
        save.saveData(appData);

        assertThat(service.listProjects(user)).isEqualTo(load.loadAllUserProjects(user.getId()));
    }
}
