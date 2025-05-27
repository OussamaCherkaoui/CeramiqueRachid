package com.CeramiqueRachid.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("Admin not found !");
    }
}
