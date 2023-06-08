package hwr.oop.UserInterface;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class addUserBuilder {


    private final Scanner inputStream;
    private final PrintStream outputStream;
    private String UserName;
    private int UserId;

    public addUserBuilder(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = new Scanner(inputStream);
        this.outputStream = new PrintStream(outputStream);
    }

    public String get_username() {
        outputStream.println("Enter a username:");
        UserName = inputStream.nextLine();
        return UserName;
    }

    public int get_userid() {
        outputStream.println("Enter a UserId:");
        UserId = inputStream.nextInt();
        return UserId;
    }
}