package hwr.oop.userinterface;

import hwr.oop.application.ValidateUserService;
import hwr.oop.application.ValidateUserUseCase;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.UserNotInAppDataException;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Login {
    private final Scanner input;
    private final PrintStream out;

    private final MainMenu mainMenu;
    private final LoadPort loadPort;

    public Login(InputStream input, OutputStream out, MainMenu mainMenu, LoadPort loadPort) {
        this.input = new Scanner(input);
        this.out = new PrintStream(out);
        this.mainMenu = mainMenu;
        this.loadPort = loadPort;
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
        out.println("Enter Username: \n");
        String name = input.nextLine();

        ValidateUserUseCase validateUserUseCase = new ValidateUserService(loadPort);

        try {
            mainMenu.start(validateUserUseCase.validateUser(name));
        } catch (UserNotInAppDataException e) {
            out.println("Username not found");
            //start();
        }
    }

    public void registerUser() {
        out.println("Enter username for new user: \n");

    }
}
