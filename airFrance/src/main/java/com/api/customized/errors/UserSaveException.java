package com.api.customized.errors;


import org.springframework.beans.factory.annotation.Value;

public class UserSaveException extends RuntimeException{
    public UserSaveException(String nom) {
        super("Enregistrement impossible de l'utilisateur " +nom+ "  car il ne reside pas en France.");
    }
}
