package exception;

public class DuplicateEmployeeException extends Throwable{
    public DuplicateEmployeeException(String message){
        super(message);
    }
}

