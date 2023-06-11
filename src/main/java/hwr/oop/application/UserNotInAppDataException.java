package hwr.oop.application;

public class UserNotInAppDataException extends RuntimeException {
    public UserNotInAppDataException(String errorMessage) { super(errorMessage); }
}
