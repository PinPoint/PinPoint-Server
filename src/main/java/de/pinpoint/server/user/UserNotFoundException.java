package de.pinpoint.server.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception {
    public UserNotFoundException(UUID uuid) {
        super("User with uuid " + uuid.toString() + " not found.");
    }
}
