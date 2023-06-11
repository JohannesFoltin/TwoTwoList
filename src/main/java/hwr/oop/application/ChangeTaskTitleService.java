package hwr.oop.application;
import hwr.oop.persistence.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.SavePort;

public class ChangeTaskTitleService implements ChangeTaskTitleUseCase {
    private final LoadPort loadPort;
    private final SavePort savePort;

    public ChangeTaskTitleService(LoadPort loadPort, SavePort savePort) {
        this.loadPort = loadPort;
        this.savePort = savePort;
    }

    @Override
    public void changeTitle(Task task, String newTitle){
        int ind =loadPort.loadData().getTaskList().indexOf(task);
        if(ind>=0){
            AppData appData= loadPort.loadData();
            appData.getTaskList().get(ind).changeTitle(newTitle);
            savePort.saveData(appData);
        }
        else{
            throw new ChangeTitleException("Task not found");
        }
    }
    
}
