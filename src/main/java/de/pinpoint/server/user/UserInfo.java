package de.pinpoint.server.user;

import de.pinpoint.server.position.PinpointPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserInfo {
    private UUID uuid;
    private String name;
    private String color;
    private PinpointPosition position;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return uuid.equals(userInfo.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }
}