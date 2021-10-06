package com.api.customized.errors;

import java.time.LocalDate;

public class InputUserRequiredException extends RuntimeException {
    public InputUserRequiredException(Object name) {
              super("Les champs 'name', 'dateNaissance', 'countryResidence' sont obligatoires") ;

    }


}
