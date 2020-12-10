package de.pinpoint.server.response;

import de.pinpoint.server.user.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class UserListResponse extends Response {
    private List<UserInfo> users;

    /**
     * Constructs a failed UuidResponse.
     *
     * @param statusCode http error code
     * @param message fail reason
     */
    public UserListResponse(int statusCode, String message) {
        super(statusCode, message);
    }

    /**
     * Constructs a UserListResponse with the given UserInfo list.
     *
     * @param users
     */
    public UserListResponse(List<UserInfo> users){
        super();
        this.users = users;
    }

    /* TODO Should we clone the list here?*/
    public List<UserInfo> getUsers() {
        return users;
    }
}