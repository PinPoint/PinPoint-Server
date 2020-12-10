package de.pinpoint.server.response;

import de.pinpoint.server.request.UuidRequest;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UuidResponse extends Response {

    private UUID uuid;

    /**
     * Constructs a failed UuidResponse.
     *
     * @param statusCode http error code
     * @param message    fail reason
     */
    public UuidResponse(int statusCode, String message) {
        super(statusCode, message);
    }

    /**
     * Constructs a UuidResponse with the given uuid.
     *
     * @param uuid
     */
    public UuidResponse(UUID uuid) {
        super();
        this.uuid = uuid;
    }
}