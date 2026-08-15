package org.smartgrc.exception;

public class DepartmentNotFoundException extends Throwable{
    public DepartmentNotFoundException(String message){
        super(message);
    }
}
