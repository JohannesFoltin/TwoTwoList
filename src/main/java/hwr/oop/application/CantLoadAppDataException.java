package hwr.oop.application;

public class CantLoadAppDataException extends RuntimeException {
    public CantLoadAppDataException(String message) {
        super(message);
    }
}
