package de.pinpoint.server.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class Request {
    /**
     * The uuid of the client's user the request was sent from.
     */
    private UUID userId;

    public Request(){};
}