package de.pinpoint.server.position;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PinpointPosition {
    private double longitude, latitude;
}
