package com.man.cleanup.util;

import java.util.ArrayList;
import java.util.List;

public class Errors {

    private List<String> errors = new ArrayList<String>();

    public void addError(String error) {
        errors.add(error);
    }

    public boolean hasErros() {
        return !errors.isEmpty();
    }

    @Override
    public String toString() {
        return String.join( "\n", errors);
    }
}