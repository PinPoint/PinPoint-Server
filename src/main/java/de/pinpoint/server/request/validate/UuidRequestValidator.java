package de.pinpoint.server.request.validate;

import de.pinpoint.server.request.UuidRequest;

public class UuidRequestValidator extends AbstractRequestValidator<UuidRequest> {
    @Override
    protected void validateContent(UuidRequest request) throws RequestValidationException {
        /* Well there is nothing to validate in this request...*/
    }
}
