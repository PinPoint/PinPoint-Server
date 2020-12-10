package de.pinpoint.server.request;

import java.util.UUID;

/**
 * The client uses this request to get a uniwue id for the user.
 * The uuid field of the Request will just be a random uuid and the
 * server returns a unique one (may be the same)
 */
public class UuidRequest extends Request {
    public UuidRequest(UUID uuid) {
        super(uuid);
    }

    public UuidRequest(){};
}