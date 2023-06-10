package hwr.oop.userinterface;

import hwr.oop.application.DeleteProjectUseCase;
import hwr.oop.application.ListProjectsOfUserUseCase;
import hwr.oop.application.Project;
import hwr.oop.application.User;;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class ProjectMenu {

    private final Scanner input;
    private final PrintStream output;
    private final ListProjectsOfUserUseCase listProjectsOfUserUseCase;
    private final DeleteProjectUseCase deleteProject;
    private final EditProjectMenu editProjectMenu;
    private final CreateProjectMenu createProjectMenu;

    public ProjectMenu(Scanner input, PrintStream output, ListProjectsOfUserUseCase listProjectsOfUserUseCase, DeleteProjectUseCase deleteProject, EditProjectMenu editProjectMenu, CreateProjectMenu createProjectMenu) {
        this.input = input;
        this.output = output;
        this.listProjectsOfUserUseCase = listProjectsOfUserUseCase;
        this.deleteProject = deleteProject;
        this.editProjectMenu = editProjectMenu;
        this.createProjectMenu = createProjectMenu;
    }

    public void start(User user) {
        output.println("These are your projects: \n");
        List<Project> projects = listProjectsOfUserUseCase.listProjects(user);
        for (int i = 0; i < projects.size(); i++) {
            output.println(i+1 + ": " + projects.get(i).toString() + "\n");
        }

        output.println("What do you want to do? \n");
        output.println("Type 1 to edit a Project \n");
        output.println("Type 2 to create a new Project \n");
        output.println("Type 3 to delete a Project \n");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            editProject(projects, user);
        } else if (choice.equals("2")) {
            createProject(user);
        } else if (choice.equals("3")) {
            deleteProject(projects, user);
        } else {
            output.println("Choice invalid. \n");
            start(user);
        }
    }

    private Project chooseProject(List<Project> projects) {
        output.println("Which project? (1 - " + projects.size() + ") \n");
        String choice = input.nextLine();
        if (Integer.parseInt(choice) <= 1 || Integer.parseInt(choice) >= projects.size()) {
            output.println("Invalid Choice. \n");
            chooseProject(projects);
        }
        return projects.get(Integer.parseInt(choice)-1);
    }

    private void deleteProject(List<Project> projects, User user) {
        Project toBeDeleted = chooseProject(projects);
        if (toBeDeleted.getPermissions().get(user).equals(Boolean.FALSE)) {
            output.println("You do not have the necessary permissions to delete this Project. \n");
            start(user);
        }
        deleteProject.deleteProject(toBeDeleted);
    }

    void editProject(List<Project> projects, User user) {
        Project toBeEdited = chooseProject(projects);
        if (toBeEdited.getPermissions().get(user).equals(Boolean.FALSE)) {
            output.println("You do not have the necessary permissions to edit this Project. \n");
            start(user);
        }
        editProjectMenu.start();
    }

    private void createProject(User user) {
        createProjectMenu.start();
    }
}
