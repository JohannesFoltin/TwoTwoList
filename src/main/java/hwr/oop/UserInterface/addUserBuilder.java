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

    public void get_username() {
        OutputStream.println("Enter a username:");
        UserName = InputStream.nextLine();
        return UserName;
    }

    public void get_userid() {
        OutputStream.Println("Enter a UserId:");
        UserId = InputStream.nextLine();
        return UserId;
    }
}