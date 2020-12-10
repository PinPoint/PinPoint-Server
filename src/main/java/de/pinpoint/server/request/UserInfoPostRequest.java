package de.pinpoint.server.request;

import de.pinpoint.server.user.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserInfoPostRequest extends Request {
    private UserInfo userInfo;

    public UserInfoPostRequest(UUID uuid, UserInfo info) {
        super(uuid);
        this.userInfo = info;
    }
}