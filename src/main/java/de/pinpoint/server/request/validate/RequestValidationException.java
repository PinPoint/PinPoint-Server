package de.pinpoint.server.request.validate;

public class RequestValidationException extends Exception {
    public RequestValidationException(String message){
        super(message);
    }
}