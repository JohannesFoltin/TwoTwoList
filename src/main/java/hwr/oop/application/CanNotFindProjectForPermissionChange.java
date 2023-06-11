package hwr.oop.application;

public class CanNotFindProjectForPermissionChange extends RuntimeException{
    public CanNotFindProjectForPermissionChange(String message){
        super(message);
    }
}
