package de.pinpoint.server.request.validate;

import de.pinpoint.server.request.Request;

/**
 * An abstract implementation of RequestValidator that already checks the uuid field.
 *
 * @param <T> type of Request
 */
public abstract class AbstractRequestValidator<T extends Request> implements RequestValidator<T> {
    @Override
    public void validate(T request) throws RequestValidationException {
        if (request.getUserId() == null) {
            throw new RequestValidationException("no uuid provided");
        }
        this.validateContent(request);
    }

    protected abstract void validateContent(T request) throws RequestValidationException;
}