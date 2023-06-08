package hwr.oop.application;

import hwr.oop.persistence.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.SavePort;

public class AllowUserToReadService implements AllowUserToReadUseCase{

    private final LoadPort loadPort;
    private final SavePort savePort;

    public AllowUserToReadService(LoadPort loadPort, SavePort savePort) {
        this.loadPort = loadPort;
        this.savePort = savePort;
    }
    @Override
    public void addReadingPermission(Project project, User user){
        int ind = loadPort.loadData().getProjectList().indexOf(project);
        if(ind>=0) {
            AppData appData = loadPort.loadData();
            appData.getProjectList().get(ind).changePermission(user,false);
            savePort.saveData(appData);
        }
        else{
            throw new PermissionsException("project not found");
        }
    }
}
