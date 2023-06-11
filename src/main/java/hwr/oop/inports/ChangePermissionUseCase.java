package hwr.oop.inports;

import hwr.oop.application.Project;
import hwr.oop.application.User;

public interface ChangePermissionUseCase {
    void changePermission(Project project, User user, Boolean permission);
    void removePermission(Project project, User user);

}
