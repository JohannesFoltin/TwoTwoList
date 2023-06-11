package hwr.oop.applicationTest;

import hwr.oop.application.GetProjectService;
import hwr.oop.application.Project;
import hwr.oop.persistence.AppData;
import hwr.oop.persistence.PersistenceAdapter;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.*;

class GetProjectTest {
    PersistenceAdapter load;
    PersistenceAdapter save;
    AppData appData;

    @Test
    void getProjectTest() {
        load = new PersistenceAdapter("./OOPTest/");
        save = new PersistenceAdapter("./OOPTest/");
        appData = new AppData(new ArrayList<>(), new ArrayList<>());
        Project project = RandomTestData.getRandomProject();
        appData.addProject(project);
        save.saveData(appData);

        GetProjectService getProjectService = new GetProjectService(load);

        assertThat(getProjectService.getProject(project.getId())).isEqualTo(project);
    }
}
