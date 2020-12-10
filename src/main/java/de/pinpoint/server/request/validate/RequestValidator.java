package de.pinpoint.server.request.validate;

import de.pinpoint.server.request.Request;

public interface RequestValidator<T extends Request> {
    public void validate(T request) throws RequestValidationException;
}
