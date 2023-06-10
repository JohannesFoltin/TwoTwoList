package hwr.oop.application;

import hwr.oop.persistence.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.SavePort;

public class ChangePermissionService implements ChangePermissionUseCase {

    private final LoadPort loadPort;
    private final SavePort savePort;

    public ChangePermissionService(LoadPort loadPort, SavePort savePort) {
        this.loadPort = loadPort;
        this.savePort = savePort;
    }
    @Override
    public void changePermission(Project project, User user, Boolean permission){
        int ind = loadPort.loadData().getProjectList().indexOf(project);
        if(ind>=0) {
            AppData appData = loadPort.loadData();
            appData.getProjectList().get(ind).changePermission(user,permission);
            savePort.saveData(appData);
        }
        else{
            throw new CanNotFindProjectForPermissionChange("project not found");
        }
    }

    @Override
    public void removePermissionUser(Project project, User user){
        int ind = loadPort.loadData().getProjectList().indexOf(project);
        if(ind>=0) {
            AppData appData = loadPort.loadData();
            appData.getProjectList().get(ind).removePermissionUser(user);
            savePort.saveData(appData);
        }
        else{
            throw new CanNotFindProjectForPermissionChange("project not found");
        }
    }
}
