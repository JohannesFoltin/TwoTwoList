package hwr.oop.UserInterface;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class inbox {

    private final Scanner inputStream;
    private final PrintStream outputStream;
    public  inbox(InputStream inputStream, OutputStream outputStream){
        this.inputStream = new Scanner(inputStream);
        this.outputStream = new PrintStream(outputStream);
    }
    public void istart() {
        String input;

        outputStream.println("Willkommen in deiner Inbox");
        outputStream.println("1: Eintrag hinzufügen");
        outputStream.println("2: Eintrag erwitern");
        outputStream.println("3: Eintrag löschen");
        outputStream.println("4: zurück zur Startseite");
        input = inputStream.nextLine();

        switch (input) {
            case "1":

        }
    }
}