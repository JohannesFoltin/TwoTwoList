package hwr.oop.application;

import hwr.oop.persistence.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.SavePort;

public class DeleteProjectService implements DeleteProjectUseCase {
    private LoadPort load;
    private SavePort save;

    public DeleteProjectService(LoadPort load, SavePort save) {
        this.load = load;
        this.save = save;
    }

    @Override
    public void deleteProject(Project project) {
        AppData appData = load.loadData();
        if (!appData.getProjectList().contains(project)) {
            throw new CantDeleteNonexistentProjectException("Project not in AppData");
        }
        appData.deleteProject(project);
        save.saveData(appData);
    }
}
