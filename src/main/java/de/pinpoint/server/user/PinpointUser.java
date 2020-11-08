package de.pinpoint.server.user;

import de.pinpoint.server.position.PinpointPosition;
import lombok.Data;

import java.util.UUID;

@Data
public class PinpointUser {
    private UUID uuid;
    private String name;
    private PinpointPosition position;
    private long lastPositionUpdateStamp;
    private boolean alive;

    public PinpointUser(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        this.alive = true;
    }

    public UserInfo getInfo(){
        return new UserInfo(this.uuid, this.name, this.position);
    }
}
