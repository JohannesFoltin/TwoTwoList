package hwr.oop.userinterface;

import hwr.oop.application.User;
import hwr.oop.application.ValidateUserUseCase;
import hwr.oop.persistence.UserNotInAppDataException;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Login {
    private final Scanner input;
    private final PrintStream out;
    private final MainMenu mainMenu;
    private final ValidateUserUseCase validateUserUseCase;

    public Login(InputStream input, OutputStream out, MainMenu mainMenu, ValidateUserUseCase validateUserUseCase) {
        this.input = new Scanner(input);
        this.out = new PrintStream(out);
        this.mainMenu = mainMenu;
        this.validateUserUseCase = validateUserUseCase;
    }

    public void start() {
        out.println("What do you wanna do??????");
        out.println("Type 1 to login");
        out.println("Type 2 to register a new user");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            loginLogin();
        } else if (choice.equals("2")) {
            registerUser();
        }

    }

    public void loginLogin() {
        out.println("Enter Username:\n");
        String name = input.nextLine();

        User user;
        try {
            user = validateUserUseCase.validateUser(name);
            mainMenu.start(user);
        } catch (UserNotInAppDataException e) {
            out.println("Username not found");
            start();
        }
    }

    public void registerUser() {
        out.println("Enter username for new user:\n");

    }
}
