package hwr.oop.application;

import hwr.oop.inports.ChangePermissionUseCase;
import hwr.oop.outports.LoadPort;
import hwr.oop.outports.SavePort;

public class ChangePermissionService implements ChangePermissionUseCase {

    private final LoadPort loadPort;
    private final SavePort savePort;

    public ChangePermissionService(LoadPort loadPort, SavePort savePort) {
        this.loadPort = loadPort;
        this.savePort = savePort;
    }

    @Override
    public void changePermission(Project project, User user, Boolean permission){
        AppData appData = loadPort.loadData();

        if(!appData.getProjectList().contains(project)) {
            throw new CanNotFindProjectForPermissionChange("project not found");
        }
        Project toBeEdited = loadPort.loadProjectById(project.getId());
        toBeEdited.changePermission(user, permission);
        savePort.saveData(appData);
    }

    @Override
    public void removePermission(Project project, User user){
        AppData appData = loadPort.loadData();

        if(!appData.getProjectList().contains(project)) {
            throw new CanNotFindProjectForPermissionChange("project not found");
        }
        Project toBeEdited = loadPort.loadProjectById(project.getId());

        if (!toBeEdited.getPermissions().containsKey(user)) {
            throw new CanNotFindUserToRemove("user not found");
        }
        toBeEdited.removePermissionUser(user);
        savePort.saveData(appData);
    }
}
