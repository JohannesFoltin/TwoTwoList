package hwr.oop.UserInterface;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class kontextliste {

    private final Scanner inputStream;
    private final PrintStream outputStream;

    public  kontextliste(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = new Scanner(inputStream);
        this.outputStream = new PrintStream(outputStream);
    }

    public void klstart() {
        String input;

        outputStream.println("Welcome to your Kontextliste");
        outputStream.println("1: add entry");
        outputStream.println("2: delete entry");
        outputStream.println("3: list entries");
        outputStream.println("4: back");
        input = inputStream.nextLine();

        switch (input) {
            case "1":
            case "4": break;





        }
    }
}
