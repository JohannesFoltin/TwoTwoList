package hwr.oop.application.deletetask;

public class CannotDeleteTaskException extends RuntimeException{
    public CannotDeleteTaskException(String message) {
        super(message);
    }

}
