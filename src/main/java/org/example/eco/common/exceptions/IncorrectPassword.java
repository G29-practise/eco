package org.example.eco.common.exceptions;


public class IncorrectPassword extends RuntimeException {
    public IncorrectPassword(String message) {
        super(message);
    }
}