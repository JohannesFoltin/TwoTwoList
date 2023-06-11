package hwr.oop.userinterface;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class addTaskfromInbox {

    private final Scanner input;
    private final PrintStream out;

    public addTaskfromInbox(InputStream input, OutputStream out) {
        this.input = new Scanner(input);
        this.out = new PrintStream(out);

    }
}
