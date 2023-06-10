package hwr.oop.application;

public class CannotDeleteTaskException extends RuntimeException{
    public CannotDeleteTaskException(String message) {
        super(message);
    }

}
