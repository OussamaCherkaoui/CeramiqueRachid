package com.CeramiqueRachid.exception;

public class DatabaseEmptyException extends RuntimeException{
    public DatabaseEmptyException(){
        super("database is empty ! no data found");
    }
}
