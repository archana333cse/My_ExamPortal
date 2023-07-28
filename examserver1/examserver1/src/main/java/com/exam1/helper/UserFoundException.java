package com.exam1.helper;

public class UserFoundException extends Exception{
    public UserFoundException(){
        super(" User with this username already there in DB!! try with another one!");
    }
    public UserFoundException(String msg){super(msg);}
}
