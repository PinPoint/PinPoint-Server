package de.pinpoint.server.user;

import de.pinpoint.server.position.PinpointPosition;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class PinpointUser {
    private UUID uuid;
    @Setter
    private String name;
    @Setter
    private String color;
    private PinpointPosition position;
    private long lastPositionUpdateStamp;
    @Setter
    private boolean alive;

    public PinpointUser(UUID uuid, String name, String color) {
        this.uuid = uuid;
        this.name = name;
        this.alive = true;
    }

    public UserInfo getInfo(){
        return new UserInfo(this.uuid, this.name, this.color, this.position);
    }

    public void updatePosition(PinpointPosition position){
        this.position = position;
        this.lastPositionUpdateStamp = System.currentTimeMillis();
        this.alive = true;
    }
}
