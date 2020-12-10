package de.pinpoint.server.request.validate;

import de.pinpoint.server.request.UserListRequest;
import de.pinpoint.server.request.UuidRequest;

public class UserListRequestValidator extends AbstractRequestValidator<UserListRequest> {
    @Override
    protected void validateContent(UserListRequest request) throws RequestValidationException {
        /* Well there is nothing to validate in this request...*/
    }
}
