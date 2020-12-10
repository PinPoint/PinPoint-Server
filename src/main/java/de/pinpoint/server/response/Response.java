package de.pinpoint.server.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response {
    /**
     * 0 means everything is alright. For everything else HTTP error code.
     */
    private int status;

    /**
     * Message why the request failed. "OK" if statusCode is 0.
     */
    private String message;

    /**
     * Constructs a default success response.
     */
    public Response() {
        this.status = 0;
        this.message = "OK";
    }

    /**
     * Returns whether or not the response is a success.
     *
     * @return true if success, false if fail
     */
    public boolean hasSucceeded() {
        return status == 0;
    }
}