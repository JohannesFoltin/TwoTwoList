package hwr.oop.application;

public class CantSaveAppDataException extends RuntimeException{
    public CantSaveAppDataException(String message) {
        super(message);
    }
}
