package hwr.oop.application.createtask;

public class CannotCreateTaskException extends RuntimeException {
    public CannotCreateTaskException(String message) {
        super(message);
    }
}
