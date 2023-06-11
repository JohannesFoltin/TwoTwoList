package hwr.oop.application;

public class CannotChangeTaskException extends RuntimeException{
    public CannotChangeTaskException(String message) {
        super(message);
    }
}
