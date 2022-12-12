package com.example.medicalonlinebookingservice.exception;


public class UserNotFoundException extends RuntimeException{
    @Override
    public String getMessage() {
        return ExceptionConstant.USER_NOT_FOUND;
    }

    public UserNotFoundException(){
        super();
    }

    public UserNotFoundException(String message){
        super(message);
    }
}
