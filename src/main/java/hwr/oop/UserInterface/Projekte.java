package hwr.oop.UserInterface;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;



public class Projekte {
    private final Scanner inputStream;
    private final PrintStream outputStream;
    public  Projekte(InputStream inputStream, OutputStream outputStream){
        this.inputStream = new Scanner(inputStream);
        this.outputStream = new PrintStream(outputStream);
    }
    public void pstart() {
        String input;

        outputStream.println("Willkommen zu deinen Projekten");
        outputStream.println("1: Projekt öfnnen");
        outputStream.println("2: Projekte anzeigen");
        outputStream.println("3: Projekt erstellen");
        outputStream.println("4: Projekt löschen");
        outputStream.println("5: Projekt umbenennen");
        outputStream.println("6: zurück zur Startseite");
        input = inputStream.nextLine();

        switch (input) {
            case "3": addProjectBuilder addProjectBuilder = new addProjectBuilder(System.in,System.out)



        }
    }
}
