package hwr.oop.userinterface;

import hwr.oop.application.DeleteProjectUseCase;
import hwr.oop.application.GetProjectsOfUserUseCase;
import hwr.oop.application.Project;
import hwr.oop.application.User;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class ProjectMenu {

    private final Scanner input;
    private final PrintStream output;
    private final GetProjectsOfUserUseCase getProjectsOfUserUseCase;
    private final DeleteProjectUseCase deleteProjectUseCase;
    private final EditProjectMenu editProjectMenu;
    private final CreateProjectMenu createProjectMenu;

    public ProjectMenu(InputStream input, OutputStream output, GetProjectsOfUserUseCase getProjectsOfUserUseCase,
                       DeleteProjectUseCase deleteProjectUseCase, EditProjectMenu editProjectMenu,
                       CreateProjectMenu createProjectMenu) {
        this.input = new Scanner(input);
        this.output = new PrintStream(output);
        this.getProjectsOfUserUseCase = getProjectsOfUserUseCase;
        this.deleteProjectUseCase = deleteProjectUseCase;
        this.editProjectMenu = editProjectMenu;
        this.createProjectMenu = createProjectMenu;
    }

    public void start(User user) {
        List<Project> projects = getProjectsOfUserUseCase.getProjects(user);
        listProjects(projects);

        output.print("What do you want to do? \n\n");
        output.print("Type 1 to edit a Project \n\n");
        output.print("Type 2 to create a new Project \n\n");
        output.print("Type 3 to delete a Project \n\n");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            editProject(projects, user);
        } else if (choice.equals("2")) {
            createProject(user);
        } else if (choice.equals("3")) {
            deleteProject(projects, user);
        } else {
            output.print("Choice invalid. \n\n");
            start(user);
        }
    }

    public void listProjects(List<Project> projects) {
        output.print("These are your projects: \n\n");
        for (int i = 0; i < projects.size(); i++) {
            output.print(i+1 + ": " + projects.get(i).toString()+"\n");
        }
    }

    public Project chooseProject(List<Project> projects) {
        output.print("Which project? (1 - " + projects.size() + ") \n\n");
        String choice = input.nextLine();
        if (Integer.parseInt(choice) < 1 || Integer.parseInt(choice) > projects.size()) {
            output.print("Invalid Choice. \n\n");
            return chooseProject(projects);
        } else {
            return projects.get(Integer.parseInt(choice) - 1);
        }
    }

    public void deleteProject(List<Project> projects, User user) {
        Project toBeDeleted = chooseProject(projects);
        if (!toBeDeleted.getPermissions().containsKey(user) ||
                toBeDeleted.getPermissions().get(user).equals(Boolean.FALSE)) {
            output.print("You do not have the necessary permissions to delete this Project. \n\n");
            start(user);
        } else {
            deleteProjectUseCase.deleteProject(toBeDeleted);
        }
    }

    public void editProject(List<Project> projects, User user) {
        Project toBeEdited = chooseProject(projects);
        if (!toBeEdited.getPermissions().containsKey(user) ||
                toBeEdited.getPermissions().get(user).equals(Boolean.FALSE)) {
            output.print("You do not have the necessary permissions to edit this Project. \n\n");
            start(user);
        } else {
            editProjectMenu.start(user, toBeEdited);
        }
    }

    public void createProject(User user) {
        createProjectMenu.start(user);
    }
}
