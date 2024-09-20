package com.test2.exception;

public class InvalidInput extends  RuntimeException{

    public InvalidInput(String msg){
        super(msg);
    }

}
