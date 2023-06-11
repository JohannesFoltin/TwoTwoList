package hwr.oop.applicationTest;

import hwr.oop.application.GetUsersService;
import hwr.oop.application.User;
import hwr.oop.application.AppData;
import hwr.oop.persistence.PersistenceAdapter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GetUsersTest {
    PersistenceAdapter load;
    PersistenceAdapter save;
    AppData appData;

    @Test
    void getUsersTest() {
        load = new PersistenceAdapter("./OOPTest/");
        save = new PersistenceAdapter("./OOPTest/");

        List<User> userList = RandomTestData.getRandomUsers();
        appData = new AppData(new ArrayList<>(), userList);
        save.saveData(appData);

        GetUsersService getUsersService = new GetUsersService(load);

        assertThat(getUsersService.getUsers()).isEqualTo(userList);
    }
}
