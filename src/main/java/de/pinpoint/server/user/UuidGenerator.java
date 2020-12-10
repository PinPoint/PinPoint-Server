package de.pinpoint.server.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

public class UuidGenerator {
    @Autowired
    private UserService userservice;

    public UUID generateUuid() {
        UUID uuid = UUID.randomUUID();

        /* chance of uuid collision (122 bits) loop is negligible (i would wonder if it even occurs once)*/
        while (userservice.userExists(uuid)) {
            uuid = UUID.randomUUID();
        }

        return uuid;
    }
}