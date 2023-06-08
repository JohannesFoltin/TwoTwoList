package hwr.oop.UserInterface;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;



public class menu implements Interfacemenu{

    private final Scanner inputStream;
    private final PrintStream outputStream;

    public menu(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = new Scanner(inputStream);
        this.outputStream = new PrintStream(outputStream);
    }

    public void start() {
        String input;
        boolean i = true;

        //user


        do {
            outputStream.println("Welcome to your ToDo-List App");
            outputStream.println("1: open inbox");
            outputStream.println("2: open contextlist");
            outputStream.println("3: open projects");
            outputStream.println("4: close app");
            input = inputStream.nextLine();

            switch (input) {
                case "1":
                    inbox inbox = new inbox(System.out,System.in);
                    inbox.istart();
                    break;
                case "2":
                    contextlist contextlist = new contextlist;
                    contextlist.klstart();
                    break;
                case "3":
                     projects projects = new projects(;
                    projects.pstart();
                    break;
                case "4" : i = false;
                    break;
                default:
                    outputStream.println("This is an invalid input");


            }
        } while (i);
    }
}
