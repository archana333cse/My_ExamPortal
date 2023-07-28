package com.exam1.helper;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super("User with this username not found in DB!! try with another one!!");
    }
    public UserNotFoundException(String msg){
        super(msg);
    }
}
