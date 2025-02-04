package hwr.oop.applicationTest;
import hwr.oop.application.*;
import hwr.oop.outports.LoadPort;
import hwr.oop.application.AppData;
import hwr.oop.outports.SavePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.*;


class PermissionTest {
    private LoadPort loadPort;
    private MySavePort savePort;
    private ChangePermissionService changePermissionService;
    AppData appDataMock;
    @BeforeEach
    void setUp() {
        List<Project> projects = new ArrayList<>();
        UUID id = UUID.randomUUID();

        List<User> users = new ArrayList<>();
        users.add(new User(UUID.randomUUID(),"TestUser",new ArrayList<>(),new ArrayList<>()));
        Map<User, Boolean> permissions= new HashMap<>();
        projects.add(new Project(id,new ArrayList<>(),"Test Project",permissions));
        projects.get(0).getPermissions().put(users.get(0),true);

        loadPort = () -> appDataMock;
        savePort = new MySavePort();
        appDataMock = new AppData(projects,users);

        changePermissionService = new ChangePermissionService(loadPort, savePort);
    }
    @Test
    void RemoveUserTest(){
        AppData appData= loadPort.loadData();
        Project project = appData.getProjectList().get(0);
        User user = appData.getUserList().get(0);
        changePermissionService.removePermission(project,user);
        assertThat(savePort.isFlag()).isEqualTo(true);
        assertThat(loadPort.loadData().getProjectList().get(0).getPermissions().containsKey(user)).isFalse();
    }
    @Test
    void RemovePermissionUnsuccessfully(){
        AppData appData= loadPort.loadData();
        Map<User, Boolean> permissions= new HashMap<>();
        Project project = new Project(UUID.randomUUID(),null,"Title",permissions);
        User user = appData.getUserList().get(0);
        try {
            changePermissionService.removePermission(project,user);
            fail("project not found");
        }catch (CanNotFindProjectForPermissionChange e){
            e.printStackTrace();
        }
    }

    @Test
    void removePermissionsWrongUserTest(){
        Project project = RandomTestData.getRandomProject();
        User user = RandomTestData.getRandomUser();
        AppData appData = new AppData(List.of(project), List.of(user));
        savePort.saveData(appData);
        try {
            changePermissionService.removePermission(project, user);
            fail("should throw exception");
        } catch (CanNotFindUserToRemove e) {
            e.printStackTrace();
        }
    }

    @Test
    void ChangePermissionUnsuccessfully(){
        AppData appData= loadPort.loadData();
        Map<User, Boolean> permissions= new HashMap<>();
        Project project = new Project(UUID.randomUUID(),null,"Title",permissions);
        User user = appData.getUserList().get(0);
        try {
            changePermissionService.changePermission(project,user,true);
            fail("project not found");
        } catch (CanNotFindProjectForPermissionChange e){
            e.printStackTrace();
        }
    }
    @Test
    void ChangePermissionTest(){
        AppData appData= loadPort.loadData();
        Project project = appData.getProjectList().get(0);
        User user = appData.getUserList().get(0);
        changePermissionService.changePermission(project,user,false);
        assertThat(savePort.isFlag()).isEqualTo(true);
        assertThat(loadPort.loadData().getProjectList().get(0).getPermissions().get(user)).isFalse();
    }
    private class MySavePort implements SavePort {
        private boolean flag = false;

        public boolean isFlag() {
            return flag;
        }

        @Override
        public void saveData(AppData appData) {
            this.flag = true;
            appDataMock = appData;
        }
    }


}
