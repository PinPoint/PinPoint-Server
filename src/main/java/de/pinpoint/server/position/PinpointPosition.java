package de.pinpoint.server.position;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PinpointPosition {
    private double longitude, latitude;

    /**
     * Calculates the distance between 2 PinpointPositions.
     * Source: https://stackoverflow.com/questions/837872/calculate-distance-in-meters-when-you-know-longitude-and-latitude-in-java
     *
     * @return distance in meters
     */
    public float distance(PinpointPosition other) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(other.getLatitude() - this.latitude);
        double dLng = Math.toRadians(other.longitude - this.longitude);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(other.longitude)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        float dist = (float) (earthRadius * c);
        return dist;
    }
}
