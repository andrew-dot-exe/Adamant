package com.andrewexe.editor.exceptions;

public class ParsingFailedException extends Exception {
    public ParsingFailedException(int line) {
        super(String.format("Parsing line %d failed", line));
    }

}
