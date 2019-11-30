package com.payment.demo.exceptions;

public class BadFormatCardStatusException extends Exception {
    public BadFormatCardStatusException(String message) {
        super(message);
    }
}
