package hwr.oop.application;

import hwr.oop.inports.GetProjectUseCase;
import hwr.oop.outports.LoadPort;
import java.util.UUID;

public class GetProjectService implements GetProjectUseCase {
    private final LoadPort load;

    public GetProjectService(LoadPort load) {
        this.load = load;
    }

    @Override
    public Project getProject(UUID id) {
        return load.loadProjectById(id);
    }
}
