package de.pinpoint.server.request.validate;

import de.pinpoint.server.position.PinpointPosition;
import de.pinpoint.server.request.UserInfoPostRequest;
import de.pinpoint.server.user.UserInfo;

import java.util.regex.Pattern;

public class UserInfoPostRequestValidator extends AbstractRequestValidator<UserInfoPostRequest> {
    private Pattern namePattern = Pattern.compile("[\\w]{3,16}");
    private Pattern colorPattern = Pattern.compile("^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$");

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
        PinpointPosition position = info.getPosition();
        double lat = position.getLatitude();
        double lon = position.getLongitude();
        if(lat < -85.05112877980658  || lat > 85.05112877980658) {
            throw new RequestValidationException("invalid latitude in request");
        }
        if(lon < -180.0  || lon > 180.0) {
            throw new RequestValidationException("invalid longitude in request");
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
        if(!colorPattern.matcher(info.getColor()).matches()){
            throw new RequestValidationException("invalid color in request");
        }
    }
}
