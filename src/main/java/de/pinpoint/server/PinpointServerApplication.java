package de.pinpoint.server;

import de.pinpoint.server.user.PinpointUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PinpointServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PinpointServerApplication.class, args);
	}

}
