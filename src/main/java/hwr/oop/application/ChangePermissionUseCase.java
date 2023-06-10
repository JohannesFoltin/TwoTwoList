package hwr.oop.application;

public interface ChangePermissionUseCase {
    public void changePermission(Project project, User user, Boolean permission);
    public void removePermissionUser(Project project, User user);

}
