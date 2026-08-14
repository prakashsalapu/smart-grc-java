package exception;

public class DepartmentNotFoundException extends Throwable{
    public DepartmentNotFoundException(String message){
        super(message);
    }
}
