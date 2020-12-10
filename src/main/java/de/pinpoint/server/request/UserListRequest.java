package de.pinpoint.server.request;

import java.util.UUID;

/**
 * This is used by the client to request a list of all active users.
 */
public class UserListRequest extends Request {
    public UserListRequest(UUID userId) {
        super(userId);
    }
    public UserListRequest(){};
}
