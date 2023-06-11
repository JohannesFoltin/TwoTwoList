package hwr.oop.userinterface;

import hwr.oop.application.ValidateUserUseCase;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
public class Inbox {

    private final Scanner input;
    private final PrintStream out;

    public Inbox(InputStream input, OutputStream out) {
        this.input = new Scanner(input);
        this.out = new PrintStream(out);
    }

    public void start() {
        out.println("What do you wanna do??????");
        out.println("Type 1 to add an Entry");
        out.println("Type 2 to delete an Entry");
        out.println("Type 3 to create a task from an inbox entry");
        String choice = input.nextLine();

        if (choice.equals("1")) {
            addInbox.start();
        } else if (choice.equals("2")) {
            deleteInbox.start();
        } else if (choice.equals("3")) {
            addTaskfromInbox.start();
        }
    }
}