package hwr.oop.application;

import hwr.oop.persistence.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.SavePort;

public class PermissionsService implements PermissionsUseCase{
    private final LoadPort loadPort;
    private final SavePort savePort;

    public PermissionsService(LoadPort loadPort, SavePort savePort) {
        this.loadPort = loadPort;
        this.savePort = savePort;
    }
    public void removePermissionUser(Project project, User user){
        int ind = loadPort.loadData().getProjectList().indexOf(project);
        if(ind>=0) {
            loadPort.loadData().getProjectList().get(ind).removePermissionUser(user);
        }
        else{
            throw new PermissionsException("project not found");
        }
    }

}
