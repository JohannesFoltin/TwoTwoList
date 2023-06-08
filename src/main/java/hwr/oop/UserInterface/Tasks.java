package hwr.oop.UserInterface;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;




public class Tasks {

    private final Scanner inputStream;
    private final PrintStream outputStream;

    public Tasks(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = new Scanner(inputStream);
        this.outputStream = new PrintStream(outputStream);
    }

    public void pstart() {
        String input;

        outputStream.println("Willkommen zu deinen Projekten");
        outputStream.println("2: list tasks");
        outputStream.println("3: create task");
        outputStream.println("4: delete task");
        outputStream.println("5: change attributes");
        outputStream.println("6: change taskstate");
        outputStream.println("4: back to home");
        input = inputStream.nextLine();

        switch (input) {
            case "1":


        }
    }
}
