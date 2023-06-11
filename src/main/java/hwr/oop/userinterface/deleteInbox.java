package hwr.oop.userinterface;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class deleteInbox {

    private final Scanner input;
    private final PrintStream out;

    public deleteInbox(InputStream input, OutputStream out) {
        this.input = new Scanner(input);
        this.out = new PrintStream(out);
    }

    public void start() {

        out.println("Type the Entry you want to delete:\n");
    }
}
