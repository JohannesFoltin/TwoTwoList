package hwr.oop.application;

public class ProjectNotInAppDataException extends RuntimeException{
    public ProjectNotInAppDataException(String errorMessage) { super(errorMessage); }
}
