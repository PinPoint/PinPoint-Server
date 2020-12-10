package de.pinpoint.server;

import de.pinpoint.server.user.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Sent by the client to update it's own position.
 */
@Getter
@AllArgsConstructor
public class UpdateUserRequest {
    private UserInfo user;
}
