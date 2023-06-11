package hwr.oop.application;
import hwr.oop.inports.ChangeProjectTitleUseCase;
import hwr.oop.outports.LoadPort;
import hwr.oop.outports.SavePort;

public class ChangeProjectTitleService implements ChangeProjectTitleUseCase {
    private final LoadPort loadPort;
    private final SavePort savePort;

    public ChangeProjectTitleService(LoadPort loadPort, SavePort savePort) {
        this.loadPort = loadPort;
        this.savePort = savePort;
    }

    @Override
    public void changeTitle(Project project, String title){
        int ind =loadPort.loadData().getProjectList().indexOf(project);
        if(ind>=0){
            AppData appData= loadPort.loadData();
            appData.getProjectList().get(ind).changeTitle(title);
            savePort.saveData(appData);
        }
        else{
            throw new ChangeTitleException("Project not found");
        }
    }
}
