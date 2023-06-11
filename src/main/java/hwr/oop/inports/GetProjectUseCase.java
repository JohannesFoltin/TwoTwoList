package hwr.oop.inports;

import hwr.oop.application.Project;

import java.util.UUID;

public interface GetProjectUseCase {
    Project getProject(UUID id);
}
