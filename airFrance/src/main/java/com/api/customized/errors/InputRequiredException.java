package com.api.customized.errors;

public class InputRequiredException extends RuntimeException {
    public InputRequiredException(Object name) {
        super("Le champs " + name.getClass().getName() + " est obligatoire");
    }


}
