package hwr.oop.userinterface;

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

    public ProjectMenu(Scanner input, PrintStream output, ListProjectsOfUserUseCase listProjectsOfUserUseCase) {
        this.input = input;
        this.output = output;
        this.listProjectsOfUserUseCase = listProjectsOfUserUseCase;
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

        if (choice == "1") {
            editProject(user);
        } else if (choice == "2") {
            createProject(user);
        } else if (choice == "3") {
            deleteProject(projects);
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

    private void deleteProject(List<Project> projects) {
        Project toBeDeleted = chooseProject(projects);
    }

    void editProject(User user) {

    }
    private void createProject(User user) {

    }
}
