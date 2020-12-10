package de.pinpoint.server.request.validate;

import de.pinpoint.server.request.UserInfoPostRequest;
import de.pinpoint.server.user.UserInfo;

import java.util.regex.Pattern;

public class UserInfoPostRequestValidator extends AbstractRequestValidator<UserInfoPostRequest> {
    private Pattern namePattern = Pattern.compile("[\\w]{3,16}");

    @Override
    protected void validateContent(UserInfoPostRequest request) throws RequestValidationException {
        if (request.getUserInfo() == null) {
            throw new RequestValidationException("no userinfo provided in request");
        }
        UserInfo info = request.getUserInfo();
        if (!request.getUserId().equals(info.getUuid())) {
            throw new RequestValidationException("request userId does not match userInfo");
        }
        if (info.getPosition() == null) {
            throw new RequestValidationException("missing position in request");
        }
        if (info.getColor() == null) {
            throw new RequestValidationException("missing color in request");
        }
        if(info.getName() == null){
            throw new RequestValidationException("missing name in request");
        }
        if (!namePattern.matcher(info.getName()).matches()) {
            throw new RequestValidationException("invalid name in request");
        }
    }
}
