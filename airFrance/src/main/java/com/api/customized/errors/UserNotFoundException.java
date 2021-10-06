package com.api.customized.errors;


public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String nom) {
        super("l'utilisateur "+nom + " est inconnu. ");
    }
}
