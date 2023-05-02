package com.basejava.cart.exceptions;

public class UserNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 5071646428281007896L;

    public UserNotFoundException(String message){
        super(message);
    }
}
